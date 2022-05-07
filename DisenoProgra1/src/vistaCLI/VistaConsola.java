package vistaCLI;

import logicadenegocios.Persona;
import controladoresCLI.ControladorCuenta;
import controladoresCLI.ControladorPersona;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;

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
	
	public static void main(String[] args) throws IOException, ParseException {
		Scanner reader = new Scanner(System.in);
	    boolean salir = false;
	 
		
        do {
	   		System.out.println();
	   		System.out.println("Bienvenido al Zendicard Bank");
	   		System.out.println("Eliga la opcion que desea acceder: ");
	   		System.out.println();
	    	System.out.println("1. Registrar cliente ");
	    	System.out.println("2. Registrar cuenta ");
	    	System.out.println("3. Salir");
	    		
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
	    		
		
		
		
		
				

 

