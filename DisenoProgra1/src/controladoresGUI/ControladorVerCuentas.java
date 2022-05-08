package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;

import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaMenu;
import vistaGUI.VistaVerClientes;
import vistaGUI.VistaVerCuentas;

public class ControladorVerCuentas implements ActionListener {

	private VistaMenu vistaMenu;
	private VistaVerCuentas vista;
	private PersonaDAO personaDAO;
	private CuentaDAO cuentaDAO;
	private Cuenta cuenta;

		
	
	public ControladorVerCuentas(VistaVerCuentas vista ) {
		this.vista = vista;
		this.cuenta = new Cuenta();
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getBtnCargar().addActionListener(this);
		this.vista.getBtnVerDetalles().addActionListener(this);
		
	}
	public void iniciar() {
		vista.setTitle("Ver Clientes");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}
	
	public void listarClientes(JTable tabla) throws SQLException {
		cuentaDAO.consultarCuentasInfo(tabla);
	}
	
	public void volver() {
		vista.setVisible(false);
		VistaMenu vistaMenu = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
		controladorMenu.iniciar();
	}
	
	public void verDetalles() {
		cuentaDAO.detallesCuenta(vista.getTableCuentas());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==  vista.getBtnVolver()) {
			volver();
		}
		if(e.getSource() ==  vista.getBtnCargar()) {
			try {
				listarClientes(vista.getTableCuentas());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == vista.getBtnVerDetalles()) {
			verDetalles();
		}
		
	}
}
