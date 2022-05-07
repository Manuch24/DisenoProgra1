package controladoresCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import logicadenegocios.Persona;

import consultasDAO.PersonaDAO;

public class ControladorPersona {
	
	private PersonaDAO modeloConsulta;
	private boolean verificarTelefono=false;
	private boolean verificarEmail=false;
	
	public void cliRegistrarPersona(PersonaDAO modeloConsulta) throws IOException, ParseException {
		
		this.modeloConsulta = modeloConsulta;
		
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
		persona.setIdentificacion(Integer.parseInt(identificacion));
		
		System.out.println("Ingrese la fecha de nacimiento con el siguiente formato yyyy-MM-dd: ");
		String fechaNacimiento = br.readLine();
		persona.setFechaNacimiento(fechaNacimiento);
		
		System.out.println("Ingrese su numero telefonico: ");
		String  telefono = br.readLine();
		System.out.println("Es extraño: "+ Integer.parseInt(telefono));
		while(verificarTelefono!=true) {
			if (Validaciones.Validaciones.validarTelefonoRegex(Integer.parseInt(telefono))==true) {
				persona.setNumeroTelefono(Integer.parseInt(telefono));
				verificarTelefono=true;
			}else{
				System.out.println("Error en el formato de teléfono");
				System.out.println("Vuelva a ingresar su numero telefonico: ");
				telefono = br.readLine();
			}
		}
		System.out.println("Ingrese el correo: ");
		String correo = br.readLine();
		while(verificarEmail!=true) {
			if (Validaciones.Validaciones.validarEmailRegex(correo)==true) {
				persona.setEmail(correo);
				verificarEmail=true;
			}else{
				System.out.println("Error en el formato de su email");
				System.out.println("Vuelva a ingresar su email: ");
				correo = br.readLine();
			}
		}
		//persona.setEmail(correo);
		
		if (modeloConsulta.registrar(persona, fechaNacimiento)) {
			System.out.println("Registro guardado");
		}else {
			System.out.println("Error al guardar");
		}
		
		System.out.println(persona.toString());
		System.out.println("Los datos registrados son: ");
		System.out.println();
		System.out.println("Codigo: "+persona.getIdPersona());
		System.out.println("Nombre: "+persona.getNombreCliente());
		System.out.println("Identificacion: "+persona.getIdentificacion());
		System.out.println("Numero telefonico: "+persona.getNumeroTelefono());
		
		
	
	
		
	}
}
