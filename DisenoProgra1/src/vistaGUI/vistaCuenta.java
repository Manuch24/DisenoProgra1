package vistaGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class vistaCuenta {

	private JFrame frame;
	private JTextField txtMontoInicial;
	
	public vistaCuenta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese el PIN");
		lblNewLabel.setBounds(69, 77, 77, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JTextField txtPIN = new JTextField();
		txtPIN.setBounds(166, 79, 96, 19);
		frame.getContentPane().add(txtPIN);
		txtPIN.setColumns(10);
		
		JLabel txoDepositoInicial = new JLabel("Ingrese el deposito inicial");
		txoDepositoInicial.setBounds(69, 135, 129, 23);
		frame.getContentPane().add(txoDepositoInicial);
		
		txtMontoInicial = new JTextField();
		txtMontoInicial.setBounds(208, 137, 96, 19);
		frame.getContentPane().add(txtMontoInicial);
		txtMontoInicial.setColumns(10);
	}
}
