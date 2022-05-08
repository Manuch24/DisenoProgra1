package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Validaciones.Validaciones;
import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaMenu;
import vistaGUI.VistaPersona;

public class ControladorCuenta implements ActionListener{

	private Cuenta modelo;
	private PersonaDAO personaDAO; 
	private VistaCuenta vista;
	private CuentaDAO cuentaDAO;

	
	public ControladorCuenta (Cuenta modelo, VistaCuenta vista) {
		this.modelo = modelo;
		this.vista = vista;	
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.vista.getCbxCliente().addActionListener(this);
		this.vista.getBtnRegistrarCuenta().addActionListener(this);
		this.vista.getBtnVolver().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Registrar Cuenta");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		llenarCbxCliente();
		
	}
	
	public void llenarCbxCliente() {
		personaDAO.listarIdentificaciones(vista.getCbxCliente());
	}
	
	public void limpiar() {
		vista.getTxtMontoInicial().setText("");
		vista.getTxtPin().setText("");
	}
	
	public void volver() {
		vista.setVisible(false);
		VistaMenu vistaMenu = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
		controladorMenu.iniciar();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (Validaciones.validarPinRegex(vista.getTxtPin().getText()) ==true && 
				modelo.validarMonto(Integer.parseInt(vista.getTxtMontoInicial().getText()))  == true) {
			if(e.getSource() == vista.getBtnRegistrarCuenta() ) {
				modelo.setPin(vista.getTxtPin().getText());
				LocalDate date = java.time.LocalDate.now();
				modelo.setFechaCreacion(date.toString());
				modelo.setSaldo(Integer.parseInt(vista.getTxtMontoInicial().getText()));
				modelo.setStatus("Activo");
				
				if(cuentaDAO.registrar(modelo, Integer.parseInt(vista.getCbxCliente().getSelectedItem().toString()))) {
					
					JOptionPane.showMessageDialog(null, "Se han guardado");
				}else {
					JOptionPane.showMessageDialog(null, "Error al guardar");
				}
				limpiar();



			}
			
			

		}else {
			if(Validaciones.validarPinRegex(vista.getTxtPin().getText()) ==false) {
				JOptionPane.showMessageDialog(null, "Error en el formato del pin","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}else if(modelo.validarMonto(Integer.parseInt(vista.getTxtMontoInicial().getText())) ==false) {
				JOptionPane.showMessageDialog(null, "Error: Solo numeros positivos en el saldo incial","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == vista.getBtnVolver()) {
			volver();
		}

	}
}
