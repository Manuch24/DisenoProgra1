package controladoresCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import consultasDAO.CuentaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;
import Validaciones.Validaciones;

public class ControladorCuenta {
	
	private CuentaDAO modeloCuenta;
	private boolean verificarPin = false;
	
	public void cliRegistrarCuenta() throws IOException {
		//Cuenta cuenta = new Cuenta();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Ingrese el saldo inicial a despositar: ");
		int saldo = br.read();
		//cuenta.setSaldo(saldo);
		
		System.out.println("Ingrese el pin para su cuenta: recuerde que debe ser de 6 caracteres y debe de contener al menos una mayúscula, un caracter especial y un número.");
		String pinIngresado = br.readLine();
		while(verificarPin!=true) {
			
			if(Validaciones.validarPinRegex(pinIngresado)==true) {
				//cuenta.setPin(pinIngresado);
				verificarPin=true;
			}else {
				System.out.println("Error en el formato de pin");
				System.out.println("Vuelva a ingresar un pin válido: ");
				pinIngresado = br.readLine();
			}
		}
		
		Cuenta cuenta = new Cuenta(saldo,pinIngresado);
		
		System.out.println(cuenta.toString());
	}
	
	public void solicitarCambioPin() throws IOException, SQLException {
		
		CuentaDAO consulta = new CuentaDAO(); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean verificarPin=false;
		System.out.println("Digite el número de cuenta: ");
		int numeroCuentaSolicitada;
		
		 numeroCuentaSolicitada=Integer.parseInt(br.readLine());
		
		if(consulta.verificarExistenciaCuenta(numeroCuentaSolicitada)==true) {
			System.out.println("Ingrese su pin: ");
			
			String pinIngresado=br.readLine();
			
			if(consulta.buscarPin(numeroCuentaSolicitada).compareTo(pinIngresado)==0) {
				System.out.println("Ingrese su nuevo pin: ");
				
				String nuevoPin = br.readLine();
				while(verificarPin!=true) {
					
					if(Validaciones.validarPinRegex(nuevoPin)==true) {
						//cuenta.setPin(pinIngresado);
						verificarPin=true;
						consulta.actualizarPin(nuevoPin, numeroCuentaSolicitada);
						System.out.println("Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta"+numeroCuentaSolicitada);
						break;
					}else {
						System.out.println("Error en el formato de pin");
						System.out.println("Vuelva a ingresar un pin válido: ");
						nuevoPin = br.readLine();
					}
				}
				
			}else {
				System.out.println("Pin incorrecto");
			}
		}else {
			System.out.println("No se encuentra esta cuenta");
		}
		
	}
	
	public int depositoColones(int montoDeposito, int numeroCuenta,CuentaDAO dao) {
		double montoComision=0;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//formatter.format(date);
		
		Operacion op = new Operacion("deposito",date,false,0,montoDeposito);
		dao.realizarDeposito(op, numeroCuenta);
		
		System.out.println("Estimado usuario, se han depositado correctamente "+montoDeposito+".00 colones");
		System.out.println("[El monto real depositado a su cuenta "+numeroCuenta+" es de "+montoDeposito+" colones]");
		System.out.println("[El monto cobreado por concepto de comision fue de "+montoComision+" colones, que fueron rebajados automáticamente de su saldo actual]");
		
		return montoDeposito;
	}
	
	

}
