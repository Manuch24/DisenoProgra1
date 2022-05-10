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


public class VistaConsola {
	
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
	        switch (opcion) 
	        {
	            case 1:
	            	ControladorPersona nuevoRegistro = new ControladorPersona();
	            	PersonaDAO personaDAO = new PersonaDAO();
	            	nuevoRegistro.cliRegistrarPersona(personaDAO);
	            	break;
	            case 2:
	            	ControladorCuenta nuevoRegistroCuenta = new ControladorCuenta();
	            	nuevoRegistroCuenta.cliRegistrarCuenta();
	            	break;
	            case 3:
	            	ControladorCuenta cambioPin = new ControladorCuenta();
	            	cambioPin.solicitarCambioPin();
	            	break;
	            
	            case 4:
	            	ControladorOperacion deposito = new ControladorOperacion();
	            	
	            	String salida ="";
	            	System.out.println("Ingrese su número de cuenta: ");
	            	boolean ver=false;
	            	Scanner nuevo = new Scanner(System.in);
	            	int numCuenta=nuevo.nextInt();
	            	if(cuentaDAO.verificarExistenciaCuenta(numCuenta)==true) {
	            		System.out.println("Ingrese el monto a depositar: ");
	            		float monto = nuevo.nextFloat();
	            		
	            		while(ver!=true) {
	            			//System.out.println("aqui");
	            			if(monto == (int)monto) {
	            				salida = deposito.depositoColones(monto, numCuenta, cuenta);
	            				ver=true;
	            			}else {
	            				System.out.println("Error en el formato del monto: ");
	    						System.out.println("Vuelva a ingresar un monto: ");
	    						monto = nuevo.nextInt();
	            			}
	            		} System.out.println(salida);
	            	}else {
	            		System.out.println("Esta cuenta no existe");
	            	}
	            	break;
	            
	            case 5:
	            	ControladorOperacion depositoCambiomoneda = new ControladorOperacion();
	            	
	            	String salidaTexto ="";
	            	System.out.println("Ingrese su número de cuenta: ");
	            	boolean verificar=false;
	            	Scanner nuevoInput = new Scanner(System.in);
	            	int numeroCuenta=nuevoInput.nextInt();
	            	if(cuentaDAO.verificarExistenciaCuenta(numeroCuenta)==true) {
	            		System.out.println("Ingrese el monto a depositar en dólares: ");
	            		float monto = nuevoInput.nextFloat();
	            		
	            		while(verificar!=true) {
	            			//System.out.println("aqui");
	            			if(monto == (int)monto) {
	            				salidaTexto = depositoCambiomoneda.depositoCambioMoneda(monto, numeroCuenta, cuenta);
	            				verificar=true;
	            			}else {
	            				System.out.println("Error en el formato del monto: ");
	    						System.out.println("Vuelva a ingresar un monto: ");
	    						monto = nuevoInput.nextInt();
	            			}
	            		} System.out.println(salidaTexto);
	            	}else {
	            		System.out.println("Esta cuenta no existe");
	            	}
	            	break;
	            	
	            	
	            case 6:
	            	ControladorOperacion retiro = new ControladorOperacion();
	            	
	            	String salidaTexto1 ="";
	            	System.out.println("Ingrese su número de cuenta: ");
	            	boolean verificar1=false;
	            	Scanner nuevoInput1 = new Scanner(System.in);
	            	int numeroCuenta1=nuevoInput1.nextInt();
	            	if(cuentaDAO.verificarExistenciaCuenta(numeroCuenta1)==true) {
	            		System.out.println("Ingrese su número de pin: ");
	            		String pin = nuevoInput1.next();
	            		
	            		if(cuentaDAO.buscarPin(numeroCuenta1).compareTo(pin)==0) {
	            			System.out.println("Ingrese el monto a retirar ");
		            		float monto = nuevoInput1.nextFloat();
		            		//Aqui va verificacion de número
		            		while(verificar1!=true) {
		            			//System.out.println("aqui");
		            			if(monto == (int)monto) {
		            				salidaTexto1 = retiro.retiroColones(monto, numeroCuenta1, cuenta);
		            				verificar1=true;
		            			}else {
		            				System.out.println("Error en el formato del monto: ");
		    						System.out.println("Vuelva a ingresar un monto: ");
		    						monto = nuevoInput1.nextInt();
		            			}
		            		}System.out.println(salidaTexto1);
	            		
	            		} else {
	            			System.out.println("Contraseña incorrecta");
	            		}
	            	}else {
	            		System.out.println("Esta cuenta no existe");
	            	}
	            	
	            	break;
	            	
	            case 7:
	            	ControladorOperacion retiroCambio = new ControladorOperacion();
	            	
	            	String salidaText ="";
	            	System.out.println("Ingrese su número de cuenta: ");
	            	boolean verific=false;
	            	Scanner nuevoI = new Scanner(System.in);
	            	int numeroCuen=nuevoI.nextInt();
	            	if(cuentaDAO.verificarExistenciaCuenta(numeroCuen)==true) {
	            		System.out.println("Ingrese su número de pin: ");
	            		String pin = nuevoI.next();
	            		
	            		if(cuentaDAO.buscarPin(numeroCuen).compareTo(pin)==0) {
	            			System.out.println("Ingrese el monto a retirar dólares ");
		            		float monto = nuevoI.nextFloat();
		            		//Aqui va verificacion de número
		            		while(verific!=true) {
		            			//System.out.println("aqui");
		            			if(monto == (int)monto) {
		            				salidaText = retiroCambio.retiroCompraMoneda(monto, numeroCuen, cuenta);
		            				verific=true;
		            			}else {
		            				System.out.println("Error en el formato del monto: ");
		    						System.out.println("Vuelva a ingresar un monto: ");
		    						monto = nuevoI.nextInt();
		            			}
		            		}System.out.println(salidaText);
	            		
	            		} else {
	            			System.out.println("Contraseña incorrecta");
	            		}
	            	}else {
	            		System.out.println("Esta cuenta no existe");
	            	}
	            	
	            	break;	            	
	            	
	            	
	            case 10:
	    	        
	            	System.out.println("Saliendo del programa");
	            	salir = true;
	            	break;
	            case 20:
	    	        
	            	System.out.println("Saliendo del proghrama");
	            	salir = true;
	            	break;
	            	
	            	
	            default:
	            	System.out.println("Ingrese una opcion correcta");
	            	break;
	        }
        }while(!salir);
	}

}
	    		
		
		
		
		
				

 

