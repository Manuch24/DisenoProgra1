package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VistaMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnRgistrarCliente;
	private JButton btnRegistarCuenta;

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
		btnRgistrarCliente.setBounds(32, 44, 176, 21);
		contentPane.add(btnRgistrarCliente);
		
		btnRegistarCuenta = new JButton("Registar Cuenta");
		btnRegistarCuenta.setBounds(253, 44, 147, 21);
		contentPane.add(btnRegistarCuenta);
	}
	public JButton getBtnRgistrarCliente() {
		return btnRgistrarCliente;
	}
	public JButton getBtnRegistarCuenta() {
		return btnRegistarCuenta;
	}
}
