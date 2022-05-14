package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class VistaRetiro extends JFrame {

	private JPanel contentPane;
	private JTextField txtPIN;
	private JTextField txtMonto;
	private JTextField txtPalabra;
	private JButton btnVolver;
	private JButton btnRetirar;
	private JButton btnEnviarSMS;
	private JComboBox cbxCuentaOrigen;
	private JComboBox cbxMoneda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRetiro frame = new VistaRetiro();
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
	public VistaRetiro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleecione la cuenta origen:");
		lblNewLabel.setBounds(40, 33, 146, 13);
		contentPane.add(lblNewLabel);
		
		cbxCuentaOrigen = new JComboBox();
		cbxCuentaOrigen.setBounds(217, 29, 55, 21);
		contentPane.add(cbxCuentaOrigen);
		
		JLabel lblNewLabel_1 = new JLabel("Pin: ");
		lblNewLabel_1.setBounds(53, 86, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtPIN = new JTextField();
		txtPIN.setBounds(171, 83, 96, 19);
		contentPane.add(txtPIN);
		txtPIN.setColumns(10);
		
		cbxMoneda = new JComboBox();
		cbxMoneda.setModel(new DefaultComboBoxModel(new String[] {"Colones", "Dolares"}));
		cbxMoneda.setBounds(421, 29, 77, 21);
		contentPane.add(cbxMoneda);
		
		JLabel lblNewLabel_2 = new JLabel("Moneda:");
		lblNewLabel_2.setBounds(346, 29, 78, 13);
		contentPane.add(lblNewLabel_2);
		
		btnEnviarSMS = new JButton("EnviarSMS");
		btnEnviarSMS.setBounds(292, 82, 132, 21);
		contentPane.add(btnEnviarSMS);
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese el monto:");
		lblNewLabel_3.setBounds(39, 191, 96, 13);
		contentPane.add(lblNewLabel_3);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(176, 188, 96, 19);
		contentPane.add(txtMonto);
		txtMonto.setColumns(10);
		
		txtPalabra = new JTextField();
		txtPalabra.setBounds(176, 143, 96, 19);
		contentPane.add(txtPalabra);
		txtPalabra.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese la palabra");
		lblNewLabel_4.setBounds(39, 149, 104, 13);
		contentPane.add(lblNewLabel_4);
		
		btnRetirar = new JButton("Retirar");
		btnRetirar.setBounds(187, 233, 85, 21);
		contentPane.add(btnRetirar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(327, 233, 85, 21);
		contentPane.add(btnVolver);
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JButton getBtnRetirar() {
		return btnRetirar;
	}
	public JTextField getTxtMonto() {
		return txtMonto;
	}
	public JTextField getTxtPalabra() {
		return txtPalabra;
	}
	public JButton getBtnEnviarSMS() {
		return btnEnviarSMS;
	}
	public JTextField getTxtPIN() {
		return txtPIN;
	}
	public JComboBox getCbxCuentaOrigen() {
		return cbxCuentaOrigen;
	}
	public JComboBox getCbxMoneda() {
		return cbxMoneda;
	}
}
