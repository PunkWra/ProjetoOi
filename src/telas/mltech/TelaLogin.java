package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.mltech.ConectaBanco;
import classes.mltech.Login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 530, 281);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurio.setBounds(252, 63, 59, 14);
		panel.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setBounds(252, 104, 46, 14);
		panel.add(lblSenha);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(321, 60, 159, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {				

				
				if(evt.getKeyCode()==KeyEvent.VK_ENTER){
					try{
			            
				        ConectaBanco conecta = new ConectaBanco();			        
				        conecta.conectaBanco();
				        
				        String loginusuario = textFieldUsuario.getText();
				        String senhaUsuario = passwordField.getText();
				        
				        String fazerLogin = "select * from login where loginNome = '" +textFieldUsuario.getText() + "'";
				        
				        ResultSet rs = ConectaBanco.stm.executeQuery(fazerLogin);
				        rs.first();
				        
				        if((loginusuario.equals(rs.getString("loginNome")))&&(senhaUsuario.equals(rs.getString("loginSenha"))))
				        {
				            if(loginusuario.equals("admin"))
				            {
				                JOptionPane.showMessageDialog(null, "Acesso Autorizado");
				                //Chamar Tela de Opções  
				                 textFieldUsuario.setText("");
				                    passwordField.setText("");                    
				                    TelaOpcoes telaopcoes = new TelaOpcoes();
				                    telaopcoes.setVisible(true);
				                    dispose();
				                
				            }
				            if(loginusuario.equals("visitante"))
				            {
				                JOptionPane.showMessageDialog(null, "Acesso Autorizado Apenas Para Consultas");         
				              			                
				            }
				        }else{
				            JOptionPane.showMessageDialog(null, "Senha Incorreta");
				            textFieldUsuario.setText("");
				            passwordField.setText("");
				        }
				        
				        }catch(SQLException ex){
				            JOptionPane.showMessageDialog(null, "Usuário Não Cadastrado");
				            textFieldUsuario.setText("");
				            passwordField.setText("");
				        }
				}
				
			}
		});
		passwordField.setBounds(320, 101, 160, 20);
		panel.add(passwordField);
		
		JLabel lblInformaesDoUsurio = new JLabel("Informa\u00E7\u00F5es do Usu\u00E1rio");
		lblInformaesDoUsurio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInformaesDoUsurio.setBounds(168, 11, 205, 14);
		panel.add(lblInformaesDoUsurio);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/mltech/Cancelar (Copy).png")));
		btnSair.setBounds(419, 199, 89, 23);
		panel.add(btnSair);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                 Login login = new Login();
				
				String usuario = textFieldUsuario.getText();
				String senha = passwordField.getText();
				
				boolean verificaDados = login.verificaUsuario(usuario, senha);
				
				if(verificaDados==true){
					JOptionPane.showMessageDialog(null, "Acesso Autorizado");
					TelaOpcoes opcoes = new TelaOpcoes();
					opcoes.setVisible(true);
					dispose();
					textFieldUsuario.setText("");
					passwordField.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "Dados Não Conferem");
					textFieldUsuario.setText("");
					passwordField.setText("");
				}
				
				
			}
		});
		btnOk.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/mltech/Aceitar (Copy).png")));
		btnOk.setBounds(302, 199, 89, 23);
		panel.add(btnOk);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaLogin.class.getResource("/imagens/mltech/1209363630517948199.png")));
		label.setBounds(35, 81, 159, 141);
		panel.add(label);
	}
}
