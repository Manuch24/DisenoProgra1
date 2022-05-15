package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import consultasDAO.CuentaDAO;
import vistaGUI.VistaMenu;
import vistaGUI.VistaVerEstatus;

public class ControladorVerEstatus implements ActionListener{

	private VistaVerEstatus vista;
	private CuentaDAO cuentaDAO;
	
	public ControladorVerEstatus (VistaVerEstatus vista) {
		this.vista = vista;
		this.cuentaDAO = new CuentaDAO();
		this.vista.getBtnEstatus().addActionListener(this);
		this.vista.getBtnvolver().addActionListener(this);
	}
	


	public void listarCuentas(JComboBox cbx) {
		cuentaDAO.listarCuentas(cbx);
	}

	public void iniciar() {
		vista.setTitle("Ver estatus cuenta");
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getBtnvolver()) {
			volver();
		}
		if (e.getSource() == vista.getBtnEstatus()) {
			String estatus = cuentaDAO.esActiva(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()));
			JOptionPane.showMessageDialog(null, "“La cuenta número " +vista.getCbxCuenta().getSelectedItem().toString()  + " "
					+ "tiene estatus de " + estatus );
		}
	}

}
