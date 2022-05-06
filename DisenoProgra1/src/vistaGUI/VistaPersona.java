package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaPersona extends JFrame {

	private JPanel contentPane;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtPrimerApellid;
	private JTextField txtSegundoApellido;
	private JTextField txtNumTelefono;
	private JTextField txtEmail;
	public JButton btnRegistrarPersona;
	private JDateChooser dateChooserNacimiento;

		/**
	 * Create the frame.
	 */
	public VistaPersona() {
		setTitle("Registrar Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro de persona");
		lblNewLabel.setBounds(10, 33, 152, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese su c\u00E9dula:");
		lblNewLabel_1.setBounds(49, 63, 135, 22);
		contentPane.add(lblNewLabel_1);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(229, 64, 122, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese su nombre:");
		lblNewLabel_2.setBounds(49, 114, 152, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(229, 111, 163, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel txtPrimerApellido = new JLabel("Ingrese su primer apellido:");
		txtPrimerApellido.setBounds(46, 157, 163, 14);
		contentPane.add(txtPrimerApellido);
		
		txtPrimerApellid = new JTextField();
		txtPrimerApellid.setBounds(229, 154, 163, 20);
		contentPane.add(txtPrimerApellid);
		txtPrimerApellid.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese su segundo apellido:");
		lblNewLabel_3.setBounds(46, 196, 163, 14);
		contentPane.add(lblNewLabel_3);
		
		txtSegundoApellido = new JTextField();
		txtSegundoApellido.setBounds(229, 193, 163, 20);
		contentPane.add(txtSegundoApellido);
		txtSegundoApellido.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese fecha de nacimiento:");
		lblNewLabel_4.setBounds(49, 237, 170, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ingrese n\u00FAmero de tel\u00E9fono:");
		lblNewLabel_5.setBounds(49, 278, 170, 14);
		contentPane.add(lblNewLabel_5);
		
		txtNumTelefono = new JTextField();
		txtNumTelefono.setBounds(229, 275, 163, 20);
		contentPane.add(txtNumTelefono);
		txtNumTelefono.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Ingrese su correo:");
		lblNewLabel_6.setBounds(49, 315, 135, 14);
		contentPane.add(lblNewLabel_6);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(229, 312, 240, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		btnRegistrarPersona = new JButton("Registrar");
		btnRegistrarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarPersona.setBounds(351, 375, 89, 23);
		contentPane.add(btnRegistrarPersona);
		
		dateChooserNacimiento = new JDateChooser();
		dateChooserNacimiento.setBounds(229, 237, 163, 20);
		contentPane.add(dateChooserNacimiento);
	}
	public JButton getBtnRegistrarPersona() {
		return btnRegistrarPersona;
	}
	public JTextField getTxtCedula() {
		return txtCedula;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public JTextField getTxtPrimerApellid() {
		return txtPrimerApellid;
	}
	public JTextField getTxtSegundoApellido() {
		return txtSegundoApellido;
	}
	public JDateChooser getDateChooserNacimiento() {
		return dateChooserNacimiento;
	}
	public JTextField getTxtNumTelefono() {
		return txtNumTelefono;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
}
