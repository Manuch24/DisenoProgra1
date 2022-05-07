package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

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
		vista.setLocationRelativeTo(null);
		
	}
	
	public void llenarCbxCliente() {
		personaDAO.listarIdentificaciones(vista.getCbxCliente());
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
