package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Servicios.BCCRCambioMoneda;
import Servicios.envioSMS;
import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import vistaGUI.VistaDeposito;
import vistaGUI.VistaMenu;
import vistaGUI.VistaRetiro;

public class ControladorRetirar implements ActionListener {


	private VistaMenu vistaMenu;
	private VistaRetiro vista;
	private PersonaDAO personaDAO;
	private CuentaDAO cuentaDAO;
	private Cuenta cuenta;
	private Persona persona;
	private Operacion operacion;
	private OperacionDAO operacionDAO;
	private BCCRCambioMoneda cambioMoneda;
	private envioSMS envioSMS;
	private int contIntentos;
	private String msj;




	public ControladorRetirar(VistaRetiro vista ) {
		this.cambioMoneda = new BCCRCambioMoneda();
		this.msj = "";
		this.vista = vista;
		this.persona = new Persona();
		this.cuenta = new Cuenta();
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.cuenta = new Cuenta();
		this.cambioMoneda = new BCCRCambioMoneda();
		this.cuentaDAO = new CuentaDAO();
		this.operacionDAO = new OperacionDAO();
		this.envioSMS = new envioSMS();
		this.vista.getBtnRetirar().addActionListener(this);
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getBtnEnviarSMS().addActionListener(this);
		this.vista.getCbxCuentaOrigen().addActionListener(this);
		contIntentos = 0;
	}

	public void iniciar() {
		vista.setTitle("Realizar Deposito");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		listarCuentas(vista.getCbxCuentaOrigen());
		vista.getBtnRetirar().setVisible(false);
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
	
	public void listarcuentasDestino(JComboBox cbx) {
		cuentaDAO.listarCuentas(cbx);
	}
	

	public void retiroColones(float montoRetiro, int numeroCuenta, OperacionDAO dao) {
		// String respuesta="";
		float saldo = montoRetiro;
		float montoComision = 0;
		boolean comision = false;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		// formatter.format(date);

		int cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;

		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoRetiro * 0.02);
			montoRetiro+=montoComision;
			comision = true;
			System.out.println("aqui");
		} 
	

