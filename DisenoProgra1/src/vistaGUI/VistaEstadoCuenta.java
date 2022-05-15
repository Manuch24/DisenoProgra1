package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class VistaEstadoCuenta extends JFrame {

	private JPanel contentPane;
	private JTable tableOperaciones;
	private JButton btnCargarEstado;
	private JButton btnVolver;
	private JComboBox cbxCuenta;
	private JLabel lblSaldoActual;
	private JComboBox cbxMoneda;
	private JTextField txtPIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEstadoCuenta frame = new VistaEstadoCuenta();
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
	public VistaEstadoCuenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la cuenta");
		lblNewLabel.setBounds(49, 20, 143, 13);
		contentPane.add(lblNewLabel);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(213, 16, 56, 21);
		contentPane.add(cbxCuenta);
		
		btnCargarEstado = new JButton("Cargar Estado");
		btnCargarEstado.setBounds(279, 102, 136, 21);
		contentPane.add(btnCargarEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 145, 366, 176);
		contentPane.add(scrollPane);
		
		tableOperaciones = new JTable();
		scrollPane.setViewportView(tableOperaciones);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(324, 355, 85, 21);
		contentPane.add(btnVolver);
		
		lblSaldoActual = new JLabel("New label");
		lblSaldoActual.setBounds(61, 359, 182, 13);
		contentPane.add(lblSaldoActual);
		
		cbxMoneda = new JComboBox();
		cbxMoneda.setModel(new DefaultComboBoxModel(new String[] {"Colones", "Dolares"}));
		cbxMoneda.setBounds(436, 56, 111, 21);
		contentPane.add(cbxMoneda);
		
		txtPIN = new JTextField();
		txtPIN.setBounds(173, 57, 96, 19);
		contentPane.add(txtPIN);
		txtPIN.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PIN:");
		lblNewLabel_1.setBounds(70, 60, 45, 13);
		contentPane.add(lblNewLabel_1);
	}
	public JTable getTableOperaciones() {
		return tableOperaciones;
	}
	public JButton getBtnCargarEstado() {
		return btnCargarEstado;
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JComboBox getCbxCuenta() {
		return cbxCuenta;
	}
	public JLabel getLblSaldoActual() {
		return lblSaldoActual;
	}
	public JComboBox getCbxMoneda() {
		return cbxMoneda;
	}
	public JTextField getTxtPIN() {
		return txtPIN;
	}
}
