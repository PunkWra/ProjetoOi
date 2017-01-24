package classes.mltech;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;


import javax.swing.JOptionPane;

public class Placa {
	
	private String serialPlaca;
	private String localPlaca;
	private Date dataEnvio;
	private String obervacoes;
	private int indiceCidade;
	private int indiceEstacao;
	private int indiceModelo;
	private String statusPlaca;
	private String tecnicaPlaca;
	private String almoxPlaca;
	private String ultimoComentario;
	
	public String getUltimoComentario() {
		return ultimoComentario;
	}
	public void setUltimoComentario(String ultimoComentario) {
		this.ultimoComentario = ultimoComentario;
	}
	public String getAlmoxPlaca() {
		return almoxPlaca;
	}
	public void setAlmoxPlaca(String almoxPlaca) {
		this.almoxPlaca = almoxPlaca;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public String getTecnicaPlaca() {
		return tecnicaPlaca;
	}
	public void setTecnicaPlaca(String tecnicaPlaca) {
		this.tecnicaPlaca = tecnicaPlaca;
	}
	public String getSerialPlaca() {
		return serialPlaca;
	}
	public void setSerialPlaca(String serialPlaca) {
		this.serialPlaca = serialPlaca;
	}
	public String getLocalPlaca() {
		return localPlaca;
	}
	public void setLocalPlaca(String localPlaca) {
		this.localPlaca = localPlaca;
	}
	public String getObervacoes() {
		return obervacoes;
	}
	public void setObervacoes(String obervacoes) {
		this.obervacoes = obervacoes;
	}
	public int getIndiceCidade() {
		return indiceCidade;
	}
	public void setIndiceCidade(int indiceCidade) {
		this.indiceCidade = indiceCidade;
	}
	public int getIndiceEstacao() {
		return indiceEstacao;
	}
	public void setIndiceEstacao(int indiceEstacao) {
		this.indiceEstacao = indiceEstacao;
	}
	public int getIndiceModelo() {
		return indiceModelo;
	}
	public void setIndiceModelo(int indiceModelo) {
		this.indiceModelo = indiceModelo;
	}
	public String getStatusPlaca() {
		return statusPlaca;
	}
	public void setStatusPlaca(String statusPlaca) {
		this.statusPlaca = statusPlaca;
	}
	
	
	public boolean verificaPlaca(String serial){
		
		ResultSet rs = null;
		boolean retorno = false;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String verificaPlaca = "select * from placa";
			
			rs = conecta.stm.executeQuery(verificaPlaca);
			
			while(rs.next()){
				if(serial.equals(rs.getString("serialPlaca"))){
					retorno = true;
					//JOptionPane.showMessageDialog(null, "Placa Já Cadastrada");
					break;
				}
			}		
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return retorno;
		
	}//Fim do método verificaPlaca()
	
	public void inserePlaca(){			
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			String inserePlaca = "insert into placa(serialPlaca,localPlaca,dataEnvio,observacoes,cidade,estacao,modelo,statusPlaca,tecnicaPlaca,almoxPlaca,ultimoComentario) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stm = conecta.con.prepareStatement(inserePlaca);
			stm.setString(1, this.serialPlaca);
			stm.setString(2, this.localPlaca);
			if(this.dataEnvio!=null){
			stm.setDate(3, new java.sql.Date(this.dataEnvio.getTime()));
			}else{
				stm.setNull(3, Types.DATE);
			}
			stm.setString(4, this.obervacoes);
			stm.setInt(5, this.indiceCidade);
			stm.setInt(6, this.indiceEstacao);
			stm.setInt(7, this.indiceModelo);
			stm.setString(8, this.statusPlaca);
			stm.setString(9, this.tecnicaPlaca);
			stm.setString(10, this.almoxPlaca);
			stm.setString(11, this.ultimoComentario);
			stm.executeUpdate();
			stm.close();
			conecta.con.close();				
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
	}//Fim do método inserePlaca()
	
	public void atualizaPlaca(){
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String atualizaPlaca = "update placa set localPlaca=?,dataEnvio=?,observacoes=?,cidade=?,estacao=?,modelo=?,statusPlaca=?,tecnicaPlaca=?,almoxPlaca=?,ultimoComentario=? where serialPlaca='"+this.serialPlaca+"'";
			PreparedStatement stm = conecta.con.prepareStatement(atualizaPlaca);
			stm.setString(1, this.localPlaca);
			if(this.dataEnvio!=null){
			stm.setDate(2, new java.sql.Date(this.dataEnvio.getTime()));
			}else{
				stm.setNull(2, Types.DATE);
			}
			stm.setString(3, this.obervacoes);
			stm.setInt(4, this.indiceCidade);
			stm.setInt(5, this.indiceEstacao);
			stm.setInt(6, this.indiceModelo);
			stm.setString(7, this.statusPlaca);
			stm.setString(8, this.tecnicaPlaca);
			stm.setString(9, this.almoxPlaca);
			stm.setString(10, this.ultimoComentario);
			stm.executeUpdate();
			stm.close();
			conecta.con.close();			
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		
		
	}
	
	
	
	
	

}
