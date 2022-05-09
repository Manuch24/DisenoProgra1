package logicadenegocios;

import java.util.Date;

public class Operacion {
	private String tipo;
	private Date fechaOperacion;
	private boolean hayComision;
	private int montoComision;
	private int montoOperacion;
	
	
	public Operacion(String pTipo, Date pFechaOperacion, boolean pHayComision, int pMontoComision, int pMontoOperacion) {
		super();
		this.tipo = pTipo;
		this.fechaOperacion = pFechaOperacion;
		this.hayComision = pHayComision;
		this.montoComision = pMontoComision;
		this.montoOperacion = pMontoOperacion;
	}
	//sin monto
	public Operacion(String pTipo, Date pFechaOperacion, boolean pHayComision, int pMontoOperacion) {
		super();
		this.tipo = pTipo;
		this.fechaOperacion = pFechaOperacion;
		this.hayComision = pHayComision;
		this.montoOperacion = pMontoOperacion;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public boolean isHayComision() {
		return hayComision;
	}
	public void setHayComision(boolean hayComision) {
		this.hayComision = hayComision;
	}
	public int getMontoComision() {
		return montoComision;
	}
	public void setMontoComision(int montoComision) {
		this.montoComision = montoComision;
	}
	public int getMontoOperacion() {
		return montoOperacion;
	}
	public void setMontoOperacion(int montoOperacion) {
		this.montoOperacion = montoOperacion;
	}
	
	
	
}
