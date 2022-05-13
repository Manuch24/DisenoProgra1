package controladoresCLI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import Servicios.BCCRCambioMoneda;
import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import logicadenegocios.Operacion;

public class ControladorOperacion {
	CuentaDAO cuentaDAO = new CuentaDAO();
	OperacionDAO operacionDAO = new OperacionDAO();
	private BCCRCambioMoneda cambio = new BCCRCambioMoneda();
	
	private static int cantidadOperacionesGratis = 0;
	public static int cantOporu=3;
	public static float saldoAlmacenado=0;
	
	public String depositoColones(float montoDeposito, int numeroCuenta, OperacionDAO dao) {
		float saldo = 0;
		float montoComision = 0;
		boolean comision = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;

		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoDeposito * 0.02);
			saldo = montoDeposito - montoComision;
			comision = true;
		} else {
			saldo = montoDeposito;
		}

		Operacion op = new Operacion("deposito", date, comision, montoComision, saldo);
		dao.realizarDeposito(op, numeroCuenta);

		String salida = "Estimado usuario, se han depositado correctamente " + montoDeposito + ".00 colones \n"
				+ "[El monto real depositado a su cuenta " + numeroCuenta + " es de " + saldo + " colones] \n"
				+ "[El monto cobreado por concepto de comision fue de " + montoComision
				+ " colones, que fueron rebajados automáticamente de su saldo actual]";

		return salida;
	}

	public String depositoCambioMoneda(float montoDepositoDolares, int numeroCuenta, OperacionDAO dao) {

		float compraCambio = cambio.getVenta();
		float montoEnColones = 0;

		Date date = new Date();
		String formatter = new SimpleDateFormat("dd/MM/yyyy").format(date);

		montoEnColones = montoDepositoDolares * compraCambio;

		String deposito = depositoColones(montoEnColones, numeroCuenta, dao);

		String resultado = "Estimado usuario, se han recibido correctamente " + montoDepositoDolares + ".00 dólares \n"
				+ "[Según el BCCR, el tipo de cambio de compra del dólar de " + formatter + " es " + compraCambio
				+ "] \n" + "[El monto equivalente en colones es " + montoEnColones + "] \n" + deposito;

		return resultado;
	}

	public String retiroColones(float montoRetiro, int numeroCuenta, OperacionDAO dao) {
		// String respuesta="";
		float saldo = montoRetiro;
		float montoComision = 0;
		boolean comision = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;

		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoRetiro * 0.02);
			montoRetiro+=montoComision;
			comision = true;
			System.out.println("aqui");
		} 
		String respuesta = "Estimado usuario, el monto de este retiro es: " + saldo + " colones \n"
				+ "[El monto cobrado por concepto de comisión fue de: " + montoComision +" colones, que fueron rebajados automáticamente de su saldo actual]";

		if (dao.confirmarSaldo(numeroCuenta) >= (montoRetiro+montoComision)) {
			System.out.println("Entra aqui");
			Operacion op = new Operacion("retiro", date, comision, montoComision, montoRetiro);
			dao.realizarRetiro(op, numeroCuenta);
			
			return respuesta;
			
		} else {
			return "No se encuentran suficientes fondos en sus cuenta";
		}

		
		
	}
	
	public String retiroCompraMoneda(float montoRetiroDolares,int numeroCuenta,OperacionDAO dao) {
		float ventaCambio = cambio.getCompra();
		float montoEnColones = 0;
		float montoComision=0;

		Date date = new Date();
		String formatter = new SimpleDateFormat("dd/MM/yyyy").format(date);

		montoEnColones =  ventaCambio*montoRetiroDolares;
		cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;
		//System.out.println(montoEnColones);
		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoEnColones * 0.02);
			
		} 
		retiroColones(montoEnColones, numeroCuenta, dao);

		String resultado = "Estimado usuario, el monto de retiro es: " + montoRetiroDolares + " dólares \n"
				+ "[Según el BCCR, el tipo de cambio de compra del dólar de " + formatter + " es " + ventaCambio+"] \n" 
				+ "[El monto equivalente de su retiro " + montoEnColones + " colones] \n"
				+ "[El monto cobrado por concepto de comisión fue de "+montoComision+" colobes, que fueron rebajados automáticamente de sus saldo actual]";


		return resultado;
	}
	
	public String realizarTransferencia(int numCuentaRetiro,float monto, int numeroCuentaDestino , OperacionDAO dao) {
		
		
		if(monto!= (int)monto) {
			return "Error en el formato.";
		}
		
		String retiroTransferencia[] = retiroColones(monto,numCuentaRetiro,dao).split(" ");
		
		float montoExtraido = Float.parseFloat(retiroTransferencia[8]);
		float comisionExtraido = Float.parseFloat(retiroTransferencia[19]);
		
		
		
		depositoColonesTransferencia(montoExtraido,numeroCuentaDestino,dao);
		
		
		
		String res = "Estimado usuario, la transferencia de fondos se ejecutó satisfactoriamente.  \n"
				+"El monto retirado de la cuenta origen y depositado en la cuenta destino es "+monto+" colones.  \n"
				+"[El monto cobrado por concepto de comisión a la cuenta origen fue de "+comisionExtraido+" colones, que fueron rebajados automáticamente de su saldo actual]";
		
		return res;
		}
	
	
	public boolean depositoColonesTransferencia(float montoDeposito, int numeroCuenta, OperacionDAO dao) {
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		
		Operacion op = new Operacion("deposito", date, false, 0, montoDeposito);
		if(dao.realizarDeposito(op, numeroCuenta)) {
			return true;
		}

		return false;
		
	}
	
	public String inactivarCuenta(int numeroCuenta,OperacionDAO dao) {
		
		dao.InactivarCuenta(numeroCuenta);
		
		return "Se inactivó su cuenta";
	}
	
	
	public float consultarCambioCompra() {
		return cambio.getCompra();
	}
	
	public float consultarCambioVenta() {
		return cambio.getVenta();
	}
	
	public String consultaSaldoActual(int numCuenta, OperacionDAO dao) {
		
		saldoAlmacenado=dao.confirmarSaldo(numCuenta);
		
		
		return "Estimado usuario el saldo actual de su cuenta es: "+saldoAlmacenado+" colones.";
	}
	
	public String saldoActualEquivalente(int numCuenta, OperacionDAO dao) {
		double resultadoCambio=0;
		float cambioCompra=cambio.getCompra();
		
		consultaSaldoActual(numCuenta,dao);
		
		resultadoCambio=(double) (saldoAlmacenado/cambioCompra);
		
		return "Estimado usuario el saldo actual de su cuenta en "+resultadoCambio+" dólares \n"
				+"Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra \n"
				+"[Según el BCCR, el tipo de cambio de compra, del dólar de hoy es: "+cambioCompra+"]";
	}
	
	public String consultaEstadoCuenta(int numCuenta, OperacionDAO dao) {
		Vector salida;
		String resultado="";
		double saldo=0;
		
		salida=dao.generarEstadoCuenta(numCuenta);
		saldo=(double) dao.confirmarSaldo(numCuenta);
		for(int i=0;i<salida.size();i++) {
			resultado+=salida.get(i).toString()+ "\n";
			
		}
		
		return "El estado de cuenta para la cuenta "+numCuenta+" \n"
				+" \n"
				+"[Fecha, Transaccion, monto, comision] \n"
				+resultado+" \n"
				+" \n"
				+"Con un saldo de: "+saldo;
			
	}
	public String consultaEstadoCuentaCambio(int numCuenta,OperacionDAO dao) {
		Vector salida;
		String resultado="";
		double saldo=0;
		float cambioCompra=cambio.getCompra();		
		//BigDecimal bigDecimal = new BigDecimal(saldo).setScale(2, RoundingMode.UP);
		//System.out.println(bigDecimal.doubleValue());
		DecimalFormat df = new DecimalFormat("#.##");
		
		salida=dao.generarEstadoCuenta(numCuenta);
		saldo=(double) dao.confirmarSaldo(numCuenta);
		for(int i=0;i<salida.size();i++) {
			Vector dentro=(Vector) salida.get(i);
			for(int j=0;j<dentro.size();j++) {
				if(j==3 || j==2) {
					int hola=(int) dentro.get(j);
					
					//System.out.println();
					double calculo=(double) hola/cambioCompra;
					String pal=String.valueOf(new BigDecimal(String.valueOf(calculo)).setScale(1, BigDecimal.ROUND_FLOOR));
					resultado+=pal;
				}
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
	
	public String consultaEstatusCuenta(int numCuenta,OperacionDAO dao) {
		
		String resultado = dao.consultarEstatusCuenta(numCuenta);
		
		return "La cuenta número "+numCuenta+" tiene estatus "+resultado;
	}
	
	public String consultarGanaciasTotales() {
		OperacionDAO dao = new OperacionDAO();
		float resultado = dao.consultaComisionesTotales();
		
		return"El monto total cobrado por comisiones para todo el universo de cuentas es: "+resultado;
	}
	
	public String consultarGanaciasPorCuenta(int numCuenta) {
		OperacionDAO dao = new OperacionDAO();
		float resultado = dao.consultaComisionesPorCuenta(numCuenta);
		
		return"El monto total cobrado por comisiones para la cuenta "+numCuenta+" es: "+resultado;
	}
	
	
}
