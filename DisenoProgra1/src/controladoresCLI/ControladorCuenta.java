package controladoresCLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import consultasDAO.CuentaDAO;
import logicadenegocios.Cuenta;
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
}
