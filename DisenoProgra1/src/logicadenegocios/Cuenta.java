package logicadenegocios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import Servicios.BCCRCambioMoneda;
import consultasDAO.OperacionDAO;

import java.math.BigDecimal;
//import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Cuenta {
	private static int numeroCuenta = 1;
	private String fechaCreacion;
	private int saldo;
	private String status;
	private String pin;
	private ArrayList<Operacion> operaciones;
	private int cantidadOperaciones;
	public static int cantOporu = 3;
	private static int cantidadOperacionesGratis = 0;
	private static int montoComision = 0;
	public static float saldoAlmacenado=0;
	
	OperacionDAO operacionDAO = new OperacionDAO();
	private BCCRCambioMoneda cambio = new BCCRCambioMoneda();

	public Cuenta() {
		this.operaciones = new ArrayList<Operacion>();
		numeroCuenta++;
	}

	public Cuenta(int pSaldo, String pPin) {
		this.saldo = pSaldo;
		this.pin = pPin;
		this.status = "Activo";
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

	/*public int deposito(int pSaldo) {
		return getSaldo() + pSaldo;

	}*/

	/*public int retiro(int pSaldo) {
		// validar cuando que el saldo no quede en negativo con validar monto
		return getSaldo() - pSaldo;

	}*/

	public int consultarSaldo() {
		return getSaldo();
	}

	public String cambioPin(String pPin) {
		setPin(pPin);
		// llamar a validarFormatoPin, en caso que lo cumpola se realiza el cambio
		return pPin;
	}

	private boolean validarFormatoPin(String pPin) {
		return true;
	}

	/***
	 * Valida que el pin sea el mismo ingresado, en caso que falle 2 veces se
	 * procede a inactivar la cuenta
	 */
	private boolean validarPinIngresado(String pPin) {

		for (int i = 0; i <= 2; i++) {
			if (pPin.equals(getPin())) {
				System.out.println("El pin es el correcto");
				return true;
			}
		}

		return false;
	}

	public String depositoColones(int montoDeposito) {
		boolean comision = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if (cantidadOperacionesGratis > 3) {
			montoComision = (int) (montoDeposito * 0.02);
			saldo = montoDeposito - montoComision;
			comision = true;
		} else {
			saldo = montoDeposito;
		}
		Operacion op = new Operacion("deposito", date, comision, montoComision, saldo);

		String salida = "Estimado usuario, se han depositado correctamente " + montoDeposito + ".00 colones \n"
				+ "[El monto real depositado a su cuenta " + numeroCuenta + " es de " + saldo + " colones] \n"
				+ "[El monto cobreado por concepto de comision fue de " + montoComision
				+ " colones, que fueron rebajados automáticamente de su saldo actual]";

		return salida;
	}

	public String depositoCambioMoneda(float montoDepositoDolares) {

		float compraCambio = cambio.getVenta();
		float montoEnColones = 0;

		float saldo = 0;
		float montoComision = 0;
		boolean comision = false;

		Date date = new Date();
		String formatter = new SimpleDateFormat("dd/MM/yyyy").format(date);

		if (cantidadOperacionesGratis > 3) {
			montoComision = (int) (montoDepositoDolares * 0.02);
			saldo = montoDepositoDolares - montoComision;
			comision = true;
		} else {
			saldo = montoDepositoDolares;
		}

		montoEnColones = montoDepositoDolares * compraCambio;

		String deposito = depositoColones(numeroCuenta);

		String resultado = "Estimado usuario, se han recibido correctamente " + montoDepositoDolares + ".00 dólares \n"
				+ "[Según el BCCR, el tipo de cambio de compra del dólar de " + formatter + " es " + compraCambio
				+ "] \n" + "[El monto equivalente en colones es " + montoEnColones + "] \n" + deposito;

		return resultado;
	}

	public String retiroColones(float montoRetiro) {
		// String respuesta="";
		float saldo = montoRetiro;
		float montoComision = 0;
		boolean comision = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		cantidadOperacionesGratis = operacionDAO.verificarCantTransaccionesGratis(numeroCuenta) + 1;

		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoRetiro * 0.02);
			montoRetiro += montoComision;
			comision = true;
			System.out.println("aqui");
		}
		String respuesta = "Estimado usuario, el monto de este retiro es: " + saldo + " colones \n"
				+ "[El monto cobrado por concepto de comisión fue de: " + montoComision
				+ " colones, que fueron rebajados automáticamente de su saldo actual]";

		if (operacionDAO.confirmarSaldo(numeroCuenta) >= (montoRetiro + montoComision)) {
			System.out.println("Entra aqui");
			Operacion op = new Operacion("retiro", date, comision, montoComision, montoRetiro);
			operacionDAO.realizarRetiro(op, numeroCuenta);

			return respuesta;

		} else {
			return "No se encuentran suficientes fondos en sus cuenta";
		}

	}

	public String retiroCompraMoneda(float montoRetiroDolares) {
		float ventaCambio = cambio.getCompra();
		float montoEnColones = 0;
		float montoComision = 0;

		Date date = new Date();
		String formatter = new SimpleDateFormat("dd/MM/yyyy").format(date);

		montoEnColones = ventaCambio * montoRetiroDolares;
		cantidadOperacionesGratis = operacionDAO.verificarCantTransaccionesGratis(numeroCuenta) + 1;
		// System.out.println(montoEnColones);
		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoEnColones * 0.02);

		}
		retiroColones(montoEnColones);

		String resultado = "Estimado usuario, el monto de retiro es: " + montoRetiroDolares + " dólares \n"
				+ "[Según el BCCR, el tipo de cambio de compra del dólar de " + formatter + " es " + ventaCambio
				+ "] \n" + "[El monto equivalente de su retiro " + montoEnColones + " colones] \n"
				+ "[El monto cobrado por concepto de comisión fue de " + montoComision
				+ " colobes, que fueron rebajados automáticamente de sus saldo actual]";

		return resultado;
	}

	public String realizarTransferencia(int numCuentaRetiro, float monto, int numeroCuentaDestino, OperacionDAO dao) {

		if (monto != (int) monto) {
			return "Error en el formato.";
		}

		String retiroTransferencia[] = retiroColones(monto).split(" ");

		float montoExtraido = Float.parseFloat(retiroTransferencia[8]);
		float comisionExtraido = Float.parseFloat(retiroTransferencia[19]);

		depositoColonesTransferencia(montoExtraido);

		String res = "Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente.  \n"
				+ "El monto retirado de la cuenta origen y depositado en la cuenta destino es " + monto
				+ " colones.  \n" + "[El monto cobrado por concepto de comisión a la cuenta origen fue de "
				+ comisionExtraido + " colones, que fueron rebajados automáticamente de su saldo actual]";

		return res;
	}

	public boolean depositoColonesTransferencia(float montoDeposito) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		Operacion op = new Operacion("deposito", date, false, 0, montoDeposito);
		if (operacionDAO.realizarDeposito(op, numeroCuenta)) {
			return true;
		}

		return false;

	}

	public String inactivarCuenta(int numeroCuenta) {

		operacionDAO.InactivarCuenta(numeroCuenta);

		return "Se inactivó su cuenta";
	}

	public String consultaSaldoActual(int numCuenta) {

		saldoAlmacenado = operacionDAO.confirmarSaldo(numCuenta);

		return "Estimado usuario el saldo actual de su cuenta es: " + saldoAlmacenado + " colones.";
	}

	public String saldoActualEquivalente(int numCuenta) {
		double resultadoCambio = 0;
		float cambioCompra = cambio.getCompra();

		consultaSaldoActual(numCuenta);

		resultadoCambio = (double) (saldoAlmacenado / cambioCompra);

		return "Estimado usuario el saldo actual de su cuenta en " + resultadoCambio + " dólares \n"
				+ "Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra \n"
				+ "[Según el BCCR, el tipo de cambio de compra, del dólar de hoy es: " + cambioCompra + "]";
	}

	public String consultaEstadoCuenta(int numCuenta) {
		Vector salida;
		String resultado = "";
		double saldo = 0;

		salida = operacionDAO.generarEstadoCuenta(numCuenta);
		saldo = (double) operacionDAO.confirmarSaldo(numCuenta);
		for (int i = 0; i < salida.size(); i++) {
			resultado += salida.get(i).toString() + "\n";

		}

		return "El estado de cuenta para la cuenta " + numCuenta + " \n" + " \n"
				+ "[Fecha, Transaccion, monto, comision] \n" + resultado + " \n" + " \n" + "Con un saldo de: " + saldo;

	}
	public String consultaEstadoCuentaCambio(int numCuenta) {
		Vector salida;
		String resultado="";
		double saldo=0;
		float cambioCompra=cambio.getCompra();		
		//BigDecimal bigDecimal = new BigDecimal(saldo).setScale(2, RoundingMode.UP);
		//System.out.println(bigDecimal.doubleValue());
		DecimalFormat df = new DecimalFormat("#.##");
		
		salida=operacionDAO.generarEstadoCuenta(numCuenta);
		saldo=(double) operacionDAO.confirmarSaldo(numCuenta);
		for(int i=0;i<salida.size();i++) {
			Vector dentro=(Vector) salida.get(i);
			for(int j=0;j<dentro.size();j++) {
				//if(j==3 || j==2) {
					int hola=(int) dentro.get(j);
					
					//System.out.println();
					double calculo=(double) hola/cambioCompra;
					String pal=String.valueOf(new BigDecimal(String.valueOf(calculo)).setScale(1, BigDecimal.ROUND_FLOOR));
					resultado+=pal;
				//}
				resultado+=dentro.get(j)+", ";
			}
			resultado+="\n";
			
		}
		
		return "El estado de cuenta para la cuenta "+numCuenta+" \n"
				+" \n"
				+"[Fecha, Transaccion, monto, comision] \n"
				+resultado+" \n"
				+" \n"
				+"Con un saldo de: "+saldo/cambioCompra;
	}
	public float consultarCambioCompra() {
		return cambio.getCompra();
	}

	public float consultarCambioVenta() {
		return cambio.getVenta();
	}
