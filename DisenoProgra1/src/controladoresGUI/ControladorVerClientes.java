package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;

import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaMenu;
import vistaGUI.VistaPersona;
import vistaGUI.VistaVerClientes;

public class ControladorVerClientes implements ActionListener {

	private VistaMenu vistaMenu;
	private VistaVerClientes vista;
	private PersonaDAO personaDAO;
	private CuentaDAO cuentaDAO;
	private Cuenta cuenta;
	private Persona persona;
		
	
	public ControladorVerClientes(VistaVerClientes vista ) {
		this.vista = vista;
		this.persona = new Persona();
		this.cuenta = new Cuenta();
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getBtnCargar().addActionListener(this);
		
	}
	public void iniciar() {
		vista.setTitle("Ver Clientes");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}
	
	public void listarClientes(JTable tabla) throws SQLException {
		personaDAO.listarClientes(tabla);
	}
	
	public void volver() {
		vista.setVisible(false);
		VistaMenu vistaMenu = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
		controladorMenu.iniciar();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==  vista.getBtnVolver()) {
			volver();
		}
		if(e.getSource() ==  vista.getBtnCargar()) {
			try {
				listarClientes(vista.getTableClientes());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
}
