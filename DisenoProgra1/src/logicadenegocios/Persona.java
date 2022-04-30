package logicadenegocios;

import java.util.Date;

public class Persona {
	private String idPersona;
	private int cedula;
	private String nombreCliente;
	private String primerApellido;
	private String segundoApellido;
	private Date fechaNacimiento;
	private int numeroTelefono;
	private String email;
	
	
	public Persona(String pIdPersona, int pCedula, String pNombreCliente, String pPrimerApellido, String pSegundoApellido,
			Date pFechaNacimiento, int pNumeroTelefono, String pEmail) {
		super();
		this.idPersona = pIdPersona;
		this.cedula = pCedula;
		this.nombreCliente = pNombreCliente;
		this.primerApellido = pPrimerApellido;
		this.segundoApellido = pSegundoApellido;
		this.fechaNacimiento = pFechaNacimiento;
		this.numeroTelefono = pNumeroTelefono;
		this.email = pEmail;
	}
	
	public String getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
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
	
	
	
	
}
