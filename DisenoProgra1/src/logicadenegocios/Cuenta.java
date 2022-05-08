package logicadenegocios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Cuenta {
	private static int numeroCuenta=1;
	private String fechaCreacion;
	private int saldo;
	private String status;
	private String pin;
	private ArrayList<Operacion> operaciones;
	private int cantidadOperaciones;
	
	public Cuenta() {
		this.operaciones = new ArrayList<Operacion>();
		numeroCuenta++;
	}
	
	public Cuenta(int pSaldo, String pPin) {
		this.saldo=pSaldo;
		this.pin=pPin;
		this.status="Activo";
		this.operaciones = new ArrayList<Operacion>();
//		numeroCuenta++;
	}
	
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	

	public ArrayList<Operacion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(ArrayList<Operacion> operaciones) {
		this.operaciones = operaciones;
	}
	
	public int deposito(int pSaldo) {
		return getSaldo()+ pSaldo;
		
	}
	
	
	public int retiro(int pSaldo) {
		//validar cuando que el saldo no quede en negativo con validar monto
		return getSaldo()- pSaldo;
		
	}
	
	public int consultarSaldo() {
		return getSaldo();
	}

	public String cambioPin(String pPin) {
		setPin(pPin);
		//llamar a validarFormatoPin, en caso que lo cumpola se realiza el cambio
		return pPin;
	}
	
	private boolean validarFormatoPin(String pPin) {
		return true;
	}
	/***
	 * Valida que el pin sea el mismo ingresado, en caso que falle 2 veces
	 * se procede a inactivar la cuenta
	 */
	private boolean validarPinIngresado(String pPin) {
		
		for(int i=0; i <=2; i++) {
			if (pPin.equals(getPin())){
				System.out.println("El pin es el correcto");
				return true;
			}
		}
		
		return false;
	}
	
	public int montoTotalComisionesDepositoRetiro() {
		int monto = 0;
		
		return monto + (montoTotalComisionesDeposito() + montoTotalComisionesRetiro()) ;
	}
	
	public int montoTotalComisionesDeposito() {
		int monto = 0;
		for (int i = 0; i <= getOperaciones().size(); i++ ) {
			if (getOperaciones().get(i).getTipo().equals("Deposito")) {
				monto =+ getOperaciones().get(i).getMontoComision();
			}			
		}
		return monto;
	}
	
	public int montoTotalComisionesRetiro() {
		int monto = 0;
		for (int i = 0; i <= getOperaciones().size(); i++ ) {
			if (getOperaciones().get(i).getTipo().equals("Retiro")) {
				monto =+ getOperaciones().get(i).getMontoComision();
			}			
		}
		return monto;
	}
	
	//Valida que el monto a retirar sea menos o igual al saldo.
	public boolean validarMonto(int pMonto) {
		if (pMonto <= getSaldo()) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public boolean esCuentaBloqueada() {
		if (getStatus().equals("Bloqueada")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void bloquearCuenta() {
		setStatus("Bloqueada");
	}

	@Override
	public String toString() {
		return "Cuenta [fechaCreacion=" + fechaCreacion + ", saldo=" + saldo + ", status=" + status + ", pin=" + pin
				+ ", operaciones=" + operaciones + ", cantidadOperaciones=" + cantidadOperaciones + "]";
	}
	
	
	
	
	
}
