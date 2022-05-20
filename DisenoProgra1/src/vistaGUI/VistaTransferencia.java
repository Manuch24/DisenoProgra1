package vistaGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VistaTransferencia extends JFrame {

	public JPanel contentPane;
	public JTextField TxtSMSCodigo;
	public JTextField txtMonto;
	public JTextField txtDestino;
	public JTextField txtPin;
	public JButton BtnTransferir;
	public JButton BtnSMS;
	public JButton BtnVolver;
	public JComboBox CbxCuentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTransferencia frame = new VistaTransferencia();
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
	public VistaTransferencia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Realizar transferencia a otra cuenta:");
		lblNewLabel.setBounds(27, 23, 252, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese cuenta de origen: ");
		lblNewLabel_1.setBounds(51, 58, 141, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese el pin de su cuenta:");
		lblNewLabel_2.setBounds(51, 103, 153, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enviar SMS:");
		lblNewLabel_3.setBounds(51, 152, 70, 14);
		contentPane.add(lblNewLabel_3);
		
		BtnSMS = new JButton("Enviar SMS");
		BtnSMS.setBounds(128, 148, 89, 23);
		contentPane.add(BtnSMS);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese el c\u00F3digo que recibi\u00F3:");
		lblNewLabel_4.setBounds(75, 192, 204, 14);
		contentPane.add(lblNewLabel_4);
		
		TxtSMSCodigo = new JTextField();
		TxtSMSCodigo.setBounds(128, 217, 108, 20);
		contentPane.add(TxtSMSCodigo);
		TxtSMSCodigo.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Ingrese el monto que desea transferir:");
		lblNewLabel_5.setBounds(51, 259, 204, 14);
		contentPane.add(lblNewLabel_5);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(246, 256, 113, 20);
		contentPane.add(txtMonto);
		txtMonto.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Ingrese el n\u00FAmero de cuenta al cual desea transferir:");
		lblNewLabel_6.setBounds(51, 311, 289, 14);
		contentPane.add(lblNewLabel_6);
		
		txtDestino = new JTextField();
		txtDestino.setBounds(246, 336, 113, 20);
		contentPane.add(txtDestino);
		txtDestino.setColumns(10);
		
		BtnTransferir = new JButton("Realizar Transferencia");
		BtnTransferir.setBounds(64, 398, 140, 23);
		contentPane.add(BtnTransferir);
		
		BtnVolver = new JButton("Volver");
		BtnVolver.setBounds(355, 398, 89, 23);
		contentPane.add(BtnVolver);
		
		txtPin = new JTextField();
		txtPin.setBounds(193, 100, 113, 20);
		contentPane.add(txtPin);
		txtPin.setColumns(10);
		
		CbxCuentas = new JComboBox();
		CbxCuentas.setBounds(202, 59, 89, 22);
		contentPane.add(CbxCuentas);
		
		
	}
	
	public JTextField getTxtSMSCodigo() {
		return TxtSMSCodigo;
	}
	public JTextField getTxtMonto() {
		return txtMonto;
	}
	public JTextField getTxtDestino() {
		return txtDestino;
	}
	public JTextField getTxtPin() {
		return txtPin;
	}
	public JButton getBtnTransferir() {
		return BtnTransferir;
	}
	public JButton getBtnSMS() {
		return BtnSMS;
	}
	public JButton getBtnVolver() {
		return BtnVolver;
	}
	public JComboBox getCbxCuentas() {
		return CbxCuentas;
	}
	
	
}
