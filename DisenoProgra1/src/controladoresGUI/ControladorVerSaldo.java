package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Servicios.BCCRCambioMoneda;
import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaMenu;
import vistaGUI.VistaVerSaldo;

public class ControladorVerSaldo implements ActionListener{

private CuentaDAO cuentaDAO;
private VistaVerSaldo vista;
private int contIntentos;
private BCCRCambioMoneda bccrCambioMoneda;

	
	public ControladorVerSaldo (VistaVerSaldo vista) {
		this.bccrCambioMoneda = new BCCRCambioMoneda();
		this.contIntentos = 0;
		this.vista = vista;	
		this.cuentaDAO = new CuentaDAO();
		this.vista.getBtnVerSaldo().addActionListener(this);
		this.vista.getBtnVolver().addActionListener(this);
	}
	
	public void iniciar() {
		vista.setTitle("Ver Saldo cuenta");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		listarCuentas(vista.getCbxCuenta());
		
	}
	
	public void listarCuentas(JComboBox cbx) {
		cuentaDAO.listarCuentas(cbx);
	}
	
	public void limpiar() {
		vista.getTextPIN().setText("");
	}
	
	public void volver() {
		vista.setVisible(false);
		VistaMenu vistaMenu = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
		controladorMenu.iniciar();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getBtnVerSaldo()) {
			if(cuentaDAO.esActiva(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())).equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "La cuenta seleccionada esta inactiva","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);


			}else {


				if(!vista.getTextPIN().getText().equals(cuentaDAO.buscarPin(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())))) {
					JOptionPane.showMessageDialog(null, "El pin ingresdo de la cuenta\nno es el mismo. Solo tiene un intento más","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					contIntentos +=1;

					if (contIntentos == 2) {
						JOptionPane.showMessageDialog(null, "Por realizar 2 intentos inválidos, su cuenta se acaba de BLOQUEAR","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
						try {
							cuentaDAO.bloquearCuenta(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()));
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					if (vista.getCbxMoneda().getSelectedItem().toString().equals("Colones")) {
						int saldo = cuentaDAO.obtenerSaldo(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()));
						JOptionPane.showMessageDialog(null,"Estimado usuario el saldo actual de su cuenta es:"+saldo + ".00 colones");
					}
					else {
						int saldo = cuentaDAO.obtenerSaldo(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()));
						saldo = (int) (saldo / bccrCambioMoneda.getCompra());
						JOptionPane.showMessageDialog(null,"Estimado usuario el saldo actual de su cuenta es:"+saldo + ".00 dolares\n"
								+ "Para esta conversión se utilizó el tipo de cambio del dólar, precio de compra.\n"
								+ "[Según el BCCR, el tipo de cambio de compra del dólar de hoy es:" + bccrCambioMoneda.getCompra());

					}
				}
			}

		}

	}

}
