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
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;


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
		
        do {
	   		System.out.println();
	   		System.out.println("Bienvenido al Zendicard Bank");
	   		System.out.println("Eliga la opcion que desea acceder: ");
	   		System.out.println();
	    	System.out.println("1. Registrar cliente ");
	    	System.out.println("2. Registrar cuenta ");
	    	System.out.println("3. Cambiar Pin");
	    	System.out.println("4. Realizar Depósito");
	    	System.out.println("5. Salir");
	    	System.out.println("4. Salir");
	    	System.out.println("4. Salir");
	    	
	    		
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
	            	ControladorCuenta deposito = new ControladorCuenta();
	            	CuentaDAO cuenta = new CuentaDAO();
	            	System.out.println("Ingrese su número de cuenta: ");
	            	boolean ver=false;
	            	Scanner nuevo = new Scanner(System.in);
	            	int numCuenta=nuevo.nextInt();
	            	if(cuentaDAO.verificarExistenciaCuenta(numCuenta)==true) {
	            		System.out.println("Ingrese el monto a depositar: ");
	            		int monto = nuevo.nextInt();
	            		
	            		while(ver!=true) {
	            			//System.out.println("aqui");
	            			if(monto == (int)monto) {
	            				deposito.depositoColones(monto, numCuenta, cuentaDAO);
	            				ver=true;
	            			}else {
	            				System.out.println("Error en el formato del monto: ");
	    						System.out.println("Vuelva a ingresar un monto: ");
	    						monto = nuevo.nextInt();
	            			}
	            		}//System.out.println("aqui2");
	            	}else {
	            		System.out.println("Esta cuenta no existe");
	            	}
	            	break;
	            case 5:
	    	        
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
	    		
		
		
		
		
				

 

