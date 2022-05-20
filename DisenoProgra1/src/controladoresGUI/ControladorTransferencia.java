package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Servicios.BCCRCambioMoneda;
import Servicios.envioSMS;
import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import consultasDAO.PersonaDAO;
import controladoresCLI.ControladorOperacion;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import vistaGUI.VistaDeposito;
import vistaGUI.VistaMenu;
import vistaGUI.VistaTransferencia;

public class ControladorTransferencia implements ActionListener{
	
	private VistaMenu vistaMenu;
	private VistaTransferencia vista;
	private PersonaDAO personaDAO;
	private CuentaDAO cuentaDAO;
	private Cuenta cuenta;
	private Persona persona;
	private Operacion operacion;
	private OperacionDAO operacionDAO;
	private BCCRCambioMoneda cambioMoneda;
	private ControladorOperacion Ctrloperacion;
	private int contIntentos;
	private String msj;
	private envioSMS envioSMS;
	
	public ControladorTransferencia(VistaTransferencia vista) {
		this.vista = vista;
		this.persona = new Persona();
		this.cuenta = new Cuenta();
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.cuenta = new Cuenta();
		this.cambioMoneda = new BCCRCambioMoneda();
		this.cuentaDAO = new CuentaDAO();
		this.operacionDAO = new OperacionDAO();	
		this.Ctrloperacion=new ControladorOperacion();
		this.msj = "";
		this.envioSMS = new envioSMS();
		contIntentos = 0;
		this.vista.getBtnTransferir().addActionListener(this);
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getCbxCuentas().addActionListener(this);
		this.vista.getBtnSMS().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Realizar Transferencias");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		listarCuentas(vista.getCbxCuentas());
		vista.getBtnTransferir().setVisible(false);
		
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==vista.getBtnVolver()) {
			volver();
		}else
		if(e.getSource()==vista.getBtnSMS()) {
			if (cuentaDAO.esActiva(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString())).equals("Inactivo")) {
				JOptionPane.showMessageDialog(null, "La cuenta seleccionada esta inactiva","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}else {

				if(!vista.getTxtPin().getText().equals(cuentaDAO.buscarPin(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString())))) {
					JOptionPane.showMessageDialog(null, "El pin ingresdo de la cuenta\nno es el mismo. Solo tiene un intento más","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					contIntentos +=1;
					
					if (contIntentos == 2) {
						JOptionPane.showMessageDialog(null, "Por realizar 2 intentos inválidos, su cuenta se acaba de BLOQUEAR","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
						try {
							cuentaDAO.bloquearCuenta(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString()));
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
					msj = envioSMS.generarCodigoVerificacion();
					envioSMS.enviarSMS(personaDAO.numeroTelefono(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString())),msj);
					vista.getBtnTransferir().setVisible(true);
					contIntentos = 0;
				}
			}
		}else if(e.getSource()==vista.getBtnTransferir()) {
			if (!vista.getTxtSMSCodigo().getText().equals(msj)) {
				JOptionPane.showMessageDialog(null, "La palabra ingresada no es la misma, se acaba de enviar otra\n"
						+ "Posee un intento más","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
				// se envia de nuevo el msj
				msj = envioSMS.generarCodigoVerificacion();
				envioSMS.enviarSMS(personaDAO.numeroTelefono(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString())),msj);
				contIntentos +=1;
				if (contIntentos == 2) {
					JOptionPane.showMessageDialog(null, "Por realizar 2 intentos inválidos, su cuenta se acaba de BLOQUEAR","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					try {
						cuentaDAO.bloquearCuenta(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString()));
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				if(cuenta.validarMonto(Integer.parseInt(vista.getTxtMonto().getText())) ==false) {
					JOptionPane.showMessageDialog(null, "Error: Solo numeros positivos en el saldo incial","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					//vista.setVisible(false);
					//iniciar();
					vista.getTxtMonto().setText("");
				}else {
					if(cuentaDAO.esActiva(Integer.parseInt(vista.getTxtDestino().getText())).equals("Inactivo")) {
						JOptionPane.showMessageDialog(null, "La cuenta seleccionada esta inactiva","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					}else {
						String resultado = Ctrloperacion.realizarTransferencia(Integer.parseInt(vista.getCbxCuentas().getSelectedItem().toString()), Float.parseFloat(vista.getTxtMonto().getText()), Integer.parseInt(vista.getTxtDestino().getText()), operacionDAO);
						JOptionPane.showMessageDialog(null, resultado);
					}
					
					//if(vista. .getSelectedItem().toString().equals("Colones")) {
					//retiroColones(Float.parseFloat(vista.getTxtMonto().getText()), Integer.parseInt(vista.getSelectedItem().toString()) , operacionDAO);
					//}
				}
			}
		}
	}
}
