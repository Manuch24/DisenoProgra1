package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import vistaGUI.VistaComisiones;
import vistaGUI.VistaMenu;

public class ControladorComisiones implements ActionListener {

	private CuentaDAO cuentaDAO;
	private OperacionDAO operacionDAO;
	private VistaComisiones vista;
	
	public ControladorComisiones(VistaComisiones vista) {
		this.vista = vista;
		this.operacionDAO = new OperacionDAO();	
		this.cuentaDAO = new CuentaDAO();
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getCbxCuenta().addActionListener(this);
		this.vista.getBtnConsultar().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Realizar DConsultas Ganacias Deposito");
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
		cbx.addItem("All");
		cuentaDAO.listarCuentas(cbx);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vista.getBtnVolver()) {
			volver();
		}
		if(e.getSource() == vista.getBtnConsultar()) {
			
			if(vista.getCbxCuenta().getSelectedItem().toString().equals("All")) {
				
				if(vista.getCbxTipo().getSelectedItem().toString().equals("All")) {
					JOptionPane.showMessageDialog(null, "El monto total por comision es: "
							+ operacionDAO.totalDepositosRetiros());
				}
				if(vista.getCbxTipo().getSelectedItem().toString().equals("Deposito")) {
					JOptionPane.showMessageDialog(null, "El monto total por comision es: "
							+ operacionDAO.totalDepositos());
				}
				if(vista.getCbxTipo().getSelectedItem().toString().equals("Retiro")){
					JOptionPane.showMessageDialog(null, "El monto total por comision es: "
							+ operacionDAO.totalRetiros());
				}
			}
			else {
				
				if(vista.getCbxTipo().getSelectedItem().toString().equals("All")) {
					JOptionPane.showMessageDialog(null, "El monto total por comision es: "
							+ operacionDAO.totalDepositosRetirosCuenta(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())));
				}
				if(vista.getCbxTipo().getSelectedItem().toString().equals("Deposito")) {
					JOptionPane.showMessageDialog(null, "El monto total por comision es: "
							+ operacionDAO.totalDepositosCuenta(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())));
				}
				if (vista.getCbxTipo().getSelectedItem().toString().equals("Retiro")) {
					JOptionPane.showMessageDialog(null, "El monto total por comision es: "
							+ operacionDAO.totalRetiroCuenta(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())));
				}
				
				
				
			}
		}
	}

}
