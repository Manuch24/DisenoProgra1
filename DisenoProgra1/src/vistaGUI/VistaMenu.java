package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class VistaMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnRgistrarCliente;
	private JButton btnRegistarCuenta;
	private JButton btnVerClientes;
	private JButton btnVerCuentas;
	private JButton btnDepositar;
	private JButton btnCambiarPIN;
	private JButton btnRetiro;
	private JButton btnVentaCompra;
	private JLabel lblNewLabel;
	private JLabel lblVenta;
	private JLabel lblCompra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMenu frame = new VistaMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRgistrarCliente = new JButton("Registrar Cliente");
		btnRgistrarCliente.setBounds(21, 38, 176, 21);
		contentPane.add(btnRgistrarCliente);
		
		btnRegistarCuenta = new JButton("Registar Cuenta");
		btnRegistarCuenta.setBounds(227, 38, 147, 21);
		contentPane.add(btnRegistarCuenta);
		
		btnVerClientes = new JButton("Ver Clientes");
		btnVerClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerClientes.setBounds(21, 82, 162, 21);
		contentPane.add(btnVerClientes);
		
		btnVerCuentas = new JButton("Ver Cuentas");
		btnVerCuentas.setBounds(234, 82, 140, 21);
		contentPane.add(btnVerCuentas);
		
		btnDepositar = new JButton("Realizar Deposito");
		btnDepositar.setBounds(218, 124, 156, 21);
		contentPane.add(btnDepositar);
		
		btnCambiarPIN = new JButton("CambiarPIN");
		btnCambiarPIN.setBounds(21, 124, 131, 21);
		contentPane.add(btnCambiarPIN);
		
		btnRetiro = new JButton("Realizar Retiros");
		btnRetiro.setBounds(35, 175, 129, 21);
		contentPane.add(btnRetiro);
		
		btnVentaCompra = new JButton("Ver tipo cambio");
		btnVentaCompra.setBounds(257, 175, 140, 21);
		contentPane.add(btnVentaCompra);
		
		lblNewLabel = new JLabel("Tipo de cambio de hoy ");
		lblNewLabel.setBounds(472, 50, 174, 21);
		contentPane.add(lblNewLabel);
		
		lblVenta = new JLabel("New label");
		lblVenta.setBounds(468, 86, 156, 13);
		contentPane.add(lblVenta);
		
		lblCompra = new JLabel("New label");
		lblCompra.setBounds(472, 128, 174, 13);
		contentPane.add(lblCompra);
	}
	public JButton getBtnRgistrarCliente() {
		return btnRgistrarCliente;
	}
	public JButton getBtnRegistarCuenta() {
		return btnRegistarCuenta;
	}
	public JButton getBtnVerClientes() {
		return btnVerClientes;
	}
	public JButton getBtnVerCuentas() {
		return btnVerCuentas;
	}
	public JButton getBtnDepositar() {
		return btnDepositar;
	}
	public JButton getBtnCambiarPIN() {
		return btnCambiarPIN;
	}
	public JButton getBtnRetiro() {
		return btnRetiro;
	}
	public JButton getBtnVentaCompra() {
		return btnVentaCompra;
	}
	public JLabel getLblCompra() {
		return lblCompra;
	}
	public JLabel getLblVenta() {
		return lblVenta;
	}
}
