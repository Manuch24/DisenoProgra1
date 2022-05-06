package logicadenegocios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Persona {
	private String idPersona;
	private int identificacion;
	private String nombreCliente;
	private String primerApellido;
	private String segundoApellido;
	private String fechaNacimiento;
	private int numeroTelefono;
	private String email;
	private ArrayList<Cuenta> misCuentas;
	private static int cantPersonas = 1;
	
	public Persona(String pNombreCliente, String pPrimerApellido, String pSegundoApellido,
			String pFechaNacimiento, int pNumeroTelefono, String pEmail) {
		super();
		this.idPersona += String.valueOf(cantPersonas);
		this.nombreCliente = pNombreCliente;
		this.primerApellido = pPrimerApellido;
		this.segundoApellido = pSegundoApellido;
		this.fechaNacimiento = pFechaNacimiento;
		this.numeroTelefono = pNumeroTelefono;
		this.email = pEmail;
		cantPersonas++;
		this.misCuentas = new ArrayList<Cuenta>();
	}
	
	public Persona() {
		this.misCuentas = new ArrayList<Cuenta>();
		this.idPersona = "CIF_";
		cantPersonas++;
	}
	
	
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	
	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento)  {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(int numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Cuenta> getMisCuentas() {
		return misCuentas;
	}

	public void setMisCuentas(ArrayList<Cuenta> misCuentas) {
		this.misCuentas = misCuentas;
	}
	
	
	
	public static int getCantPersonas() {
		return cantPersonas;
	}

	public static void setCantPersonas(int cantPersonas) {
		Persona.cantPersonas = cantPersonas;
	}


	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombreCliente=" + nombreCliente + ", primerApellido="
				+ primerApellido + ", segundoApellido=" + segundoApellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", numeroTelefono=" + numeroTelefono + ", email=" + email + ", misCuentas=" + misCuentas + "]";
	}
	
	
	
	
	
	
	
}
