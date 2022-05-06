package aplicacion;

import consultasDAO.PersonaDAO;
import logicadenegocios.Persona;
import vistaGUI.VistaPersona;
import controladoresGUI.ControladorPersona;
public class app {

	public static void main(String[] args) {
		Persona modelo = new Persona();
		PersonaDAO modeloConsultas = new PersonaDAO();
		VistaPersona vista = new VistaPersona();
		
		ControladorPersona controladorPersona = new ControladorPersona(modelo, modeloConsultas, vista);
		controladorPersona.iniciar();
		vista.setVisible(true);

	}

}
