package controladoresCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import logicadenegocios.Persona;

public class ControladorPersona {
	
	public void cliRegistrarPersona() throws IOException, ParseException {
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
		persona.setIdentificacion(identificacion);
		
		System.out.println("Ingrese la fecha de nacimiento con el siguiente formato dd/mm/yyyy: ");
		String fechaNacimiento = br.readLine();
		persona.setFechaNacimiento(fechaNacimiento);
		
		System.out.println("Ingrese su numero telefonico: ");
		String  telefono = br.readLine();
		
		while(Validaciones.Validaciones.validarTelefonoRegex(Integer.parseInt(telefono))!=true) {
			if (Validaciones.Validaciones.validarTelefonoRegex(Integer.parseInt(telefono))==true) {
				persona.setNumeroTelefono(Integer.parseInt(telefono));
			}else{
				System.out.println("Error en el formato de teléfono");
				System.out.println("Vuelva a ingresar su numero telefonico: ");
				telefono = br.readLine();
			}
		}
		System.out.println("Ingrese el correo: ");
		String correo = br.readLine();
		while(Validaciones.Validaciones.validarEmailRegex(correo)!=true) {
			if (Validaciones.Validaciones.validarEmailRegex(correo)==true) {
				persona.setEmail(correo);
			}else{
				System.out.println("Error en el formato de su email");
				System.out.println("Vuelva a ingresar su email: ");
				correo = br.readLine();
			}
		}
		persona.setEmail(correo);
		
		System.out.println(persona.toString());
		System.out.println("Los datos registrados son: ");
		System.out.println();
		System.out.println("Codigo: "+persona.getIdPersona());
		System.out.println("Nombre: "+persona.getNombreCliente());
		System.out.println("Identificacion: "+persona.getIdentificacion());
		System.out.println("Numero telefonico: "+persona.getNumeroTelefono());
	}
}
