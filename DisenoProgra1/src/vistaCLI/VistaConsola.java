package vistaCLI;

import logicadenegocios.Persona;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;

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
	
	public static void menuRegistrarPersona() throws IOException, ParseException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Persona persona = new Persona();
		
		System.out.println("Ingrese primer apellido: ");
		String apellido1 = br.readLine();
		persona.setPrimerApellido(apellido1);
		
		System.out.println("Ingrese segundo apellido: ");
		String  apellido2 = br.readLine();
		persona.setSegundoApellido(apellido2);
		
		System.out.println("Ingrese el nombre: ");
		String nombre = br.readLine();
		persona.setNombreCliente(nombre);
		
		System.out.println("Ingrese la identificacion: ");
		String identificacion = br.readLine();
		persona.setNombreCliente(identificacion);
		
		System.out.println("Ingrese la fecha de nacimiento con el siguiente formato dd/mm/yyyy: ");
		String fechaNacimiento = br.readLine();
		persona.setFechaNacimiento(fechaNacimiento);
		
		System.out.println("Ingrese su numero telefonico: ");
		String  telefono = br.readLine();
		persona.setNumeroTelefono(Integer.parseInt(telefono));
		
		System.out.println("Ingrese el correo: ");
		String correo = br.readLine();
		persona.setEmail(correo);
		
		System.out.println(persona.toString());
		System.out.println("Los datos registrados son: ");
		System.out.println();
		System.out.println("Codigo: "+persona.getIdPersona());
		System.out.println("Nombre: "+persona.getNombreCliente());
		System.out.println("Identificacion: "+persona.getIdentificacion());
		System.out.println("Numero telefonico: "+persona.getNumeroTelefono());
	
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
	    	System.out.println("2. Registrar cliente ");
	    	System.out.println("3. Salir");
	    		
	    	int opcion = reader.nextInt();
	        switch (opcion) 
	        {
	            case 1:
	            	menuRegistrarPersona();
	            	break;
	            case 2:
	            	menuRegistrarPersona();
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
	    		
		
		
		
		
				

 

