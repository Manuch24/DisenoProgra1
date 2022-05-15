package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Servicios.BCCRCambioMoneda;
import consultasDAO.CuentaDAO;
import consultasDAO.OperacionDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import vistaGUI.VistaDeposito;
import vistaGUI.VistaEstadoCuenta;
import vistaGUI.VistaMenu;

public class ControladorVerEstadoCuenta implements ActionListener{

	private VistaEstadoCuenta vista;
	private CuentaDAO cuentaDAO;

	private Operacion operacion;
	private OperacionDAO operacionDAO;
	private BCCRCambioMoneda cambioMoneda;
	
		
	
	public ControladorVerEstadoCuenta (VistaEstadoCuenta vista ) {
		this.vista = vista;
		this.cambioMoneda = new BCCRCambioMoneda();
		this.cuentaDAO = new CuentaDAO();
		this.cambioMoneda = new BCCRCambioMoneda();
		this.cuentaDAO = new CuentaDAO();
		this.operacionDAO = new OperacionDAO();	
		this.vista.getBtnVolver().addActionListener(this);
		this.vista.getCbxCuenta().addActionListener(this);
		this.vista.getBtnCargarEstado().addActionListener(this);
	}
	public void listarCuentas(JComboBox cbx) {
		cuentaDAO.listarCuentas(cbx);
	}

	public void iniciar() {
		vista.setTitle("Ver Estado de Ceunta");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		listarCuentas(vista.getCbxCuenta());
		vista.getLblSaldoActual().setVisible(false);
	}
	
	public void volver() {
		vista.setVisible(false);
		VistaMenu vistaMenu = new VistaMenu();
		ControladorMenu controladorMenu = new ControladorMenu(vistaMenu);
		controladorMenu.iniciar();
	}
	public void verDetalles() throws NumberFormatException, SQLException {
		operacionDAO.consultarEstadoCuenta(vista.getTableOperaciones(), 
				Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()));
	}	
	
	public void verDetallesDolares() throws NumberFormatException, SQLException {
		operacionDAO.consultarEstadoCuentaDolares(vista.getTableOperaciones(), 
				Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vista.getBtnCargarEstado()) {

			if(cuentaDAO.esActiva(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString())).equals("Inactivo")){
				JOptionPane.showMessageDialog(null, "La cuenta seleccionada esta inactiva","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}else{
				if(!vista.getCbxMoneda().getSelectedItem().toString().equals("Dolares")) {

					vista.getLblSaldoActual().setVisible(true);
					vista.getLblSaldoActual().setText("Saldo actual de la cuenta: "+ 
							cuentaDAO.obtenerSaldo(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()))+ " colones");
					try {
						verDetalles();
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
				vista.getLblSaldoActual().setText("Saldo actual de la cuenta: " + 
						cuentaDAO.obtenerSaldo(Integer.parseInt(vista.getCbxCuenta().getSelectedItem().toString()))/cambioMoneda.getCompra() + " dolares");
						try {
							verDetallesDolares();
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		}
		if (e.getSource() == vista.getBtnVolver()) {
			volver();
		}
	}

}