		if (dao.confirmarSaldo(numeroCuenta) >= (montoRetiro+montoComision)) {
			System.out.println("Entra aqui");
			Operacion op = new Operacion("retiro", date, comision, montoComision, montoRetiro);
			dao.realizarRetiro(op, numeroCuenta);
			
			if (vista.getCbxMoneda().getSelectedItem().toString().equals("Colones")) {
				
			
			JOptionPane.showMessageDialog(null, "Estimado usuario, el monto de este retiro es: " + saldo + " colones \n"
					+ "[El monto cobrado por concepto de comisión fue de: " + montoComision +" colones, que fueron rebajados automáticamente de su saldo actual]");
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se encuentran fondos suficientes dentro de su cuenta","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void retiroCompraMoneda(float montoRetiroDolares,int numeroCuenta,OperacionDAO dao) {
		float ventaCambio = cambioMoneda.getCompra();
		float montoEnColones = 0;
		float montoComision=0;

		Date date = new Date();
		String formatter = new SimpleDateFormat("dd/MM/yyyy").format(date);

		montoEnColones =  ventaCambio*montoRetiroDolares;
		int cantidadOperacionesGratis = dao.verificarCantTransaccionesGratis(numeroCuenta) + 1;
		//System.out.println(montoEnColones);
		if (cantidadOperacionesGratis > 3) {
			montoComision = (float) (montoEnColones * 0.02);
			
		} 
		retiroColones(montoEnColones, numeroCuenta, dao);
		
		JOptionPane.showMessageDialog(null, "Estimado usuario, el monto de retiro es: " + montoRetiroDolares + " dólares \n"
				+ "[Según el BCCR, el tipo de cambio de compra del dólar de " + formatter + " es " + ventaCambio+"] \n" 
				+ "[El monto equivalente de su retiro " + montoEnColones + " colones] \n"
				+ "[El monto cobrado por concepto de comisión fue de "+montoComision+" colobes, que fueron rebajados automáticamente de sus saldo actual]");	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getCbxCuentaOrigen()) {
			contIntentos = 0;
		}

		if (e.getSource() == vista.getBtnVolver()) {
			volver();
		}

		if (e.getSource() == vista.getBtnEnviarSMS()) {

			if (cuentaDAO.esActiva(Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString())).equals("Inactivo")) {
				JOptionPane.showMessageDialog(null, "La cuenta seleccionada esta inactiva","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}else {

				if(!vista.getTxtPIN().getText().equals(cuentaDAO.buscarPin(Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString())))) {
					JOptionPane.showMessageDialog(null, "El pin ingresdo de la cuenta\nno es el mismo. Solo tiene un intento más","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					contIntentos +=1;
					
					if (contIntentos == 2) {
						JOptionPane.showMessageDialog(null, "Por realizar 2 intentos inválidos, su cuenta se acaba de BLOQUEAR","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
						try {
							cuentaDAO.bloquearCuenta(Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString()));
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
					msj = envioSMS.generarCodigoVerificacion();
					envioSMS.enviarSMS(personaDAO.numeroTelefono(Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString())),msj);
					vista.getBtnRetirar().setVisible(true);
					contIntentos = 0;
				}
			}

		}
		
		if(e.getSource() == vista.getBtnRetirar()) {
		
			if (!vista.getTxtPalabra().getText().equals(msj)) {
				JOptionPane.showMessageDialog(null, "La palabra ingresada no es la misma, se acaba de enviar otra\n"
						+ "Posee un intento más","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
				// se envia de nuevo el msj
				msj = envioSMS.generarCodigoVerificacion();
				envioSMS.enviarSMS(personaDAO.numeroTelefono(Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString())),msj);
				contIntentos +=1;
				if (contIntentos == 2) {
					JOptionPane.showMessageDialog(null, "Por realizar 2 intentos inválidos, su cuenta se acaba de BLOQUEAR","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					try {
						cuentaDAO.bloquearCuenta(Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString()));
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}else {
				if(cuenta.validarMonto(Integer.parseInt(vista.getTxtMonto().getText())) ==false) {
					JOptionPane.showMessageDialog(null, "Error: Solo numeros positivos en el saldo incial","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					vista.setVisible(false);
					iniciar();
				}else {
					if(vista.getCbxMoneda().getSelectedItem().toString().equals("Colones")) {
						retiroColones(Float.parseFloat(vista.getTxtMonto().getText()), Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString()) , operacionDAO);
					}
					else {
						retiroCompraMoneda(Float.parseFloat(vista.getTxtMonto().getText()), Integer.parseInt(vista.getCbxCuentaOrigen().getSelectedItem().toString()) , operacionDAO);
						vista.setVisible(false);
						iniciar();
						
					}
			
				}
			}
		}
		
//		if (e.getSource() == vista.getBtnRetirar()   ) {
//			if(cuenta.validarMonto(Integer.parseInt(vista.getTxtMonto().getText())) ==false) {
//				JOptionPane.showMessageDialog(null, "Error: Solo numeros positivos en el saldo incial","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
//			}
//			if(cuentaDAO.esActiva(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())).equals("Inactivo")) {
//				JOptionPane.showMessageDialog(null, "Error: Su cuenta es inactiva","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
//
//			}
//			else {
//				if(vista.getCbxMoneda().getSelectedItem().toString().equals("Colones")) {
//					depositoColones(Float.parseFloat(vista.getTxtMonto().getText()), 
//							Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())
//							, operacionDAO);
//
//				}
//				else {
//					depositoCambioMoneda(Float.parseFloat(vista.getTxtMonto().getText()), 
//							Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()), 
//							operacionDAO);
//
//
//				}
//
//
//			}
//		}
	}
}
