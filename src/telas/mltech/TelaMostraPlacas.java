package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import classes.mltech.ConectaBanco;
import classes.mltech.ModeloTabela;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaMostraPlacas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JRadioButton rdbtnTx;
	private JRadioButton rdbtnDados;
	private JRadioButton rdbtnCx;
	private JRadioButton rdbtnFilial;
	private JRadioButton rdbtnPlantaInterna;
	private JRadioButton rdbtnReparo;
	private JComboBox comboBoxModelo;
	public String novoComentario;
	private JRadioButton rdbtnEnviadoPara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMostraPlacas frame = new TelaMostraPlacas();
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
		String[] colunas = new String[]{"Modelo","Serial","Filial"};
		
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			rs = conecta.stm.executeQuery(sql);
			
			while(rs.next()){
				dados.add(new Object[]{(String)comboBoxModelo.getSelectedItem(),rs.getString("serialPlaca"),rs.getString("almoxPlaca")});
			};
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Erro ao Preencher a Tabela");
		}
		
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(209);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(209);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(209);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}//Fim do método de preencher a tabela com a opção FILIAL
	
	
	public void preencherTabelaPlanta(String sql){
		
		ResultSet rs = null;
		ArrayList dados = new ArrayList();
		String[] colunas = new String[]{"Modelo","Serial","Observações"};
		
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			rs = conecta.stm.executeQuery(sql);
			
			while(rs.next()){
				dados.add(new Object[]{(String)comboBoxModelo.getSelectedItem(),rs.getString("serialPlaca"),rs.getString("ultimoComentario")});
			};
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(209);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(209);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(209);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	public void preencherTabelaEnvio(String sql){
		
		int indiceCidade = 0;
		String nomeCidade = null;
		ResultSet rs = null;
		ResultSet RS = null;
		ArrayList dados = new ArrayList();
		String[] colunas = new String[]{"Modelo","Serial","Enviado Para"};
		
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			rs = conecta.stm.executeQuery(sql);			
			
			while(rs.next()){	
				indiceCidade = rs.getInt("cidade");
				
				String buscaCidade = "select * from cidade where idcidade='"+indiceCidade+"'";
				RS = conecta.stm.executeQuery(buscaCidade);			
				
				while(RS.next()){				
				dados.add(new Object[]{(String)comboBoxModelo.getSelectedItem(),rs.getString("serialPlaca"),RS.getString("cidadeNome")});
				}
			};
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(209);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(209);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(209);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		
	}//Fim do método preecnherTabelaEnvio

	/**
	 * Create the frame.
	 */
	public TelaMostraPlacas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMostraPlacas.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 640, 382);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 119, 26);
		panel.add(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSobre novaTelaSobre = new TelaSobre();
				novaTelaSobre.setVisible(true);
			}
		});
		mnSobre.add(mntmSobre);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Busca Por:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 43, 613, 58);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnFilial = new JRadioButton("Filial");
		rdbtnFilial.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnFilial.isSelected()){
					rdbtnPlantaInterna.setSelected(false);
					rdbtnReparo.setSelected(false);
					rdbtnEnviadoPara.setSelected(false);
				}
			}
		});
		rdbtnFilial.setBounds(20, 24, 80, 25);
		panel_1.add(rdbtnFilial);
		
		rdbtnPlantaInterna = new JRadioButton("Planta Interna");
		rdbtnPlantaInterna.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnPlantaInterna.isSelected()){
					rdbtnFilial.setSelected(false);
					rdbtnReparo.setSelected(false);
					rdbtnEnviadoPara.setSelected(false);
				}
			}
		});
		rdbtnPlantaInterna.setBounds(112, 24, 127, 25);
		panel_1.add(rdbtnPlantaInterna);
		
		rdbtnReparo = new JRadioButton("Reparo");
		rdbtnReparo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnReparo.isSelected()){
					rdbtnFilial.setSelected(false);
					rdbtnPlantaInterna.setSelected(false);
					rdbtnEnviadoPara.setSelected(false);
				}
			}
		});
		rdbtnReparo.setBounds(250, 24, 94, 25);
		panel_1.add(rdbtnReparo);
		
		rdbtnEnviadoPara = new JRadioButton("Enviado Para");
		rdbtnEnviadoPara.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnEnviadoPara.isSelected()){
					rdbtnFilial.setSelected(false);
					rdbtnPlantaInterna.setSelected(false);
					rdbtnReparo.setSelected(false);
				}
			}
		});
		rdbtnEnviadoPara.setBounds(348, 24, 127, 25);
		panel_1.add(rdbtnEnviadoPara);
		
		JLabel lblModeloDePlaca = new JLabel("Modelo de Placa");
		lblModeloDePlaca.setBounds(218, 101, 107, 16);
		panel.add(lblModeloDePlaca);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "T\u00E9cnica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 101, 179, 87);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		rdbtnTx = new JRadioButton("TX");
		rdbtnTx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnTx.isSelected()){
					rdbtnCx.setSelected(false);
					rdbtnDados.setSelected(false);
					String status = "TX";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where statusModelo='"+status+"'";
						rs = conecta.stm.executeQuery(buscaModeloPlaca);
						
						while(rs.next()){
							comboBoxModelo.addItem(rs.getString("partNumberModelo"));
						}						
						
						
					}catch(SQLException ev){
						
					}					
					
				}//Fim do primeiro if
				else{
					comboBoxModelo.removeAllItems();
				}
			}
		});
		rdbtnTx.setBounds(8, 20, 63, 25);
		panel_2.add(rdbtnTx);
		
		rdbtnCx = new JRadioButton("CX");
		rdbtnCx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnCx.isSelected()){
					rdbtnTx.setSelected(false);
					rdbtnDados.setSelected(false);
					String status = "CX";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where statusModelo='"+status+"'";
						rs = conecta.stm.executeQuery(buscaModeloPlaca);
						
						while(rs.next()){
							comboBoxModelo.addItem(rs.getString("partNumberModelo"));
						}							
						
					}catch(SQLException ev){
						
					}					
					
				}//Fim do primeiro if
				else{
					comboBoxModelo.removeAllItems();
				}
			}
		});
		rdbtnCx.setBounds(8, 50, 68, 25);
		panel_2.add(rdbtnCx);
		
		rdbtnDados = new JRadioButton("Dados");
		rdbtnDados.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnDados.isSelected()){
					rdbtnCx.setSelected(false);
					rdbtnTx.setSelected(false);
					String status = "DADOS";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where statusModelo='"+status+"'";
						rs = conecta.stm.executeQuery(buscaModeloPlaca);
						
						while(rs.next()){
							comboBoxModelo.addItem(rs.getString("partNumberModelo"));
						}							
						
					}catch(SQLException ev){
						
					}					
					
				}//Fim do primeiro if
				else{
					comboBoxModelo.removeAllItems();
				}
			}
		});
		rdbtnDados.setBounds(75, 20, 82, 25);
		panel_2.add(rdbtnDados);
		
		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(218, 124, 172, 22);
		panel.add(comboBoxModelo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 222, 630, 147);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				String serialPlaca = model.getValueAt(index, 1).toString();
				TelaPlaca nova = new TelaPlaca();
				nova.recebeSerial(serialPlaca);
				nova.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String modeloPlaca = (String)comboBoxModelo.getSelectedItem();
				ResultSet rs = null;
				int indiceTX = 0;
				String statusDaPlaca = "Almox";
				ConectaBanco conecta = new ConectaBanco();
				
				//Verifica se botão foi clicado sem nenhuma opção selecionada
				if((!(rdbtnFilial.isSelected()))&&(!(rdbtnPlantaInterna.isSelected()))&&(!(rdbtnReparo.isSelected()))&&(!(rdbtnEnviadoPara.isSelected()))){
					JOptionPane.showMessageDialog(null, "Selecione Uma das Opções Em \"Busca Por:\"");
				}else{
						if(rdbtnFilial.isSelected()){
							
							//Buscar o índice do equipamento de TX selecionado no comboBox
							try{								
								conecta.conectaBanco();								
								String buscaIndiceTX = "select * from modeloplaca where partNumberModelo='"+modeloPlaca+"'";
								rs = conecta.stm.executeQuery(buscaIndiceTX);
								
								while(rs.next()){
									indiceTX = rs.getInt("idModelo");
								}
								
								preencherTabela("select * from placa where modelo='"+indiceTX+"' and statusPlaca='"+statusDaPlaca+"'");
								
								
							}catch(SQLException e){
								
							}							
							
						}else if(rdbtnPlantaInterna.isSelected()){
							String statusPlaca = "Planta";
							
							try{
							String buscaIndiceTX = "select * from modeloplaca where partNumberModelo='"+modeloPlaca+"'";
							rs = conecta.stm.executeQuery(buscaIndiceTX);
							
							while(rs.next()){
								indiceTX = rs.getInt("idModelo");
							}
							
							preencherTabelaPlanta("select * from placa where modelo='"+indiceTX+"' and statusPlaca='"+statusPlaca+"'");
							
							}catch(SQLException e){
								JOptionPane.showMessageDialog(null, e);
							}						
							
						}else if(rdbtnReparo.isSelected()){
							String reparo = "Reparo";
							
							try{
								String buscaIndicePlaca = "select * from modeloplaca where partNumberModelo='"+modeloPlaca+"'";
								rs = conecta.stm.executeQuery(buscaIndicePlaca);
								
								while(rs.next()){
									indiceTX = rs.getInt("idModelo");
								}
								
								preencherTabelaPlanta("select * from placa where modelo='"+indiceTX+"' and statusPlaca='"+reparo+"'");								
								
							}catch(SQLException e){
								JOptionPane.showMessageDialog(null, e);
							}
							
							
						}else{
							String statusPlaca = "Filial";
							
							try{
								String buscaIndicePlaca = "select * from modeloplaca where partNumberModelo='"+modeloPlaca+"'";
								rs = conecta.stm.executeQuery(buscaIndicePlaca);
								
								while(rs.next()){
									indiceTX = rs.getInt("idModelo");
								}
								
								preencherTabelaEnvio("select * from placa where modelo='"+indiceTX+"' and statusPlaca='"+statusPlaca+"'");
								
								
							}catch(SQLException e){
								JOptionPane.showMessageDialog(null, e);
							}							
							
						}					
				}
			}
		});
		button.setToolTipText("Pesquisar");
		button.setIcon(new ImageIcon(TelaMostraPlacas.class.getResource("/imagens/mltech/Pesquisar.png")));
		button.setBounds(536, 168, 92, 41);
		panel.add(button);
	}
}
