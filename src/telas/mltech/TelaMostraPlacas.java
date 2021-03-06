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
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class TelaMostraPlacas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JRadioButton rdbtnDslamZte;
	private JRadioButton rdbtnDslamNec;
	private JRadioButton rdbtnMux;
	private JRadioButton rdbtnDslamHuawei;
	private JRadioButton rdbtnPdh;
	private JRadioButton rdbtnSDH;
	private JRadioButton rdbtnCx;
	private JRadioButton rdbtnFilial;
	private JRadioButton rdbtnPlantaInterna;
	private JRadioButton rdbtnReparo;
	private JComboBox comboBoxModelo;
	public String novoComentario;
	private JRadioButton rdbtnEnviadoPara;
	private JTextField textFieldSerial;
	private JRadioButton rdbtnDwdm;

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
	}//Fim do m�todo de preencher a tabela com a op��o FILIAL
	
	
	public void preencherTabelaPlanta(String sql){
		
		ResultSet rs = null;
		ArrayList dados = new ArrayList();
		String[] colunas = new String[]{"Modelo","Serial","Observa��es"};
		
		
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
		
	}//Fim do m�todo preecnherTabelaEnvio

	/**
	 * Create the frame.
	 */
	public TelaMostraPlacas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMostraPlacas.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 707, 517);
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
		lblModeloDePlaca.setBounds(20, 201, 107, 16);
		panel.add(lblModeloDePlaca);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "T\u00E9cnica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 101, 603, 87);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		rdbtnSDH = new JRadioButton("SDH");
		
		rdbtnSDH.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				
				if(rdbtnSDH.isSelected()){
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnPdh.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnDwdm.setSelected(false);
					
					
					String status = "SDH";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnSDH.setBounds(8, 20, 63, 25);
		panel_2.add(rdbtnSDH);
		
		rdbtnCx = new JRadioButton("CX");
		rdbtnCx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(rdbtnCx.isSelected()){
					
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnPdh.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnSDH.setSelected(false);	
					rdbtnDwdm.setSelected(false);
					
					
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
		
		rdbtnPdh = new JRadioButton("PDH");
		rdbtnPdh.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				
				if(rdbtnPdh.isSelected()){
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnSDH.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnDwdm.setSelected(false);
					
					String status = "PDH";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnPdh.setBounds(100, 20, 68, 25);
		panel_2.add(rdbtnPdh);
		
		rdbtnMux = new JRadioButton("MUX");
		rdbtnMux.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {				
				
				if(rdbtnMux.isSelected()){
					
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnSDH.setSelected(false);
					rdbtnPdh.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnDwdm.setSelected(false);
					
					String status = "MUX";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnMux.setBounds(208, 20, 82, 25);
		panel_2.add(rdbtnMux);
		
		rdbtnDslamHuawei = new JRadioButton("DSLAM Huawei");
		rdbtnDslamHuawei.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				
				if(rdbtnDslamHuawei.isSelected()){
					
					//Desmarcar os outros RadioButton
					rdbtnPdh.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnSDH.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnDwdm.setSelected(false);
					
					String status = "HUAWEI";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnDslamHuawei.setBounds(100, 50, 141, 25);
		panel_2.add(rdbtnDslamHuawei);
		
		rdbtnDslamNec = new JRadioButton("DSLAM Nec");
		rdbtnDslamNec.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(rdbtnDslamNec.isSelected()){
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnPdh.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnSDH.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnDwdm.setSelected(false);
					
					String status = "NEC";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnDslamNec.setBounds(257, 50, 127, 25);
		panel_2.add(rdbtnDslamNec);
		
		rdbtnDslamZte = new JRadioButton("DSLAM ZTE");
		rdbtnDslamZte.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(rdbtnDslamZte.isSelected()){
					
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnPdh.setSelected(false);
					rdbtnSDH.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnDwdm.setSelected(false);
					
					String status = "ZTE";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnDslamZte.setBounds(306, 20, 127, 25);
		panel_2.add(rdbtnDslamZte);
		
		rdbtnDwdm = new JRadioButton("DWDM");
		rdbtnDwdm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				
				if(rdbtnDwdm.isSelected()){
					
					//Desmarcar os outros RadioButton
					rdbtnDslamHuawei.setSelected(false);
					rdbtnDslamNec.setSelected(false);
					rdbtnDslamZte.setSelected(false);
					rdbtnSDH.setSelected(false);
					rdbtnMux.setSelected(false);
					rdbtnCx.setSelected(false);
					rdbtnPdh.setSelected(false);
					
					String status = "DWDM";	
					ResultSet rs = null;
					comboBoxModelo.removeAllItems();
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaModeloPlaca = "select * from modeloplaca where tecnicaModelo='"+status+"'";
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
		rdbtnDwdm.setBounds(393, 50, 93, 25);
		panel_2.add(rdbtnDwdm);
		
		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBounds(20, 222, 172, 22);
		panel.add(comboBoxModelo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 357, 630, 147);
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
				
				//Verifica se bot�o foi clicado sem nenhuma op��o selecionada
				if((!(rdbtnFilial.isSelected()))&&(!(rdbtnPlantaInterna.isSelected()))&&(!(rdbtnReparo.isSelected()))&&(!(rdbtnEnviadoPara.isSelected()))){
					JOptionPane.showMessageDialog(null, "Selecione Uma das Op��es Em \"Busca Por:\"");
				}else{
						if(rdbtnFilial.isSelected()){
							
							//Buscar o �ndice do equipamento de TX selecionado no comboBox
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
		button.setBounds(223, 203, 92, 41);
		panel.add(button);
		
		JLabel lblPesquisarPorSerial = new JLabel("Pesquisar Por Serial:");
		lblPesquisarPorSerial.setBounds(20, 269, 157, 16);
		panel.add(lblPesquisarPorSerial);
		
		textFieldSerial = new JTextField();
		textFieldSerial.setBounds(20, 298, 172, 22);
		panel.add(textFieldSerial);
		textFieldSerial.setColumns(10);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldSerial.getText() == ""){
					JOptionPane.showMessageDialog(null, "Nenhum Serial Digitado");
				}else{
					TelaPlaca novaPlaca = new TelaPlaca();					
					novaPlaca.recebeSerial(textFieldSerial.getText());
					novaPlaca.setVisible(true);
				}
			}
		});
		button_1.setIcon(new ImageIcon(TelaMostraPlacas.class.getResource("/imagens/mltech/Pesquisar.png")));
		button_1.setBounds(218, 279, 97, 41);
		panel.add(button_1);
	}
}
