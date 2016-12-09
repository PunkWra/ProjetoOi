package classes.mltech;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Circuito {
	
	private int indiceCircuito;
	private String numeroCircuito;	
	private int equipamentoPDHA;
	private int equipamentoPDHB;
	private int equipamentoSDHA;
	private int equipamentoSDHB;
	private int cidadeA;
	private int cidadeB;
	private int estacaoA;
	private int estacaoB;
	private String portaA;
	private String portaB;
	private String obervacoes;
	private String slotA;
	private String slotB;
	private String statusA;
	private String statusB;
	private String didA;
	private String didB;
	private String cliente;
	private String cabeceira;
	private String statusCircuito;
	
		
	public String getStatusCircuito() {
		return statusCircuito;
	}

	public void setStatusCircuito(String statusCircuito) {
		this.statusCircuito = statusCircuito;
	}

	public int getEstacaoA() {
		return estacaoA;
	}

	public void setEstacaoA(int estacaoA) {
		this.estacaoA = estacaoA;
	}

	public int getEstacaoB() {
		return estacaoB;
	}

	public void setEstacaoB(int estacaoB) {
		this.estacaoB = estacaoB;
	}

	public String getCabeceira() {
		return cabeceira;
	}

	public void setCabeceira(String cabeceira) {
		this.cabeceira = cabeceira;
	}

	public int getIndiceCircuito() {
		return indiceCircuito;
	}

	public void setIndiceCircuito(int indiceCircuito) {
		this.indiceCircuito = indiceCircuito;
	}

	public String getNumeroCircuito() {
		return numeroCircuito;
	}

	public void setNumeroCircuito(String numeroCircuito) {
		this.numeroCircuito = numeroCircuito;
	}

	public int getEquipamentoPDHA() {
		return equipamentoPDHA;
	}

	public void setEquipamentoPDHA(int equipamentoPDHA) {
		this.equipamentoPDHA = equipamentoPDHA;
	}

	public int getEquipamentoPDHB() {
		return equipamentoPDHB;
	}

	public void setEquipamentoPDHB(int equipamentoPDHB) {
		this.equipamentoPDHB = equipamentoPDHB;
	}
	
	public int getEquipamentoSDHA() {
		return equipamentoSDHA;
	}

	public void setEquipamentoSDHA(int equipamentoSDHA) {
		this.equipamentoSDHA = equipamentoSDHA;
	}

	public int getEquipamentoSDHB() {
		return equipamentoSDHB;
	}

	public void setEquipamentoSDHB(int equipamentoSDHB) {
		this.equipamentoSDHB = equipamentoSDHB;
	}

	public int getCidadeA() {
		return cidadeA;
	}

	public void setCidadeA(int cidadeA) {
		this.cidadeA = cidadeA;
	}

	public int getCidadeB() {
		return cidadeB;
	}

	public void setCidadeB(int cidadeB) {
		this.cidadeB = cidadeB;
	}

	public String getPortaA() {
		return portaA;
	}

	public void setPortaA(String portaA) {
		this.portaA = portaA;
	}

	public String getPortaB() {
		return portaB;
	}

	public void setPortaB(String portaB) {
		this.portaB = portaB;
	}

	public String getObervacoes() {
		return obervacoes;
	}

	public void setObervacoes(String obervacoes) {
		this.obervacoes = obervacoes;
	}

	public String getSlotA() {
		return slotA;
	}

	public void setSlotA(String slotA) {
		this.slotA = slotA;
	}

	public String getSlotB() {
		return slotB;
	}

	public void setSlotB(String slotB) {
		this.slotB = slotB;
	}

	public String getStatusA() {
		return statusA;
	}

	public void setStatusA(String statusA) {
		this.statusA = statusA;
	}

	public String getStatusB() {
		return statusB;
	}

	public void setStatusB(String statusB) {
		this.statusB = statusB;
	}

	public String getDidA() {
		return didA;
	}

	public void setDidA(String didA) {
		this.didA = didA;
	}

	public String getDidB() {
		return didB;
	}

	public void setDidB(String didB) {
		this.didB = didB;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void insereCircuito(){
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			if((this.statusA=="s")&&(this.statusB=="sB")){
			String insereCircuito = "insert into circuito (circuitoNumero,codSdhA,codSdhB,codPdhA,codPdhB,codCidadeA,codCidadeB,slotA,slotB,portaA,portaB,didA,didB,observacoesCircuito,statusA,statusB,cliente,cabeceira,codEstacaoA,codEstacaoB,statusCircuito) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stm = conecta.con.prepareStatement(insereCircuito);
			stm.setString(1, this.numeroCircuito);
			stm.setInt(2, this.equipamentoSDHA);
			stm.setInt(3, this.equipamentoSDHB);
			stm.setInt(4, 1);
			stm.setInt(5, 1);
			stm.setInt(6, this.cidadeA);
			stm.setInt(7, this.cidadeB);
			stm.setString(8, this.slotA);
			stm.setString(9, this.slotB);
			stm.setString(10, this.portaA);
			stm.setString(11, this.portaB);
			stm.setString(12, this.didA);
			stm.setString(13, this.didB);
			stm.setString(14, this.obervacoes);
			stm.setString(15, this.statusA);
			stm.setString(16, this.statusB);
			stm.setString(17, this.cliente);
			stm.setString(18, this.cabeceira);
			stm.setInt(19, this.estacaoA);
			stm.setInt(20, this.estacaoB);
			stm.setString(21,  this.statusCircuito);
			
			stm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Circuito Cadastrado Com Sucesso");
			stm.close();
			conecta.con.close();			
			}else if((this.statusA=="p")&&(this.statusB=="pB")){
				String insereCircuito = "insert into circuito (circuitoNumero,codSdhA,codSdhB,codPdhA,codPdhB,codCidadeA,codCidadeB,slotA,slotB,portaA,portaB,didA,didB,observacoesCircuito,statusA,statusB,cliente,cabeceira,codEstacaoA,codEstacaoB,statusCircuito) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stm = conecta.con.prepareStatement(insereCircuito);
				stm.setString(1, this.numeroCircuito);
				stm.setInt(2, 1);
				stm.setInt(3, 1);
				stm.setInt(4, this.equipamentoPDHA);
				stm.setInt(5, this.equipamentoPDHB);
				stm.setInt(6, this.cidadeA);
				stm.setInt(7, this.cidadeB);
				stm.setString(8, null);
				stm.setString(9, null);
				stm.setString(10, this.portaA);
				stm.setString(11, this.portaB);
				stm.setString(12, this.didA);
				stm.setString(13, this.didB);
				stm.setString(14, this.obervacoes);
				stm.setString(15, this.statusA);
				stm.setString(16, this.statusB);
				stm.setString(17, this.cliente);
				stm.setString(18, this.cabeceira);
				stm.setInt(19, this.estacaoA);
				stm.setInt(20, this.estacaoB);
				stm.setString(21,  this.statusCircuito);
				
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Circuito Cadastrado Com Sucesso");
				stm.close();
				conecta.con.close();
			}else if((this.statusA=="p")&&(this.statusB=="sB")){
				String insereCircuito = "insert into circuito (circuitoNumero,codSdhA,codSdhB,codPdhA,codPdhB,codCidadeA,codCidadeB,slotA,slotB,portaA,portaB,didA,didB,observacoesCircuito,statusA,statusB,cliente,cabeceira,codEstacaoA,codEstacaoB,statusCircuito) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stm = conecta.con.prepareStatement(insereCircuito);
				stm.setString(1, this.numeroCircuito);
				stm.setInt(2, 1);
				stm.setInt(3, this.equipamentoSDHB);
				stm.setInt(4, this.equipamentoPDHA);
				stm.setInt(5, 1);
				stm.setInt(6, this.cidadeA);
				stm.setInt(7, this.cidadeB);
				stm.setString(8, null);
				stm.setString(9, this.slotB);
				stm.setString(10, this.portaA);
				stm.setString(11, this.portaB);
				stm.setString(12, this.didA);
				stm.setString(13, this.didB);
				stm.setString(14, this.obervacoes);
				stm.setString(15, this.statusA);
				stm.setString(16, this.statusB);
				stm.setString(17, this.cliente);
				stm.setString(18, this.cabeceira);
				stm.setInt(19, this.estacaoA);
				stm.setInt(20, this.estacaoB);
				stm.setString(21,  this.statusCircuito);
				
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Circuito Cadastrado Com Sucesso");
				stm.close();
				conecta.con.close();
				
			}else if((this.statusA=="s")&&(this.statusB=="pB")){
				
				String insereCircuito = "insert into circuito (circuitoNumero,codSdhA,codSdhB,codPdhA,codPdhB,codCidadeA,codCidadeB,slotA,slotB,portaA,portaB,didA,didB,observacoesCircuito,statusA,statusB,cliente,cabeceira,codEstacaoA,codEstacaoB,statusCircuito) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stm = conecta.con.prepareStatement(insereCircuito);
				stm.setString(1, this.numeroCircuito);
				stm.setInt(2, this.equipamentoSDHA);
				stm.setInt(3, 1);
				stm.setInt(4, 1);
				stm.setInt(5, this.equipamentoPDHB);
				stm.setInt(6, this.cidadeA);
				stm.setInt(7, this.cidadeB);
				stm.setString(8, this.slotA);
				stm.setString(9, null);
				stm.setString(10, this.portaA);
				stm.setString(11, this.portaB);
				stm.setString(12, this.didA);
				stm.setString(13, this.didB);
				stm.setString(14, this.obervacoes);
				stm.setString(15, this.statusA);
				stm.setString(16, this.statusB);
				stm.setString(17, this.cliente);
				stm.setString(18, this.cabeceira);
				stm.setInt(19, this.estacaoA);
				stm.setInt(20, this.estacaoB);
				stm.setString(21,  this.statusCircuito);
				
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Circuito Cadastrado Com Sucesso");
				stm.close();
				conecta.con.close();
			}
			
			
		}catch(SQLException ev){
			JOptionPane.showMessageDialog(null, ev);
		}
		
	}//Fim do método de inserir circuito
	
	
	public void atualizaCircuito(String circuito,String nomeCabeceira){
		
		ResultSet rs = null;		
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();			
			
			if((this.statusA=="s")&&(this.statusB=="sB")){
			String atualizaCircuito = "update circuito set circuitoNumero=?,codSdhA=?,codSdhB=?,codPdhA=?,codPdhB=?,codCidadeA=?,codCidadeB=?,slotA=?,slotB=?,portaA=?,portaB=?,didA=?,didB=?,observacoesCircuito=?,statusA=?,statusB=?,cliente=?,cabeceira=?,codEstacaoA=?,codEstacaoB=?, statusCircuito=? where circuitoNumero='"+circuito+"'";
			PreparedStatement stm = conecta.con.prepareStatement(atualizaCircuito);
			stm.setString(1, this.numeroCircuito);
			stm.setInt(2, this.equipamentoSDHA);
			stm.setInt(3, this.equipamentoSDHB);
			stm.setInt(4, 1);
			stm.setInt(5, 1);
			stm.setInt(6, this.cidadeA);
			stm.setInt(7, this.cidadeB);
			stm.setString(8, this.slotA);
			stm.setString(9, this.slotB);
			stm.setString(10, this.portaA);
			stm.setString(11, this.portaB);
			stm.setString(12, this.didA);
			stm.setString(13, this.didB);
			stm.setString(14, this.obervacoes);
			stm.setString(15, this.statusA);
			stm.setString(16, this.statusB);
			stm.setString(17, this.cliente);
			stm.setString(18, this.cabeceira);
			stm.setInt(19, this.estacaoA);
			stm.setInt(20, this.estacaoB);
			stm.setString(21,  this.statusCircuito);
			
			stm.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Circuito Atualizado Com Sucesso");
			
			stm.close();
			conecta.con.close();			
			}else if((this.statusA=="p")&&(this.statusB=="pB")){
				String atualizaCircuito = "update circuito set circuitoNumero=?,codSdhA=?,codSdhB=?,codPdhA=?,codPdhB=?,codCidadeA=?,codCidadeB=?,slotA=?,slotB=?,portaA=?,portaB=?,didA=?,didB=?,observacoesCircuito=?,statusA=?,statusB=?,cliente=?,cabeceira=?,codEstacaoA=?,codEstacaoB=?, statusCircuito=? where circuitoNumero='"+circuito+"'";
				PreparedStatement stm = conecta.con.prepareStatement(atualizaCircuito);
				stm.setString(1, this.numeroCircuito);
				stm.setInt(2, 1);
				stm.setInt(3, 1);
				stm.setInt(4, this.equipamentoPDHA);
				stm.setInt(5, this.equipamentoPDHB);
				stm.setInt(6, this.cidadeA);
				stm.setInt(7, this.cidadeB);
				stm.setString(8, null);
				stm.setString(9, null);
				stm.setString(10, this.portaA);
				stm.setString(11, this.portaB);
				stm.setString(12, this.didA);
				stm.setString(13, this.didB);
				stm.setString(14, this.obervacoes);
				stm.setString(15, this.statusA);
				stm.setString(16, this.statusB);
				stm.setString(17, this.cliente);
				stm.setString(18, this.cabeceira);
				stm.setInt(19, this.estacaoA);
				stm.setInt(20, this.estacaoB);
				stm.setString(21,  this.statusCircuito);
				
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Circuito Atualizado Com Sucesso");
				stm.close();
				conecta.con.close();
			}else if((this.statusA=="p")&&(this.statusB=="sB")){
				String atualizaCircuito = "update circuito set circuitoNumero=?,codSdhA=?,codSdhB=?,codPdhA=?,codPdhB=?,codCidadeA=?,codCidadeB=?,slotA=?,slotB=?,portaA=?,portaB=?,didA=?,didB=?,observacoesCircuito=?,statusA=?,statusB=?,cliente=?,cabeceira=?,codEstacaoA=?,codEstacaoB=?, statusCircuito=? where circuitoNumero='"+circuito+"'";
				PreparedStatement stm = conecta.con.prepareStatement(atualizaCircuito);
				stm.setString(1, this.numeroCircuito);
				stm.setInt(2, 1);
				stm.setInt(3, this.equipamentoSDHB);
				stm.setInt(4, this.equipamentoPDHA);
				stm.setInt(5, 1);
				stm.setInt(6, this.cidadeA);
				stm.setInt(7, this.cidadeB);
				stm.setString(8, null);
				stm.setString(9, this.slotB);
				stm.setString(10, this.portaA);
				stm.setString(11, this.portaB);
				stm.setString(12, this.didA);
				stm.setString(13, this.didB);
				stm.setString(14, this.obervacoes);
				stm.setString(15, this.statusA);
				stm.setString(16, this.statusB);
				stm.setString(17, this.cliente);
				stm.setString(18, this.cabeceira);
				stm.setInt(19, this.estacaoA);
				stm.setInt(20, this.estacaoB);
				stm.setString(21,  this.statusCircuito);
				
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Circuito Atualizado Com Sucesso");
			
				stm.close();
				conecta.con.close();
				
			}else if((this.statusA=="s")&&(this.statusB=="pB")){
				
				String atualizaCircuito = "update circuito set circuitoNumero=?,codSdhA=?,codSdhB=?,codPdhA=?,codPdhB=?,codCidadeA=?,codCidadeB=?,slotA=?,slotB=?,portaA=?,portaB=?,didA=?,didB=?,observacoesCircuito=?,statusA=?,statusB=?,cliente=?,cabeceira=?,codEstacaoA=?,codEstacaoB=?, statusCircuito=? where circuitoNumero='"+circuito+"'";
				PreparedStatement stm = conecta.con.prepareStatement(atualizaCircuito);
				stm.setString(1, this.numeroCircuito);
				stm.setInt(2, this.equipamentoSDHA);
				stm.setInt(3, 1);
				stm.setInt(4, 1);
				stm.setInt(5, this.equipamentoPDHB);
				stm.setInt(6, this.cidadeA);
				stm.setInt(7, this.cidadeB);
				stm.setString(8, this.slotA);
				stm.setString(9, null);
				stm.setString(10, this.portaA);
				stm.setString(11, this.portaB);
				stm.setString(12, this.didA);
				stm.setString(13, this.didB);
				stm.setString(14, this.obervacoes);
				stm.setString(15, this.statusA);
				stm.setString(16, this.statusB);
				stm.setString(17, this.cliente);
				stm.setString(18, this.cabeceira);
				stm.setInt(19, this.estacaoA);
				stm.setInt(20, this.estacaoB);
				stm.setString(21,  this.statusCircuito);
				
				stm.executeUpdate();
				JOptionPane.showMessageDialog(null, "Circuito Atualizado Com Sucesso");
				stm.close();
				conecta.con.close();
			}
			
			
		}catch(SQLException ev){
			JOptionPane.showMessageDialog(null, ev);
		}
		//Fim do método caso cabeceira seja nulo
		
		
	}//Fim do método de atualizar circuito
	
	public boolean verificaCircuito(String circuito){
		
		boolean retorno = false;
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaCircuito = "select * from circuito where circuitoNumero='"+circuito+"'";
			rs = conecta.stm.executeQuery(buscaCircuito);
			
			while(rs.next()){
				if(circuito.equals(rs.getString("circuitoNumero"))){
					retorno = true;
					break;
				}
			}
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return retorno;
		
	}//Fim do método de verificar se circuito já existe

}
