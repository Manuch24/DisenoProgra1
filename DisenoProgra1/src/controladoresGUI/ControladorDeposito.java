package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaDeposito;
import vistaGUI.VistaMenu;
import vistaGUI.VistaVerClientes;

public class ControladorDeposito implements ActionListener {


	private VistaMenu vistaMenu;
	private VistaDeposito vista;
	private PersonaDAO personaDAO;
	private CuentaDAO cuentaDAO;
	private Cuenta cuenta;
	private Persona persona;
		
	
	public ControladorDeposito(VistaDeposito vista ) {
		this.vista = vista;
		this.persona = new Persona();
		this.cuenta = new Cuenta();
		this.cuentaDAO = new CuentaDAO();
		this.personaDAO = new PersonaDAO();
		this.v
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
