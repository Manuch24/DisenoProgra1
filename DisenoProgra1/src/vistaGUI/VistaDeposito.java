package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VistaDeposito extends JFrame {

	private JPanel contentPane;
	private JTextField txtMonto;
	private JButton btnDepositar;
	private JButton btnVolver;
	private JComboBox cbxCuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaDeposito frame = new VistaDeposito();
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
	public VistaDeposito() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(191, 87, 96, 19);
		contentPane.add(txtMonto);
		txtMonto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese el monto");
		lblNewLabel_1.setBounds(47, 93, 96, 13);
		contentPane.add(lblNewLabel_1);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(341, 232, 85, 21);
		contentPane.add(btnVolver);
		
		btnDepositar = new JButton("Depositar");
		btnDepositar.setBounds(201, 138, 85, 21);
		contentPane.add(btnDepositar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(357, 89, 69, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Moneda");
		lblNewLabel_2.setBounds(358, 66, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(263, 34, 60, 21);
		contentPane.add(cbxCuenta);
		
		JLabel lblNewLabel = new JLabel("Seleccione el numero de cuenta: ");
		lblNewLabel.setBounds(47, 38, 167, 13);
		contentPane.add(lblNewLabel);
	}
	public JTextField getTxtMonto() {
		return txtMonto;
	}
	public JButton getBtnDepositar() {
		return btnDepositar;
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JComboBox getCbxCuenta() {
		return cbxCuenta;
	}
}
