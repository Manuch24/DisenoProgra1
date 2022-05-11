package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Servicios.BCCRCambioMoneda;
import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import vistaGUI.VistaDeposito;
import vistaGUI.VistaMenu;
import vistaGUI.VistaVerClientes;

public class ControladorDeposito implements ActionListener {


	private VistaMenu vistaMenu;
	private VistaDeposito vista;
	private PersonaDAO personaDAO;
	private CuentaDAO cuentaDAO;
	private Cuenta cuenta;
	private Persona persona;
	private Operacion operacion;
	private OperacionDAO operacionDAO;
	private BCCRCambioMoneda cambioMoneda;
	
		
	
	public ControladorDeposito(VistaDeposito vista ) {
		this.vista = vista;
		this.persona = new Persona();
		this.cuenta = new Cuenta();
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.cuenta = new Cuenta();
		this.cambioMoneda = new BCCRCambioMoneda();
		this.cuentaDAO = new CuentaDAO();
		this.operacionDAO = new OperacionDAO();	
		this.vista.getBtnDepositar().addActionListener(this);
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getCbxCuenta().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Realizar Deposito");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		listarCuentas(vista.getCbxCuenta());
		
	}
	
	
	public void volver() {
		vista.setVisible(false);
		VistaMenu vistaMenu = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
		controladorMenu.iniciar();
	}
	
	public void listarCuentas(JComboBox cbx) {
		cuentaDAO.listarCuentas(cbx);
	}
	
	public  void depositoColones(float montoDeposito, int numeroCuenta, OperacionDAO dao) {
		float saldo = 0;
		float montoComision = 0;
		boolean comision = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		int cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;

		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoDeposito * 0.02);
			saldo = montoDeposito - montoComision;
			comision = true;
		} else {
			saldo = montoDeposito;
		}

		Operacion op = new Operacion("deposito", date, comision, montoComision, saldo);
		dao.realizarDeposito(op, numeroCuenta);
		JOptionPane.showMessageDialog(null, "Estimado usuario, se han depositado correctamente " + montoDeposito + ".00 colones \n"
				+ "[El monto real depositado a su cuenta " + numeroCuenta + " es de " + saldo + " colones] \n"
				+ "[El monto cobreado por concepto de comision fue de " + montoComision
				+ " colones, que fueron rebajados automáticamente de su saldo actual]");
		 
	}
	
	public void depositoCambioMoneda(float montoDeposito, int numeroCuenta, OperacionDAO dao) {
		float saldo = 0;
		float montoComision = 0;
		boolean comision = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		Calendar fecha = Calendar.getInstance();
		fecha.getTimeInMillis();
		// formatter.format(date);
		float compraCambio = cambioMoneda.getVenta();
		int cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;
		montoDeposito = montoDeposito*compraCambio;
		
		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoDeposito * 0.02);
			saldo = montoDeposito - montoComision;
			comision = true;
		} else {
			saldo = montoDeposito;
		}

		Operacion op = new Operacion("deposito", date, comision, montoComision, saldo);
		dao.realizarDeposito(op, numeroCuenta);
		JOptionPane.showMessageDialog(null, "Estimado usuario, se han depositado correctamente " + montoDeposito + ".00 dolares \n"
				+ "[Según el BCCR, el tipo de cambio de compra del dólar de "+ fecha.getTimeInMillis() +" es: " + compraCambio +"\n"
				+ "[El monto equivalente en colones es: " + montoDeposito+"\n"
				+ "[El monto real depositado a su cuenta " + numeroCuenta + " es de " + saldo + " \n"
				+ "[El monto cobreado por concepto de comision fue de " + montoComision
				+ " colones, que fueron rebajados automáticamente de su saldo actual]");

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getBtnVolver()) {
			volver();
	}
		if (e.getSource() == vista.getBtnDepositar()   ) {
			if(cuenta.validarMonto(Integer.parseInt(vista.getTxtMonto().getText())) ==false) {
				JOptionPane.showMessageDialog(null, "Error: Solo numeros positivos en el saldo incial","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(vista.getCbxMoneda().getSelectedItem().toString().equals("Colones")) {
					depositoColones(Float.parseFloat(vista.getTxtMonto().getText()), 
							Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())
							, operacionDAO);
					
				}
				else {
					depositoCambioMoneda(Float.parseFloat(vista.getTxtMonto().getText()), 
							Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()), 
							operacionDAO);

					
				}
				
			
			}
		}
}
}
