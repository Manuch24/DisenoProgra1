package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VistaComisiones extends JFrame {

	private JPanel contentPane;
	private JButton btnConsultar;
	private JButton btnVolver;
	private JComboBox cbxTipo;
	private JComboBox cbxCuenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaComisiones frame = new VistaComisiones();
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
	public VistaComisiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(284, 28, 74, 21);
		contentPane.add(cbxCuenta);
		
		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"All", "Retiro", "Deposito"}));
		cbxTipo.setBounds(284, 59, 74, 21);
		contentPane.add(cbxTipo);
		
		JLabel lblNewLabel = new JLabel("Seleccione la cuenta: ");
		lblNewLabel.setBounds(74, 32, 114, 13);
		contentPane.add(lblNewLabel);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(134, 146, 85, 21);
		contentPane.add(btnConsultar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(273, 146, 85, 21);
		contentPane.add(btnVolver);
	}
	public JButton getBtnConsultar() {
		return btnConsultar;
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JComboBox getCbxTipo() {
		return cbxTipo;
	}
	public JComboBox getCbxCuenta() {
		return cbxCuenta;
	}
}