/*
	public int montoTotalComisionesDepositoRetiro() {
		int monto = 0;

		return monto + (montoTotalComisionesTotales() + montoTotalComisionesCuenta());
	}*/

	public int montoTotalComisionesTotales() {
		int monto = 0;
		for (int i = 0; i <= getOperaciones().size(); i++) {
			if (getOperaciones().get(i).getTipo().equals("Deposito")) {
				monto = (int) +getOperaciones().get(i).getMontoComision();
			}
		}
		return monto;
	}

	public int montoTotalComisionesCuenta() {
		int monto = 0;
		for (int i = 0; i <= getOperaciones().size(); i++) {
			if (getOperaciones().get(i).getTipo().equals("Retiro")) {
				monto = (int) +getOperaciones().get(i).getMontoComision();
			}
		}
		return monto;
	}

	// Valida que el monto a retirar sea menos o igual al saldo.
	public boolean validarMonto(int pMonto) {
		if (pMonto <= getSaldo()) {
			return false;
		} else {
			return true;
		}

	}

	public boolean esCuentaBloqueada() {
		if (getStatus().equals("Bloqueada")) {
			return true;
		} else {
			return false;
		}
	}
/*
	public void bloquearCuenta() {
		setStatus("Bloqueada");
	}
*/
	
	public String consultaCliente() {
		OperacionDAO dao = new OperacionDAO();
		
		Vector resultado = dao.consultarInfoPersonas();
		
		return "[Nombre,  primerApellido, segundoApellido, identificacion] \n"
				+resultado;
	}
	public String consultaClienteEspecifico(int cedula) {
		OperacionDAO dao = new OperacionDAO();
		
		Vector resultado = dao.detallesPersona(cedula);
		
		return "[NumeroCuenta,  NombreCompleto  ] \n"
				+resultado;
	}
	
	public String consultaCuentas() {
		OperacionDAO dao = new OperacionDAO();
		
		Vector resultado = dao.consultarInfoCuenta();
		
		return "[Cuenta, estatus , saldo ,   nombreCompleto   ] \n"
				+resultado;
	}
	
	
	public String consultaCuentaEspecifica(int numCuenta) {
		OperacionDAO dao = new OperacionDAO();
		
		Vector resultado = dao.consultarInfoCuentaEspecifica(numCuenta);
		
		return "[Cuenta, fecha , saldo , estatus , pin] \n"
				+resultado;
	}
	@Override
	public String toString() {
		return "Cuenta [fechaCreacion=" + fechaCreacion + ", saldo=" + saldo + ", status=" + status + ", pin=" + pin
				+ ", operaciones=" + operaciones + ", cantidadOperaciones=" + cantidadOperaciones + "]";
	}

}
