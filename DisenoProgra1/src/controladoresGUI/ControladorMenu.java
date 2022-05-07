package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaMenu;
import vistaGUI.VistaPersona;

public class ControladorMenu implements ActionListener {

	private VistaPersona vistaPersona;
	private VistaCuenta vistaCuenta;
	private VistaMenu vista;
	
	
	private ControladorPersona controladorPersona;
	private ControladorCuenta controladorCuenta;
	
	public ControladorMenu (VistaMenu vista) {
	
		this.vista = vista;
		this.vista.getBtnRegistarCuenta().addActionListener(this);
		this.vista.getBtnRgistrarCliente().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Registrar Cliente");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == vista.getBtnRegistarCuenta()) {
			Cuenta modelo = new Cuenta();
			VistaCuenta vistaCuenta = new VistaCuenta();
			controladorCuenta = new ControladorCuenta(modelo, vistaCuenta);
			controladorCuenta.iniciar();
			vista.setVisible(false);
		}
		if(e.getSource() == vista.getBtnRgistrarCliente()) {
			Persona modelo = new Persona();
			PersonaDAO PersonaDAO = new PersonaDAO();
			VistaPersona vistaPersona = new VistaPersona();
			this.controladorPersona= new ControladorPersona(modelo, PersonaDAO, vistaPersona);
			controladorPersona.iniciar();
			vista.setVisible(false);
		
	}

}
}
