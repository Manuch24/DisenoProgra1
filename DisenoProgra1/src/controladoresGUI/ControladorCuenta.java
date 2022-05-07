package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Validaciones.Validaciones;
import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaPersona;

public class ControladorCuenta implements ActionListener{

	private Cuenta modelo;
	private CuentaDAO cuentaDAO;
	private PersonaDAO personaDAO; 
	private VistaCuenta vista;

	
	public ControladorCuenta (Cuenta modelo, CuentaDAO cuentaDAO, VistaCuenta vista) {
		this.modelo = modelo;
		this.cuentaDAO = cuentaDAO;
		this.vista = vista;	
		this.personaDAO = new PersonaDAO();
		this.vista.getCbxCliente().addActionListener(this);
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (Validaciones.validarPinRegex(vista.getTxtPin().getText()) && 
				modelo.validarMonto(Integer.parseInt(vista.getTxtMontoInicial().getText()))  == true) {
			if(e.getSource() == vista.getBtnRegistrarCuenta()) {
				modelo.setPin(vista.getTxtPin().);
				SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
				String format = simple.format(vista.getDateChooserNacimiento().getDate());
				modelo.setFechaNacimiento(format); 
			
				
				
				if(cuentaDAO.registrar(modelo, format)) {
					JOptionPane.showMessageDialog(null, "Registro Guardado");
				}else {
					JOptionPane.showMessageDialog(null, "Error al guardar");
				}
				limpiar();
			}
		}else {
			if(Validaciones.validarEmailRegex(vista.getTxtEmail().getText()) ==false) {
				JOptionPane.showMessageDialog(null, "Error en el formato Email","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}else if(Validaciones.validarTelefonoRegex(Integer.parseInt(vista.getTxtNumTelefono().getText())) ==false) {
				JOptionPane.showMessageDialog(null, "Error en el formato telefono","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

}
