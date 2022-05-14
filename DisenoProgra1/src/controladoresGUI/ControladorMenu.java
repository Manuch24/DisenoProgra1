package controladoresGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Servicios.BCCRCambioMoneda;
import consultasDAO.CuentaDAO;
import consultasDAO.PersonaDAO;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import vistaGUI.VistaCambiarPin;
import vistaGUI.VistaCuenta;
import vistaGUI.VistaDeposito;
import vistaGUI.VistaMenu;
import vistaGUI.VistaPersona;
import vistaGUI.VistaRetiro;
import vistaGUI.VistaVerClientes;
import vistaGUI.VistaVerCuentas;

public class ControladorMenu implements ActionListener {
	
	private BCCRCambioMoneda bccrCambioMoneda;
	
	private VistaPersona vistaPersona;
	private VistaCuenta vistaCuenta;
	private VistaDeposito vistaDeposito;
	private VistaCambiarPin vistaCambiarPin;
	private VistaRetiro vistaRetiro;
	private VistaMenu vista;
	
	private ControladorRetirar controladorRetirar;
	private ControladorCambiarPIN controladorCambiarPIN;
	private ControladorPersona controladorPersona;
	private ControladorCuenta controladorCuenta;
	private ControladorDeposito controladorDeposito;
	
	public ControladorMenu (VistaMenu vista) {
	
		this.vista = vista;
		this.vista.getBtnRegistarCuenta().addActionListener(this);
		this.vista.getBtnRgistrarCliente().addActionListener(this);
		this.vista.getBtnVerClientes().addActionListener(this);
		this.vista.getBtnVerCuentas().addActionListener(this);
		this.vista.getBtnDepositar().addActionListener(this);
		this.vista.getBtnCambiarPIN().addActionListener(this);
		this.vista.getBtnRetiro().addActionListener(this);
		this.vista.getBtnVentaCompra().addActionListener(this);
		this.bccrCambioMoneda = new BCCRCambioMoneda();
	}
	
	public void iniciar() {
		vista.setTitle("Registrar Cliente");
		vista.setVisible(true);
		vista.setLocationRelativeTo(null);
		compraVenta();

	}
	
	public void compraVenta() {
	this.vista.getLblCompra().setText("Compra: "+ bccrCambioMoneda.getCompra()+" colones");
	this.vista.getLblVenta().setText("Venta: "+ bccrCambioMoneda.getVenta()+" colones");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == vista.getBtnVentaCompra()) {
			JOptionPane.showMessageDialog(null, "La compra y venta de hoy según el BCCR es\n"
					+ "Compra: "+ bccrCambioMoneda.getCompra()+" colones\n"
					+ "Venta: " +bccrCambioMoneda.getVenta() + "colones");	
		}

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
		if (e.getSource() == vista.getBtnVerClientes()) {
			VistaVerClientes vistaVerClientes = new VistaVerClientes();
			ControladorVerClientes controladorVerClientes = new ControladorVerClientes(vistaVerClientes);
			controladorVerClientes.iniciar();
			vista.setVisible(false);
		}
		if (e.getSource() == vista.getBtnVerCuentas()) {
			VistaVerCuentas vistaVerCuentas = new VistaVerCuentas();
			ControladorVerCuentas controladorVerCuentas = new ControladorVerCuentas(vistaVerCuentas);
			controladorVerCuentas.iniciar();
			vista.setVisible(false);
		}
		if (e.getSource() == vista.getBtnDepositar()) {
			VistaDeposito vistaDeposito = new VistaDeposito();
			ControladorDeposito controladorDeposito = new ControladorDeposito(vistaDeposito);
			controladorDeposito.iniciar();
			vista.setVisible(false);
		}
		if (e.getSource() == vista.getBtnCambiarPIN()) {
			VistaCambiarPin vistaCambiarPin = new VistaCambiarPin();
			ControladorCambiarPIN controladorCambiarPIN = new ControladorCambiarPIN(vistaCambiarPin);
			controladorCambiarPIN.iniciar();
			vista.setVisible(false);
		}
		if (e.getSource() == vista.getBtnRetiro()) {
			VistaRetiro vistaRetiro = new VistaRetiro();
			ControladorRetirar controladorRetirar = new ControladorRetirar(vistaRetiro);
			controladorRetirar.iniciar();
			vista.setVisible(false);
		}



	}
}
