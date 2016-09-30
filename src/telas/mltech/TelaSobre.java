package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 437, 230);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDesenvolvidoPor = new JLabel("Desenvolvido por:");
		lblDesenvolvidoPor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesenvolvidoPor.setBounds(26, 40, 118, 14);
		panel.add(lblDesenvolvidoPor);
		
		JLabel lblMlTech = new JLabel("ML Tech");
		lblMlTech.setBounds(154, 40, 60, 14);
		panel.add(lblMlTech);
		
		JLabel lblContato = new JLabel("Contato:");
		lblContato.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContato.setBounds(26, 75, 60, 14);
		panel.add(lblContato);
		
		JLabel lblWladimirsouzaoutlookcom = new JLabel("wladimirsouza@outlook.com");
		lblWladimirsouzaoutlookcom.setBounds(96, 75, 183, 14);
		panel.add(lblWladimirsouzaoutlookcom);
	}
}
