package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import classes.mltech.ConectaBanco;
import classes.mltech.ModeloTabela;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMostraCircuito extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxCidade;
	private JComboBox comboBoxEstacao;
	private JComboBox comboBoxEquipamento;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMostraCircuito frame = new TelaMostraCircuito();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void preencherTabela(String sql){
		
		ResultSet rs = null;
		ArrayList dados = new ArrayList();
		String[] colunas = new String[]{"Circuito","Cliente"};
		
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			rs = conecta.stm.executeQuery(sql);
			
			while(rs.next()){
				dados.add(new Object[]{rs.getString("circuitoNumero"),rs.getString("cliente")});
			};
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Erro ao Preencher a Tabela");
		}
		
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
public void buscaCidade(){
		
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaCidade = "select * from cidade order by cidadeNome asc";
			rs = conecta.stm.executeQuery(buscaCidade);
			
			while(rs.next()){
				comboBoxCidade.addItem(rs.getString("cidadeNome"));				
			}			
			
		}catch(SQLException e){
			
		}		
		
	}//Fim do método de buscar cidade
	
	
	

	/**
	 * Create the frame.
	 */
	public TelaMostraCircuito() {
		setTitle("Lista de Circuitos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMostraCircuito.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 620, 359);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton buttonPesquisar = new JButton("");
		buttonPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ResultSet rs = null;
				ResultSet RS = null;
				ResultSet RS1 = null;
				int indiceSDH = 0;
				int indiceRadio = 0;
				int indiceEstacao = 0;
				int indiceCidade = 0;
				String indexPDH = null;
				
				
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					String buscaIndiceSDH = "select * from sdh where sdhNome='"+comboBoxEquipamento.getSelectedItem()+"'";
					rs = conecta.stm.executeQuery(buscaIndiceSDH);
					
					if(!rs.next()){
						
						//Verifica se foi selecionado um SDH no comboBoxEquipamento. 
						
							//Aqui vai a lógica para preencher a tabela com circuitos de pdh caso seja selecionado pdh no comboBox
							String buscaPDH = "select * from radio where modeloRadio='"+comboBoxEquipamento.getSelectedItem()+"'";
							RS = conecta.stm.executeQuery(buscaPDH);
							
							while(RS.next()){
								indiceRadio = RS.getInt("idRadio");
							}							
							
							String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxCidade.getSelectedItem()+"'";
							RS1 = conecta.stm.executeQuery(buscaIndiceCidade);
							
							while(RS1.next()){
								indiceCidade = RS1.getInt("idcidade");
								}								
							preencherTabela( "select * from circuito where codPdhA='"+indiceRadio+"' and codCidadeA='"+indiceCidade+"'");
							
						
					}else{
						//Aqui vai a lógica para preencher a tabela com circuitos de sdh
						
						indiceSDH = rs.getInt("idsdh");						
						
						
						String buscaIndiceEstacao = "select * from estacao where estacaoSigla='"+comboBoxEstacao.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaIndiceEstacao);
						
						while(rs.next()){
							indiceEstacao = rs.getInt("idEstacao");
						}
						
						
						if((indiceEstacao==1)||(indiceEstacao==6)||(indiceEstacao==56)){
							preencherTabela("select * from circuito where codEstacaoB='"+indiceEstacao+"' and codSdhB='"+indiceSDH+"'");
						}else{
							preencherTabela("select * from circuito where codEstacaoA='"+indiceEstacao+"' and codSdhA='"+indiceSDH+"'");
						}
						
						
						
					}
					
					
				}catch(SQLException e){
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}
		});
		buttonPesquisar.setToolTipText("Pesquisar");
		buttonPesquisar.setIcon(new ImageIcon(TelaMostraCircuito.class.getResource("/imagens/mltech/Pesquisar.png")));
		buttonPesquisar.setBounds(0, 0, 48, 34);
		panel.add(buttonPesquisar);
		
		JButton buttonSair = new JButton("");
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonSair.setToolTipText("Sair");
		buttonSair.setIcon(new ImageIcon(TelaMostraCircuito.class.getResource("/imagens/mltech/Sair.png")));
		buttonSair.setBounds(45, 0, 48, 34);
		panel.add(buttonSair);
		
		JLabel lblLocalidade = new JLabel("Localidade");
		lblLocalidade.setBounds(17, 59, 97, 16);
		panel.add(lblLocalidade);
		
		comboBoxCidade = new JComboBox();
		comboBoxCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = null;
				int indiceCidade = 0;
				String nomeEstacao = null;
				comboBoxEstacao.removeAllItems();
				comboBoxEquipamento.removeAllItems();
				
				
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxCidade.getSelectedItem()+"'";
					rs = conecta.stm.executeQuery(buscaIndiceCidade);
					
					while(rs.next()){
						indiceCidade = rs.getInt("idcidade");
					}
					
					String buscaEstacao = "select * from estacao where idCidade='"+indiceCidade+"'";
					rs = conecta.stm.executeQuery(buscaEstacao);
					
					while(rs.next()){
						comboBoxEstacao.addItem(rs.getString("estacaoSigla"));
					}
					
				}catch(SQLException e){
					
				}
				
			}
		});
		comboBoxCidade.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxCidade.setBounds(17, 77, 207, 22);
		panel.add(comboBoxCidade);
		
		JLabel lblEstao = new JLabel("Esta\u00E7\u00E3o");
		lblEstao.setBounds(17, 112, 56, 16);
		panel.add(lblEstao);
		
		comboBoxEstacao = new JComboBox();
		comboBoxEstacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				ResultSet RS = null;
				int indiceEstacao = 0;
				int indiceRadio = 0;
				int indiceCidade = 0;
				int indiceRadioAnterior = 0;
				comboBoxEquipamento.removeAllItems();
				
				
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					//Buscar o índice da estação selecionada no comboBoxEstação
					String buscaIndiceEstacao = "select * from estacao where estacaoSigla='"+comboBoxEstacao.getSelectedItem()+"'";
					rs = conecta.stm.executeQuery(buscaIndiceEstacao);
					
					while(rs.next()){
					indiceEstacao = rs.getInt("idEstacao");
					}
					
					//Com o índice da estação, verificar os SDH pertencentes àquela estação
					String buscaSDH = "select * from sdh where idEstacao='"+indiceEstacao+"'";
					rs = conecta.stm.executeQuery(buscaSDH);
					
					//Caso tenha sdh pertences a estação, serão inseridos no comboBoxEquipamento nessa parte.
					while(rs.next()){
						comboBoxEquipamento.addItem(rs.getString("sdhNome"));
					}
					
					if(indiceEstacao!=1){
						//Descobrir o índice da cidade
						String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxCidade.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaIndiceCidade);
						
						while(rs.next()){
							indiceCidade = rs.getInt("idcidade");
						}						
						
						
					String buscaIndiceRadio = "select * from circuito where codCidadeA='"+indiceCidade+"'";
					rs = conecta.stm.executeQuery(buscaIndiceRadio);
					
					while(rs.next()){
						indiceRadio = rs.getInt("codPdhA");
						
						String buscaRadio = "select * from radio where idRadio='"+indiceRadio+"'";
						RS = conecta.stm.executeQuery(buscaRadio);
						
						while(RS.next()){
							if((indiceRadio!=indiceRadioAnterior)&&(indiceRadio!=1)){
							comboBoxEquipamento.addItem(RS.getString("modeloRadio"));
							indiceRadioAnterior = indiceRadio;
							}
						}
						
					}//fim da condição while					
					}//fim da condição if para as estações que não sejam SAN.
				}catch(SQLException ev){
					JOptionPane.showMessageDialog(null, ev);
				}
				
				
			}
		});
		comboBoxEstacao.setBounds(17, 131, 90, 22);
		panel.add(comboBoxEstacao);
		
		JLabel lblEquipamentos = new JLabel("Equipamentos");
		lblEquipamentos.setBounds(17, 176, 97, 16);
		panel.add(lblEquipamentos);
		
		comboBoxEquipamento = new JComboBox();
		comboBoxEquipamento.setBounds(17, 194, 142, 22);
		panel.add(comboBoxEquipamento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(283, 60, 325, 286);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cliente", "Circuito"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(103);
		table.getColumnModel().getColumn(1).setPreferredWidth(111);
		
		buscaCidade();
		
	}
}
