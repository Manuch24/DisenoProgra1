package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class VistaVerSaldo extends JFrame {

	private JPanel contentPane;
	private JTextField textPIN;
	private JButton btnVolver;
	private JButton btnVerSaldo;
	private JComboBox cbxMoneda;
	private JComboBox cbxCuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVerSaldo frame = new VistaVerSaldo();
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
	public VistaVerSaldo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(291, 154, 85, 21);
		contentPane.add(btnVolver);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(187, 33, 51, 21);
		contentPane.add(cbxCuenta);
		
		JLabel lblNewLabel = new JLabel("Seleccione la cuental");
		lblNewLabel.setBounds(33, 37, 126, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el PIN");
		lblNewLabel_1.setBounds(33, 80, 101, 13);
		contentPane.add(lblNewLabel_1);
		
		textPIN = new JTextField();
		textPIN.setBounds(142, 77, 96, 19);
		contentPane.add(textPIN);
		textPIN.setColumns(10);
		
		cbxMoneda = new JComboBox();
		cbxMoneda.setModel(new DefaultComboBoxModel(new String[] {"Colones", "Dolares"}));
		cbxMoneda.setBounds(329, 60, 97, 21);
		contentPane.add(cbxMoneda);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione la moneda");
		lblNewLabel_2.setBounds(329, 37, 117, 13);
		contentPane.add(lblNewLabel_2);
		
		btnVerSaldo = new JButton("Consultar Saldo");
		btnVerSaldo.setBounds(112, 154, 126, 21);
		contentPane.add(btnVerSaldo);
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JButton getBtnVerSaldo() {
		return btnVerSaldo;
	}
	public JTextField getTextPIN() {
		return textPIN;
	}
	public JComboBox getCbxMoneda() {
		return cbxMoneda;
	}
	public JComboBox getCbxCuenta() {
		return cbxCuenta;
	}
}
