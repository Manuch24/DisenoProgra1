package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VistaMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VistaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.setBounds(125, 120, 85, 21);
		contentPane.add(btnNewButton);
		
	
	
	}
	
}
