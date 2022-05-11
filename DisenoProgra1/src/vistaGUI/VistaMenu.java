package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnRgistrarCliente;
	private JButton btnRegistarCuenta;
	private JButton btnVerClientes;
	private JButton btnVerCuentas;
	private JButton btnDepositar;
	private JButton btnCambiarPIN;

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
		setBounds(100, 100, 450, 300);
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
}
