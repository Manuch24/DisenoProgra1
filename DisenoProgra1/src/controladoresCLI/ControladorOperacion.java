package controladoresCLI;

import java.text.SimpleDateFormat;
import java.util.Date;

import Servicios.BCCRCambioMoneda;
import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import logicadenegocios.Operacion;

public class ControladorOperacion {
	CuentaDAO cuentaDAO = new CuentaDAO();
	OperacionDAO operacionDAO = new OperacionDAO();
	private BCCRCambioMoneda cambio = new BCCRCambioMoneda();
	
	private static int cantidadOperacionesGratis = 0;
	
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
		} 
		String respuesta = "Estimado usuario, el monto de este retiro es: " + saldo + ".00 colones \n"
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
	
	public String realizarTransferencia(int numCuenta,float monto, String pin) {
		if (!cuentaDAO.buscarPin(numCuenta).equals(pin)) {
			return "Pin incorrecto";
		}
		
		if(monto!= (int)monto) {
			return "Error en el formato.";
		}
		
		
		
		
		String res = "";
		
		
		
		return res;
		}
}
