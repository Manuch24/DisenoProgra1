package logicadenegocios;

import java.util.Date;

public class Operacion {
	private String tipo;
	private Date fechaOperacion;
	private boolean hayComision;
	private float montoComision;
	private float montoOperacion;
	
	
	public Operacion(String pTipo, Date pFechaOperacion, boolean pHayComision, float pMontoComision, float pMontoOperacion) {
		super();
		this.tipo = pTipo;
		this.fechaOperacion = pFechaOperacion;
		this.hayComision = pHayComision;
		this.montoComision = pMontoComision;
		this.montoOperacion = pMontoOperacion;
	}
	//sin monto
	public Operacion(String pTipo, Date pFechaOperacion, boolean pHayComision, float pMontoOperacion) {
		super();
		this.tipo = pTipo;
		this.fechaOperacion = pFechaOperacion;
		this.hayComision = pHayComision;
		this.montoOperacion = pMontoOperacion;
	}
	
	public Operacion() {
		
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
	public float getMontoComision() {
		return montoComision;
	}
	public void setMontoComision(float montoComision) {
		this.montoComision = montoComision;
	}
	public float getMontoOperacion() {
		return montoOperacion;
	}
	public void setMontoOperacion(float montoOperacion) {
		this.montoOperacion = montoOperacion;
	}
	
	
	
}
