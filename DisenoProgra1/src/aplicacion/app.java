package aplicacion;

import consultasDAO.PersonaDAO;
import logicadenegocios.Persona;
import vistaGUI.VistaMenu;
import vistaGUI.VistaPersona;
import controladoresGUI.ControladorMenu;
import controladoresGUI.ControladorPersona;
public class app {

	public static void main(String[] args) {
		VistaMenu vista = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vista);
		controladorMenu.iniciar();
		
		
	}

}
