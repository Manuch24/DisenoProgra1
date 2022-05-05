package Validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
	private final static String regexEmail= "^[A-Za-z0-9+_.-]+@(.+)$";
	private final static String regexPin = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\da-zA-Z]).{6,6}$";
	private final static String regexTelefono = "^[0-9]{8}$";
	
	
	public static boolean validarEmailRegex(String email) {
		Pattern patron = Pattern.compile(regexEmail);
		Matcher coincidencia = patron.matcher(email);
		
		return coincidencia.matches();
	}
	
	public static boolean validarPinRegex(String pinCuenta) {
		Pattern patron = Pattern.compile(regexPin);
		Matcher coincidencia = patron.matcher(pinCuenta);
		
		return coincidencia.matches();
	}
	
	public static boolean validarTelefonoRegex(int numeroTelefono) {
		Pattern patron = Pattern.compile(regexTelefono);
		Matcher coincidencia = patron.matcher(String.valueOf(numeroTelefono));
		
		return coincidencia.matches();
	}
}
