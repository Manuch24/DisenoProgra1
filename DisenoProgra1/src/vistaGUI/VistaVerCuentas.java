package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaVerCuentas extends JFrame {

	private JPanel contentPane;
	private JTable tableCuentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVerCuentas frame = new VistaVerCuentas();
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
	public VistaVerCuentas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCargar = new JButton("Cargar cuentas");
		btnCargar.setBounds(10, 10, 157, 21);
		contentPane.add(btnCargar);
		
		JButton btnVerDetalles = new JButton("Ver detalles");
		btnVerDetalles.setBounds(180, 10, 85, 21);
		contentPane.add(btnVerDetalles);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(476, 319, 85, 21);
		contentPane.add(btnVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 93, 380, 201);
		contentPane.add(scrollPane);
		
		tableCuentas = new JTable();
		scrollPane.setViewportView(tableCuentas);
	}
}
