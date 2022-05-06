package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;

import consultasDAO.PersonaDAO;
import logicadenegocios.Persona;
import vistaGUI.VistaPersona;

public class ControladorPersona implements ActionListener{
	
	private Persona modelo;
	private PersonaDAO modeloConsultas;
	private VistaPersona vista;
	
	public ControladorPersona (Persona modelo, PersonaDAO modeloConsultas, VistaPersona vista) {
		this.modelo = modelo;
		this.modeloConsultas = modeloConsultas;
		this.vista = vista;
		this.vista.getBtnRegistrarPersona().addActionListener(this);	
	}
	
	public void iniciar() {
		vista.setTitle("Registrar Cliente");
		vista.setLocationRelativeTo(null);
	}
	
	public void limpiar() {
		vista.getTxtCedula().setText("");
		vista.getTxtEmail().setText("");
		vista.getTxtNombre().setText("");
		vista.getTxtNumTelefono().setText("");
		vista.getTxtPrimerApellid().setText("");
		vista.getTxtSegundoApellido().setText("");
		vista.getDateChooserNacimiento().setDate(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vista.getBtnRegistrarPersona()) {
			modelo.setIdentificacion(Integer.parseInt(vista.getTxtCedula().getText()));
			modelo.setNombreCliente(vista.getTxtNombre().getText());
			modelo.setPrimerApellido(vista.getTxtPrimerApellid().getText());
			modelo.setSegundoApellido(vista.getTxtSegundoApellido().getText());
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			String format = simple.format(vista.getDateChooserNacimiento().getDate());
			modelo.setFechaNacimiento(format); 
			modelo.setNumeroTelefono(Integer.parseInt(vista.getTxtNumTelefono().getText()));
			modelo.setEmail(vista.getTxtEmail().getText());
			
			if(modeloConsultas.registrar(modelo, format)) {
				JOptionPane.showMessageDialog(null, "Registro Guardado");
			}else {
				JOptionPane.showMessageDialog(null, "Error al guardar");
			}
			limpiar();
		}
	}

}
