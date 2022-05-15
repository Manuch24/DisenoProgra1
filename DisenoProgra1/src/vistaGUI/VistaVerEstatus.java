package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VistaVerEstatus extends JFrame {

	private JPanel contentPane;
	private JComboBox cbxCuenta;
	private JButton btnvolver;
	private JButton btnEstatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVerEstatus frame = new VistaVerEstatus();
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
	public VistaVerEstatus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(196, 30, 70, 21);
		contentPane.add(cbxCuenta);
		
		JLabel lblNewLabel = new JLabel("Seleccione la cuenta");
		lblNewLabel.setBounds(48, 34, 138, 13);
		contentPane.add(lblNewLabel);
		
		btnEstatus = new JButton("Ver estatus");
		btnEstatus.setBounds(196, 89, 85, 21);
		contentPane.add(btnEstatus);
		
		btnvolver = new JButton("Volver");
		btnvolver.setBounds(311, 89, 85, 21);
		contentPane.add(btnvolver);
	}

	public JButton getBtnEstatus() {
		return btnEstatus;
	}
	public JButton getBtnvolver() {
		return btnvolver;
	}
	public JComboBox getCbxCuenta() {
		return cbxCuenta;
	}
}
