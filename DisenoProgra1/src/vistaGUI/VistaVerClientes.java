package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JScrollPane;

public class VistaVerClientes extends JFrame {

	private JPanel contentPane;
	private JButton btnVolver;
	private JButton btnCargar;
	private JScrollPane scrollPane;
	private JTable tableClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVerClientes frame = new VistaVerClientes();
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
	public VistaVerClientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(21, 10, 85, 21);
		contentPane.add(btnCargar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(169, 10, 85, 21);
		contentPane.add(btnVolver);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 84, 403, 195);
		contentPane.add(scrollPane);
		
		tableClientes = new JTable();
		scrollPane.setViewportView(tableClientes);
	}


	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JButton getBtnCargar() {
		return btnCargar;
	}
	public JTable getTableClientes() {
		return tableClientes;
	}
}
