package vistaCLI;

import logicadenegocios.Persona;
import controladoresCLI.ControladorCuenta;
import controladoresCLI.ControladorPersona;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;
import controladoresCLI.ControladorOperacion;
import Servicios.envioSMS;

public class VistaConsola {
	public static int cantOportunidades = 3;

	public static void menu() {
		System.out.println();
		System.out.println("Bienvenido al Zendicard Bank");
		System.out.println("Eliga la opcion que desea acceder: ");
		System.out.println();
		System.out.println("1. Registrar cliente ");

	}

	public static void main(String[] args) throws IOException, ParseException, SQLException {
		Scanner reader = new Scanner(System.in);
		boolean salir = false;
		CuentaDAO cuentaDAO = new CuentaDAO();
		OperacionDAO cuenta = new OperacionDAO();
		envioSMS mensajes = new envioSMS();
		ControladorOperacion ctrl = new ControladorOperacion();

		do {
			System.out.println();
			System.out.println("Bienvenido al Zendicard Bank");
			System.out.println("Eliga la opcion que desea acceder: ");
			System.out.println();
			System.out.println("1. Registrar cliente ");
			System.out.println("2. Registrar cuenta ");
			System.out.println("3. Listar todos los clientes creados: ");
			System.out.println("3.1. Consultar datos de un cliente: ");
			System.out.println("4. Listar toda las cuentas creadas: ");
			System.out.println("4.1. Consultar datos de cuenta: ");
			System.out.println("5. Cambiar Pin");
			System.out.println("6. Realizar Depósito");
			System.out.println("7. Realizar depósito con cambio de moneda");
			System.out.println("8. Realizar retiro en colones");
			System.out.println("9. Realizar retiro con cambio de moneda");
			System.out.println("10. Realizar transferencia");
			System.out.println("11. Consultar tipo de cambio de compra de divisa extranjera");
			System.out.println("12. consultar tipo de venta de divisa extrajera");
			System.out.println("13. Consultar saldo actual");
			System.out.println("14. Consultat saldo actual(en su equivalente a la divisa extranjera)");
			System.out.println("15. Consultar estado de cuenta");
			System.out.println("16. Consultar estado de cuenta (con montos equivalente a la divisa extranjera)");
			System.out.println("17. Consultar estatus de una cuenta");
			System.out.println("18. Consultar ganancias del banco producto de cobro de comisiones TOTALIZADO");
			System.out.println("19. Consultar ganancias del banco producto de cobro de comisiones para una cuenta");
			System.out.println("20. salir");

			int opcion = reader.nextInt();
			switch (opcion) {
			case 1:
				ControladorPersona nuevoRegistro = new ControladorPersona();
				PersonaDAO personaDAO = new PersonaDAO();
				nuevoRegistro.cliRegistrarPersona(personaDAO);
				break;
			case 2:
				ControladorCuenta nuevoRegistroCuenta = new ControladorCuenta();
				nuevoRegistroCuenta.cliRegistrarCuenta();
				break;
			case 7:
				ControladorCuenta cambioPin = new ControladorCuenta();
				cambioPin.solicitarCambioPin();
				break;

			case 8:
				ControladorOperacion deposito = new ControladorOperacion();

				String salida = "";
				System.out.println("Ingrese su número de cuenta: ");
				boolean ver = false;
				Scanner nuevo = new Scanner(System.in);
				int numCuenta = nuevo.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(numCuenta) == true) {
					System.out.println("Ingrese el monto a depositar: ");
					float monto = nuevo.nextFloat();

					while (ver != true) {
						// System.out.println("aqui");
						if (monto == (int) monto) {
							salida = deposito.depositoColones(monto, numCuenta, cuenta);
							ver = true;
						} else {
							System.out.println("Error en el formato del monto: ");
							System.out.println("Vuelva a ingresar un monto: ");
							monto = nuevo.nextInt();
						}
					}
					System.out.println(salida);
				} else {
					System.out.println("Esta cuenta no existe");
				}
				break;

			case 9:
				ControladorOperacion depositoCambiomoneda = new ControladorOperacion();

				String salidaTexto = "";
				System.out.println("Ingrese su número de cuenta: ");
				boolean verificar = false;
				Scanner nuevoInput = new Scanner(System.in);
				int numeroCuenta = nuevoInput.nextInt();
				if (!cuenta.estatusInactivo(numeroCuenta)) {
					if (cuentaDAO.verificarExistenciaCuenta(numeroCuenta) == true) {
						System.out.println("Ingrese el monto a depositar en dólares: ");
						float monto = nuevoInput.nextFloat();

						while (verificar != true) {
							// System.out.println("aqui");
							if (monto == (int) monto) {
								salidaTexto = depositoCambiomoneda.depositoCambioMoneda(monto, numeroCuenta, cuenta);
								verificar = true;
							} else {
								System.out.println("Error en el formato del monto: ");
								System.out.println("Vuelva a ingresar un monto: ");
								monto = nuevoInput.nextInt();
							}
						}
						System.out.println(salidaTexto);
					} else {
						System.out.println("Esta cuenta no existe");
					}
				} else {
					System.out.println("La cuenta se encuentra inactiva");
				}
				break;

			case 10:
				ControladorOperacion retiro = new ControladorOperacion();

				String salidaTexto1 = "";
				System.out.println("Ingrese su número de cuenta: ");
				boolean verificar1 = false;
				Scanner nuevoInput1 = new Scanner(System.in);
				int numeroCuenta1 = nuevoInput1.nextInt();
				if (!cuenta.estatusInactivo(numeroCuenta1)) {
					if (cuentaDAO.verificarExistenciaCuenta(numeroCuenta1) == true) {
						System.out.println("Ingrese su número de pin: ");
						String pin = nuevoInput1.next();

						if (cuentaDAO.buscarPin(numeroCuenta1).compareTo(pin) == 0) {
							String codigo = mensajes.generarCodigoVerificacion();
							mensajes.enviarSMS(cuenta.extaerTelefono(numeroCuenta1), codigo);

							System.out.println("Ingrese el código de seguridad enviado a su teléfono: ");
							String codigoVerfi = nuevoInput1.next();

							if (codigo.equals(codigoVerfi)) {

								System.out.println("Ingrese el monto a retirar ");
								float monto = nuevoInput1.nextFloat();

								while (verificar1 != true) {
									// System.out.println("aqui");
									if (monto == (int) monto) {
										salidaTexto1 = retiro.retiroColones(monto, numeroCuenta1, cuenta);
										verificar1 = true;
									} else {
										System.out.println("Error en el formato del monto: ");
										System.out.println("Vuelva a ingresar un monto: ");
										monto = nuevoInput1.nextInt();
									}
								}
								System.out.println(salidaTexto1);
							} else {
								System.out.println("Código incorrecto");
							}
						} else {
							System.out.println("Contraseña incorrecta");
						}
					} else {
						System.out.println("Esta cuenta no existe");
					}
				} else {
					System.out.println("La cuenta se encuentra inactiva");
				}
				break;

			case 11:
				ControladorOperacion retiroCambio = new ControladorOperacion();

				String salidaText = "";
				System.out.println("Ingrese su número de cuenta: ");
				boolean verific = false;
				Scanner nuevoI = new Scanner(System.in);
				int numeroCuen = nuevoI.nextInt();
				if (!cuenta.estatusInactivo(numeroCuen)) {
					if (cuentaDAO.verificarExistenciaCuenta(numeroCuen) == true) {
						System.out.println("Ingrese su número de pin: ");
						String pin = nuevoI.next();

						if (cuentaDAO.buscarPin(numeroCuen).compareTo(pin) == 0) {

							String codigo = mensajes.generarCodigoVerificacion();
							mensajes.enviarSMS(cuenta.extaerTelefono(numeroCuen), codigo);

							System.out.println("Ingrese el código de seguridad enviado a su teléfono: ");
							String codigoVerfi = nuevoI.next();
							if (codigo.equals(codigoVerfi)) {
								System.out.println("Ingrese el monto a retirar dólares ");
								float monto = nuevoI.nextFloat();

								while (verific != true) {

									if (monto == (int) monto) {
										salidaText = retiroCambio.retiroCompraMoneda(monto, numeroCuen, cuenta);
										verific = true;
									} else {
										System.out.println("Error en el formato del monto: ");
										System.out.println("Vuelva a ingresar un monto: ");
										monto = nuevoI.nextInt();
									}
								}
								System.out.println(salidaText);
							} else {
								System.out.println("Código Incorrecto");
							}
						} else {
							System.out.println("Contraseña incorrecta");
						}
					} else {
						System.out.println("Esta cuenta no existe");
					}
				} else {
					System.out.println("La cuenta se encuentra inactiva");
				}
				break;

			case 12:
				ControladorOperacion transferencia = new ControladorOperacion();
				int cantidad = transferencia.cantOporu;
				String transfeText = "";
				System.out.println("Ingrese su número de cuenta: ");
				boolean verifica = false;
				Scanner input = new Scanner(System.in);
				int cuentaRetiro = input.nextInt();
				if (!cuenta.estatusInactivo(cuentaRetiro)) {

					if (cuentaDAO.verificarExistenciaCuenta(cuentaRetiro) == true) {

						while (cantOportunidades > 0) {

							System.out.println("Ingrese su número de pin: ");
							String pin = input.next();
							if (cuentaDAO.buscarPin(cuentaRetiro).equals(pin)) {

								String codigo = mensajes.generarCodigoVerificacion();
								mensajes.enviarSMS(cuenta.extaerTelefono(cuentaRetiro), codigo);

								System.out.println("Ingrese el código de seguridad enviado a su teléfono: ");
								String codigoVerfi = input.next();
								if (codigo.equals(codigoVerfi)) {

									System.out.println("Ingrese el monto a transferir: ");
									Float montoDepositar = input.nextFloat();

									System.out.println("Ingrese la cuenta destino");
									int cuentaDepositar = input.nextInt();
									if (cuentaDAO.verificarExistenciaCuenta(cuentaDepositar) == true) {
										transfeText = transferencia.realizarTransferencia(cuentaRetiro, montoDepositar,
												cuentaDepositar, cuenta);

										System.out.println(transfeText);
										break;

									} else {
										System.out.println("No existe la cuenta destino");
									}
								} else {
									System.out.println("codigoIncorrecto");
									cantOportunidades--;
									System.out.println("Le quedan [" + cantOportunidades + "] oportunidades");
								}
							} else {
								System.out.println("Pin Incorrecto");
								cantOportunidades--;
								System.out.println("Le quedan [" + cantOportunidades + "] oportunidades");
							}
						} // else {
						if (cantOportunidades == 0) {
							System.out.println("Se quedón sin intentos");
							ctrl.inactivarCuenta(cuentaRetiro, cuenta);
						}
						// }

					} else {
						System.out.println("Cuenta no existe");
					}
				} else {
					System.out.println("La cuenta se encuenta inactiva");
				}

				break;
			case 13:

				System.out
						.println("Estimado usuario, se le indica que el cambio por el tipo de compra según el BCCR es: "
								+ ctrl.consultarCambioCompra());

				break;

			case 14:
				// cambioVenta
				System.out
						.println("Estimado usuario, se le indica que el cambio por el tipo de venta según el BCCR es: "
								+ ctrl.consultarCambioVenta());

				break;

			case 15:
				// saldoactual
				// String transfeText = "";
				System.out.println("Ingrese su número de cuenta: ");
				// boolean verifica = false;
				Scanner entr = new Scanner(System.in);
				int cuentaConsulta = entr.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(cuentaConsulta) == true) {
					System.out.println("Ingrese su número de pin: ");
					String pin = entr.next();

					if (cuentaDAO.buscarPin(cuentaConsulta).compareTo(pin) == 0) {
						String mostrar = ctrl.consultaSaldoActual(cuentaConsulta, cuenta);
						System.out.println(mostrar);
					} else {
						System.out.println("Pin Incorrecto");
					}

				} else {
					System.out.println("La cuenta no existe");
				}
				break;

			case 16:
				// saldoactualCambio
				System.out.println("Ingrese su número de cuenta: ");
				// boolean verifica = false;
				Scanner entrada = new Scanner(System.in);
				int cuentaCons = entrada.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(cuentaCons) == true) {
					System.out.println("Ingrese su número de pin: ");
					String pin = entrada.next();

					if (cuentaDAO.buscarPin(cuentaCons).compareTo(pin) == 0) {
						String mostrar = ctrl.saldoActualEquivalente(cuentaCons, cuenta);
						System.out.println(mostrar);
					} else {
						System.out.println("Pin Incorrecto");
					}

				} else {
					System.out.println("La cuenta no existe");
				}

