package vistaCLI;

import logicadenegocios.Persona;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;


public class vistaCLI {

	public static void main(String[] args) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Bienvenido al Zendicard Bank");
		System.out.println("Eliga la opcion que desea acceder: ");
		System.out.println();
		
		Persona p1 = new Persona();
		p1.setNombreCliente("Manuel");
		p1.setPrimerApellido("Chaves");
		p1.setSegundoApellido("Moya");
		System.out.println(p1.toString());
		
				

	}

}
