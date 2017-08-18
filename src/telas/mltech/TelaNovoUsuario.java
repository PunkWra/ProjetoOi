package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaNovoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovoUsuario frame = new TelaNovoUsuario();
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
	public TelaNovoUsuario() {
		setTitle("Novo Usu\u00E1rio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaNovoUsuario.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsurio.setBounds(107, 71, 56, 16);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenha.setBounds(107, 119, 56, 16);
		contentPane.add(lblSenha);
		
		JLabel lblRegio = new JLabel("Regi\u00E3o");
		lblRegio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRegio.setBounds(107, 163, 56, 16);
		contentPane.add(lblRegio);
		
		textField = new JTextField();
		textField.setBounds(175, 68, 173, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 116, 173, 22);
		contentPane.add(passwordField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione Regi\u00E3o", "PMM", "SAN", "SMA"}));
		comboBox.setBounds(205, 160, 143, 22);
		contentPane.add(comboBox);
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaNovoUsuario.class.getResource("/imagens/mltech/Cancelar (Copy).png")));
		btnCancelar.setBounds(402, 235, 97, 25);
		contentPane.add(btnCancelar);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setIcon(new ImageIcon(TelaNovoUsuario.class.getResource("/imagens/mltech/Aceitar (Copy).png")));
		btnCriar.setBounds(293, 235, 97, 25);
		contentPane.add(btnCriar);
	}
}