				break;
			case 17:
				ControladorOperacion consultaEstado = new ControladorOperacion();

				String Impresion = "";
				System.out.println("Ingrese su número de cuenta: ");
				// boolean verfi = false;
				Scanner nuevoEn = new Scanner(System.in);
				int Ncuenta = nuevoEn.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(Ncuenta) == true) {
					System.out.println("Ingrese el su pin: ");
					String pinIn = nuevoEn.next();

					if (cuentaDAO.buscarPin(Ncuenta).compareTo(pinIn) == 0) {

						Impresion = consultaEstado.consultaEstadoCuenta(Ncuenta, cuenta);
						// verific = true;

						System.out.println(Impresion);

					} else {
						System.out.println("Contraseña incorrecta");
					}
				} else {
					System.out.println("Esta cuenta no existe");
				}

				break;

			case 18:
				ControladorOperacion consultaEstadoCambio = new ControladorOperacion();

				String Impresion1 = "";
				System.out.println("Ingrese su número de cuenta: ");
				// boolean verfi = false;
				Scanner entradaN = new Scanner(System.in);
				int NumCuenta = entradaN.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(NumCuenta) == true) {
					System.out.println("Ingrese el su pin: ");
					String pinIn = entradaN.next();

					if (cuentaDAO.buscarPin(NumCuenta).compareTo(pinIn) == 0) {

						Impresion1 = consultaEstadoCambio.consultaEstadoCuentaCambio(NumCuenta, cuenta);
						// verific = true;

						System.out.println(Impresion1);

					} else {
						System.out.println("Contraseña incorrecta");
					}
				} else {
					System.out.println("Esta cuenta no existe");
				}

				break;

			case 19:
				// estadoCuenta

				Scanner entradaNueva = new Scanner(System.in);
				int cuentaEstatus = entradaNueva.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(cuentaEstatus) == true) {
					System.out.println("Ingrese el su pin: ");
					String pinIn = entradaNueva.next();

					if (cuentaDAO.buscarPin(cuentaEstatus).compareTo(pinIn) == 0) {

						String salida1 = ctrl.consultaEstatusCuenta(cuentaEstatus, cuenta);
						// verific = true;

						System.out.println(salida1);

					} else {
						System.out.println("Contraseña incorrecta");
					}
				} else {
					System.out.println("Esta cuenta no existe");
				}

				break;

			case 20:
				// estadoCuentaCambiomonedas
				System.out.println(ctrl.consultarGanaciasTotales());

				break;

			case 21:
				// saldoactualCambio
				Scanner entradaNa = new Scanner(System.in);
				int cuentaComisiones = entradaNa.nextInt();
				if (cuentaDAO.verificarExistenciaCuenta(cuentaComisiones) == true) {
					System.out.println(ctrl.consultarGanaciasPorCuenta(cuentaComisiones));
				} else {
					System.out.println("Cuenta no existe");
				}

				break;
			case 22:

				System.out.println("Saliendo del programa");
				salir = true;
				break;

			default:
				System.out.println("Ingrese una opcion correcta");
				break;
			}
		} while (!salir);
	}

}
