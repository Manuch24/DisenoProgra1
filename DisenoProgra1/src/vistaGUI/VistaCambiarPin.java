package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaCambiarPin extends JFrame {

	private JPanel contentPane;
	private JTextField txtActualPIN;
	private JTextField txtNuevoPIN;
	private JButton btnCambiarPIN;
	private JButton btnVolver;
	private JComboBox cbxCuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCambiarPin frame = new VistaCambiarPin();
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
	public VistaCambiarPin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(266, 24, 84, 21);
		contentPane.add(cbxCuenta);
		
		JLabel lblNewLabel = new JLabel("Seleccione el numero de cuenta");
		lblNewLabel.setBounds(29, 28, 164, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Imgrese el PIN actual");
		lblNewLabel_1.setBounds(29, 78, 143, 13);
		contentPane.add(lblNewLabel_1);
		
		txtActualPIN = new JTextField();
		txtActualPIN.setBounds(254, 75, 96, 19);
		contentPane.add(txtActualPIN);
		txtActualPIN.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese el nuevo PIN: ");
		lblNewLabel_2.setBounds(42, 147, 118, 13);
		contentPane.add(lblNewLabel_2);
		
		txtNuevoPIN = new JTextField();
		txtNuevoPIN.setBounds(254, 144, 96, 19);
		contentPane.add(txtNuevoPIN);
		txtNuevoPIN.setColumns(10);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(406, 199, 85, 21);
		contentPane.add(btnVolver);
		
		btnCambiarPIN = new JButton("Cambiar PIN");
		btnCambiarPIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCambiarPIN.setBounds(236, 199, 137, 21);
		contentPane.add(btnCambiarPIN);
	}
	public JButton getBtnCambiarPIN() {
		return btnCambiarPIN;
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JTextField getTxtNuevoPIN() {
		return txtNuevoPIN;
	}
	public JTextField getTxtActualPIN() {
		return txtActualPIN;
	}
	public JComboBox getCbxCuenta() {
		return cbxCuenta;
	}
}
