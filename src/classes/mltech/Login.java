package classes.mltech;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Login {

	private String nomeUsuario;
	private String senhaUsuario;
	private String regiaoLogin;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
	public String getRegiaoLogin() {
		return regiaoLogin;
	}
	
	public void setRegiaoLogin(String regiaoLogin) {
		this.regiaoLogin = regiaoLogin;
	}
	
	public boolean verificaUsuario(String usuario, String senha){
		
		ResultSet rs = null;
		boolean retorno = false;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String verificaDados = "select * from login";
			rs = conecta.stm.executeQuery(verificaDados);
			
			while(rs.next()){
				if((usuario.equals(rs.getString("loginNome")))&&(senha.equals(rs.getString("loginSenha")))){
					retorno = true;
				}
			}				
		}catch(SQLException e){		
			JOptionPane.showMessageDialog(null, e);
		}
		
		return retorno;		
	}	
	
	public void adicionaUsuario(String usuario,String senha,String regiao){
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String adicionaUsuario = "insert into login(loginNome,loginSenha,regiaoLogin) values(?,?,?)";
			PreparedStatement stm = conecta.con.prepareStatement(adicionaUsuario);
			stm.setString(1, usuario);
			stm.setString(2, senha);
			stm.setString(3, regiao);
			stm.execute();
			stm.close();
			conecta.con.close();
			JOptionPane.showMessageDialog(null, "Usuário Cadastrado Com Sucesso");
			
		}catch(SQLException e){
			
			JOptionPane.showMessageDialog(null, e);
			
		}
		
		
	}
	
	
}
