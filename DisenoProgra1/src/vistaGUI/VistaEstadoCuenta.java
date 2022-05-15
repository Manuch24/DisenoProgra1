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

public class VistaEstadoCuenta extends JFrame {

	private JPanel contentPane;
	private JTable tableOperaciones;
	private JButton btnCargarEstado;
	private JButton btnVolver;
	private JComboBox cbxCuenta;
	private JLabel lblSaldoActual;
	private JComboBox cbxMoneda;

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
		lblNewLabel.setBounds(60, 20, 143, 13);
		contentPane.add(lblNewLabel);
		
		cbxCuenta = new JComboBox();
		cbxCuenta.setBounds(213, 16, 56, 21);
		contentPane.add(cbxCuenta);
		
		btnCargarEstado = new JButton("Cargar Estado");
		btnCargarEstado.setBounds(279, 16, 136, 21);
		contentPane.add(btnCargarEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 58, 366, 176);
		contentPane.add(scrollPane);
		
		tableOperaciones = new JTable();
		scrollPane.setViewportView(tableOperaciones);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(354, 288, 85, 21);
		contentPane.add(btnVolver);
		
		lblSaldoActual = new JLabel("New label");
		lblSaldoActual.setBounds(68, 312, 182, 13);
		contentPane.add(lblSaldoActual);
		
		cbxMoneda = new JComboBox();
		cbxMoneda.setModel(new DefaultComboBoxModel(new String[] {"Colones", "Dolares"}));
		cbxMoneda.setBounds(436, 56, 111, 21);
		contentPane.add(cbxMoneda);
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
}
