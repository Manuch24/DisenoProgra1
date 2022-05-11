package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import vistaGUI.VistaCambiarPin;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaMenu;
import Validaciones.Validaciones;

public class ControladorCambiarPIN implements ActionListener{
	private Cuenta cuenta;
	private PersonaDAO personaDAO; 
	private VistaCambiarPin vista;
	private CuentaDAO cuentaDAO;
	

	
	public  ControladorCambiarPIN (VistaCambiarPin vista) {
		this.cuenta = cuenta;
		this.vista = vista;	
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.vista.getBtnCambiarPIN().addActionListener(this);
		this.vista.getBtnVolver().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Registrar Cuenta");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);	
		listarCuentas(vista.getCbxCuenta());

	}
	
	
	public void limpiar() {
		vista.getTxtActualPIN().setText("");
		vista.getTxtNuevoPIN().setText("");
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
		}
		if(e.getSource()==vista.getBtnCambiarPIN()) {
			if(vista.getTxtActualPIN().getText().equals(cuentaDAO.buscarPin(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())))) {
//				System.out.print("PIN ACTUAL"+vista.getTxtActualPIN());
//				JOptionPane.showMessageDialog(null, "El pin actual ingresado no es el mismo","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
				if (Validaciones.validarPinRegex(vista.getTxtNuevoPIN().getText()) == true) {
					try {
						cuentaDAO.actualizarPin(vista.getTxtNuevoPIN().getText(),  
								Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())) ;
						JOptionPane.showMessageDialog(null, "Estimado usuario, se ha cambiado satisfactoriamente el PIN de su cuenta "+vista.getCbxCuenta().getSelectedItem().toString());
						limpiar();
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "El nuevo PIN no cumple el formato","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);

				}
			}else {
				JOptionPane.showMessageDialog(null, "El actual pin no es el mismo","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
				
			}
				
					
		}
		
	}
	
	

}
