package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VistaCuenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtPin;
	private JTextField txtMontoInicial;
	private JComboBox cbxCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCuenta frame = new VistaCuenta();
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
	public VistaCuenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbxCliente = new JComboBox();
		cbxCliente.setBounds(297, 27, 77, 21);
		contentPane.add(cbxCliente);
		
		JLabel lblNewLabel = new JLabel("seleccione su identificacion");
		lblNewLabel.setBounds(70, 31, 138, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese su pin:  ");
		lblNewLabel_1.setBounds(70, 71, 84, 13);
		contentPane.add(lblNewLabel_1);
		
		txtPin = new JTextField();
		txtPin.setBounds(207, 68, 96, 19);
		contentPane.add(txtPin);
		txtPin.setColumns(10);
		
		txtMontoInicial = new JTextField();
		txtMontoInicial.setBounds(207, 113, 96, 19);
		contentPane.add(txtMontoInicial);
		txtMontoInicial.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Monto inicial");
		lblNewLabel_2.setBounds(70, 116, 84, 13);
		contentPane.add(lblNewLabel_2);
	}
	public JComboBox getCbxCliente() {
		return cbxCliente;
	}
}
