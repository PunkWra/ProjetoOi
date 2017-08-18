package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.TextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JYearChooser;

import classes.mltech.ConectaBanco;
import classes.mltech.Placa;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class TelaPlaca extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLocal;
	private JTextField textFieldDescricao;
	private JTextField textFieldSerial;
	private JRadioButton rdbtnAlmox;
	private JRadioButton rdbtnEnvioPReparo;
	private JRadioButton rdbtnPlantaInterna;
	private JRadioButton rdbtnOutraFilial;
	private JComboBox comboBoxEnviado;
	private JComboBox comboBoxEstacao;
	private JComboBox comboBoxUtilizado;
	private JComboBox comboBoxPartNumber;
	private JRadioButton rdbtnTX;
	private JRadioButton rdbtnCx;
	private JRadioButton rdbtnDados;
	private JTextArea textArea;
	private JDateChooser dateChooser;
	private JComboBox comboBoxAlmox;
	private String novoComentario;
	private String observacaoAnterior;
	private JButton buttonAtualizar;
	private String ultimoComentario;
	private JButton buttonSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPlaca frame = new TelaPlaca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void buscaModelos(){
		
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaModelo = "select * from modeloPlaca";
			rs = conecta.stm.executeQuery(buscaModelo);
			
			while(rs.next()){
				comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
			}		
			
			
		}catch(SQLException e){
			
		}
		
	}//Fim do método de busca dos modelos
	
	
	public void insereCidade(){
		
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaCidade = "select * from cidade order by cidadeNome ASC";
			rs = conecta.stm.executeQuery(buscaCidade);
			
			while(rs.next()){
				comboBoxEnviado.addItem(rs.getString("cidadeNome"));
				comboBoxUtilizado.addItem(rs.getString("cidadeNome"));
			}			
			
		}catch(SQLException e){
			
		}		
		
	}//Fim do método de busca das cidades
	
	public void recebeSerial(String novoSerial){
		
		boolean verifica;
		ResultSet rs = null;
		ResultSet RS = null;
		ResultSet RS1 = null;
		String status = null;
		String modelo = null;
		String tecnica = null;
		String cidade = null;
		
		//Pesquisa Se Placa Já Está Cadastrada
		//String serial = JOptionPane.showInputDialog("Informe o Serial da Placa");
							
				Placa placa = new Placa();	
				novoSerial = novoSerial.replace(" ", "").toUpperCase();
				verifica = placa.verificaPlaca(novoSerial);
				
				if(verifica==false){
					JOptionPane.showMessageDialog(null, "Placa Não Cadastrada");
				}else if(verifica==true){
					buttonSalvar.setEnabled(false);
					buttonAtualizar.setEnabled(true);
					
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							
							String buscaStatus = "select * from placa where serialPlaca='"+novoSerial+"'";
							rs = conecta.stm.executeQuery(buscaStatus);
							
							while(rs.next()){
								status=rs.getString("statusPlaca");								
								tecnica = rs.getString("tecnicaPlaca");
								String buscaModelo = "select * from modeloplaca where idModelo='"+rs.getString("modelo")+"'";
								RS = conecta.stm.executeQuery(buscaModelo);
								
								String buscaCidade = "select * from cidade where idcidade='"+rs.getInt("cidade")+"'";
								RS1 = conecta.stm.executeQuery(buscaCidade);
								
								while(RS1.next()){
									cidade = RS1.getString("cidadeNome");
								}
								
								while(RS.next()){
									modelo = RS.getString("partNumberModelo");											
								}
								
							if("Almox".equals(status)){
								
								if("TX".equals(tecnica)){
									rdbtnTX.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("CX".equals(tecnica)){
									rdbtnCx.setSelected(true);
									rdbtnTX.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("DADOS".equals(tecnica)){
									rdbtnDados.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnTX.setSelected(false);
								}
								
								rdbtnAlmox.setSelected(true);
								textFieldLocal.setText(rs.getString("localPlaca"));								
								comboBoxPartNumber.setSelectedItem(modelo);
								textFieldSerial.setText(novoSerial);								
								dateChooser.setDate(rs.getDate("dataEnvio"));
								comboBoxAlmox.setSelectedItem(rs.getString("almoxPlaca"));
								comboBoxEnviado.setSelectedItem("Selecione Cidade");								
								comboBoxUtilizado.setSelectedItem("Selecione Cidade");				
								textArea.setText(rs.getString("observacoes"));										
								
								
							}//Fim da condição caso o status seja Almox
							else
							//Início da condição caso o status seja Filial
							if("Filial".equals(status)){
								
								if("TX".equals(tecnica)){
									rdbtnTX.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("CX".equals(tecnica)){
									rdbtnCx.setSelected(true);
									rdbtnTX.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("DADOS".equals(tecnica)){
									rdbtnDados.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnTX.setSelected(false);
								}
								
								rdbtnOutraFilial.setSelected(true);
								comboBoxPartNumber.setSelectedItem(modelo);
								textFieldSerial.setText(novoSerial);
								dateChooser.setDate(rs.getDate("dataEnvio"));
								comboBoxEnviado.setSelectedItem(cidade);
								textArea.setText(rs.getString("observacoes"));										
								
							}//Fim da condição caso o status seja Filial
							else if("Reparo".equals(status)){
								
								if("TX".equals(tecnica)){
									rdbtnTX.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("CX".equals(tecnica)){
									rdbtnCx.setSelected(true);
									rdbtnTX.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("DADOS".equals(tecnica)){
									rdbtnDados.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnTX.setSelected(false);
								}
								
								rdbtnEnvioPReparo.setSelected(true);
								comboBoxEnviado.setSelectedItem(cidade);
								textArea.setText(rs.getString("observacoes"));
								textFieldSerial.setText(novoSerial);
								comboBoxPartNumber.setSelectedItem(modelo);
								dateChooser.setDate(rs.getDate("dataEnvio"));
								
								
							}//Fim da condição caso o status seja Reparo
							else if("Planta".equals(status)){
								
								if("TX".equals(tecnica)){
									rdbtnTX.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("CX".equals(tecnica)){
									rdbtnCx.setSelected(true);
									rdbtnTX.setSelected(false);
									rdbtnDados.setSelected(false);
								}else if("DADOS".equals(tecnica)){
									rdbtnDados.setSelected(true);
									rdbtnCx.setSelected(false);
									rdbtnTX.setSelected(false);
								}
								
								comboBoxUtilizado.setSelectedItem(cidade);
								textArea.setText(rs.getString("observacoes"));
								comboBoxPartNumber.setSelectedItem(modelo);
								rdbtnPlantaInterna.setSelected(true);
								textFieldSerial.setText(novoSerial);
	
	
							}//Fim da condição caso o status seja Planta
							}//Fim do while
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}								
				}				
		
		
	}//Fim do método de receber o serial da placa
	
	

	/**
	 * Create the frame.
	 */
	public TelaPlaca() {
		setTitle("Cadastro de Placa/M\u00F3dulo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPlaca.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 511);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNovoModelo = new JMenuItem("Novo Modelo");
		mnArquivo.add(mntmNovoModelo);
		
		JMenuItem mntmNovaEstao = new JMenuItem("Nova Esta\u00E7\u00E3o");
		mnArquivo.add(mntmNovaEstao);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dispose();
			}
		});
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		buttonSalvar = new JButton("");
		buttonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Declaração de variáveis que serão utilizadas em quaisqer casos de status
				String serial = textFieldSerial.getText().replace(" ", "").toUpperCase();
				String almox = (String)comboBoxAlmox.getSelectedItem();
				String tecnicaPlaca;
				String cidade = null;
				String estacao = null;
				String status=null;
				String modelo = null;
				int indiceModelo = 0;
				int indiceCidade = 0;
				int indiceEstacao = 0;
				boolean verifica;
				ResultSet rs = null;	
				ResultSet RS = null;
				ResultSet RS1 = null;
				ResultSet rsModelo = null;
				Placa novaPlaca = new Placa();
				if(rdbtnTX.isSelected()){
					tecnicaPlaca="TX";
				}else if(rdbtnCx.isSelected()){
					tecnicaPlaca="CX";
				}else{
					tecnicaPlaca="DADOS";
				}
				
				//Código para inserir placa/módulo no almox de SAN
				if(rdbtnAlmox.isSelected()){
										
					//Início do código
						if((textFieldLocal.getText().isEmpty())||(textFieldSerial.getText().isEmpty())){
							JOptionPane.showMessageDialog(null, "Informe os Campos Solicitados");
						}else{
							verifica = novaPlaca.verificaPlaca(serial);
							
							//Lógica caso a placa já esteja cadastrada
								if(verifica==true){
									JOptionPane.showMessageDialog(null, "Placa Já Cadastrada");
									buttonSalvar.setEnabled(false);
									try{
										ConectaBanco conecta = new ConectaBanco();
										conecta.conectaBanco();
										
										String buscaPlaca = "select * from placa where serialPlaca='"+serial+"'";
										rs = conecta.stm.executeQuery(buscaPlaca);	
										
										while(rs.next()){										
											status = rs.getString("statusPlaca");
											String buscaCidade = "select * from cidade where idcidade='"+rs.getInt("cidade")+"'";
											RS = conecta.stm.executeQuery(buscaCidade);											
											
											while(RS.next()){
												cidade = RS.getString("cidadeNome");	
												String buscaEstacao = "select * from estacao where idCidade='"+RS.getInt("idcidade")+"'";
												RS1 = conecta.stm.executeQuery(buscaEstacao);
												
												while(RS1.next()){
													estacao = RS1.getString("estacaoSigla");
												}
												
												String buscaModelo = "select * from modeloplaca where partnumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
												rsModelo = conecta.stm.executeQuery(buscaModelo);
												
												while(rsModelo.next()){
													modelo = rsModelo.getString("partnumberModelo");
												}
												
											}											
											
										//Puxará informações da placa caso ela esteja no Almox
										if("Almox".equals(status)){
											rdbtnEnvioPReparo.setSelected(false);
											rdbtnOutraFilial.setSelected(false);
											rdbtnPlantaInterna.setSelected(false);
											rdbtnAlmox.setSelected(true);
											textArea.setText(rs.getString("observacoes"));
											textFieldLocal.setText(rs.getString("localPlaca"));
											comboBoxPartNumber.setSelectedItem(modelo);
											textFieldSerial.setText(rs.getString("serialPlaca"));
											comboBoxAlmox.setSelectedItem(rs.getString("almoxPlaca"));
										}else if("Reparo".equals(status)){
											rdbtnOutraFilial.setSelected(false);
											rdbtnPlantaInterna.setSelected(false);
											rdbtnAlmox.setSelected(false);
											rdbtnEnvioPReparo.setSelected(true);
											textFieldLocal.setText("");
											textFieldLocal.setEnabled(false);
											textFieldSerial.setText(rs.getString("serialPlaca"));
											comboBoxEnviado.setSelectedItem(cidade);
											textArea.setText(rs.getString("observacoes"));
											comboBoxPartNumber.setSelectedItem(modelo);
											dateChooser.setDate(rs.getDate("dataEnvio"));
											
										}else if("Filial".equals(status)){
											rdbtnPlantaInterna.setSelected(false);
											rdbtnAlmox.setSelected(false);
											rdbtnEnvioPReparo.setSelected(false);
											rdbtnOutraFilial.setSelected(true);
											textFieldLocal.setText("");
											textFieldLocal.setEnabled(false);
											dateChooser.setDate(rs.getDate("dataEnvio"));
											comboBoxEnviado.setSelectedItem(cidade);
											textArea.setText(rs.getString("observacoes"));											
										}else if("Planta".equals(status)){
											rdbtnAlmox.setSelected(false);
											rdbtnEnvioPReparo.setSelected(false);
											rdbtnOutraFilial.setSelected(false);
											rdbtnPlantaInterna.setSelected(true);
											textFieldLocal.setText("");
											textFieldLocal.setEnabled(false);
											dateChooser.setDate(rs.getDate("dataEnvio"));
											comboBoxUtilizado.setSelectedItem(cidade);
											comboBoxEstacao.setSelectedItem(estacao);
										}										
										}										
										
									}catch(SQLException e){
										JOptionPane.showMessageDialog(null, e);
									}									
									
								}//Fim da verificação caso a placa já esteja cadastrada
								else{	
									
									try{
										ConectaBanco conecta = new ConectaBanco();
										conecta.conectaBanco();
										
										String buscaIndiceModelo = "select * from modeloPlaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
										rs = conecta.stm.executeQuery(buscaIndiceModelo);
										
										while(rs.next()){
											indiceModelo = rs.getInt("idModelo");											
										}										
										
									}catch(SQLException e){
										
									}
									novaPlaca.setSerialPlaca(serial);
									novaPlaca.setLocalPlaca(textFieldLocal.getText().toUpperCase());
									novaPlaca.setDataEnvio(dateChooser.getDate());
									novaPlaca.setObervacoes(textArea.getText());
									novaPlaca.setIndiceModelo(indiceModelo);
									novaPlaca.setStatusPlaca("Almox");
									novaPlaca.setIndiceCidade(1);
									novaPlaca.setIndiceEstacao(1);
									novaPlaca.setTecnicaPlaca(tecnicaPlaca);
									novaPlaca.setAlmoxPlaca(almox);
									novaPlaca.setUltimoComentario(ultimoComentario);
									novaPlaca.inserePlaca();
									JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");
									textArea.setText("");
									textFieldLocal.setText("");
									textFieldSerial.setText("");
									
								}							
						}					
				}//Fim da condicional if para o botão Almox
				
				//Cadastro de Placa Quando Enviada Para Reparo
				if(rdbtnEnvioPReparo.isSelected()){
					
					//Declaração de Variáveis
					verifica = novaPlaca.verificaPlaca(serial);					
					
					//Verifica se a placa já está cadastrada no sistema
					if(verifica==true){
						//JOptionPane.showMessageDialog(null, "Placa Já Cadastrada");
						buttonSalvar.setEnabled(false);
						
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaPlaca = "select * from placa where serialPlaca='"+serial+"'";
							rs = conecta.stm.executeQuery(buscaPlaca);
							
								while(rs.next()){
									     status = rs.getString("statusPlaca");
									     String buscaCidade = "select * from cidade where idcidade='"+rs.getInt("cidade")+"'";
										 RS = conecta.stm.executeQuery(buscaCidade);										 
											
											while(RS.next()){
												cidade = RS.getString("cidadeNome");
												String buscaEstacao = "select * from estacao where idCidade='"+RS.getInt("idcidade")+"'";
												RS1 = conecta.stm.executeQuery(buscaEstacao);
												
												while(RS1.next()){
													estacao=RS1.getString("estacaoSigla");
												}
												
												String buscaModelo = "select * from modeloplaca where partnumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
												rsModelo = conecta.stm.executeQuery(buscaModelo);
												
												while(rsModelo.next()){
													modelo = rsModelo.getString("partnumberModelo");
												}
											}											
											
									     if("Almox".equals(status)){
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(true);
												textArea.setText(rs.getString("observacoes"));
												textFieldLocal.setText(rs.getString("localPlaca"));
												comboBoxPartNumber.setSelectedItem(modelo);
												textFieldSerial.setText(rs.getString("serialPlaca"));
											}else if("Reparo".equals(status)){
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												textFieldSerial.setText(rs.getString("serialPlaca"));
												comboBoxEnviado.setSelectedItem(cidade);
												textArea.setText(rs.getString("observacoes"));
												comboBoxPartNumber.setSelectedItem(modelo);
												dateChooser.setDate(rs.getDate("dataEnvio"));
											}else if("Filial".equals(status)){
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												dateChooser.setDate(rs.getDate("dataEnvio"));
												comboBoxEnviado.setSelectedItem(cidade);
												textArea.setText(rs.getString("observacoes"));	
												dateChooser.setDate(rs.getDate("dataEnvio"));
											}else if("Planta".equals(status)){
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												dateChooser.setDate(rs.getDate("dataEnvio"));
												comboBoxUtilizado.setSelectedItem(cidade);
												comboBoxEstacao.setSelectedItem(estacao);
											}									     
									     
								}//Fim de while							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}							
								
					}//Fim da condicional caso a placa já esteja cadastrada.
					else{
						
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							
							String buscaIndiceModelo = "select * from modeloPlaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
							rs = conecta.stm.executeQuery(buscaIndiceModelo);
							
							while(rs.next()){
								indiceModelo = rs.getInt("idModelo");								
							}										
							
							String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxEnviado.getSelectedItem()+"'";
							RS = conecta.stm.executeQuery(buscaIndiceCidade);
							
							while(RS.next()){
								indiceCidade = RS.getInt("idcidade");
							}
							
						}catch(SQLException e){
							
						}						
						
						novaPlaca.setLocalPlaca(null);
						novaPlaca.setIndiceModelo(indiceModelo);
						novaPlaca.setSerialPlaca(serial);
						novaPlaca.setDataEnvio(dateChooser.getDate());
						novaPlaca.setIndiceCidade(indiceCidade);
						novaPlaca.setIndiceEstacao(1);
						novaPlaca.setObervacoes(textArea.getText());
						novaPlaca.setTecnicaPlaca(tecnicaPlaca);
						novaPlaca.setStatusPlaca("Reparo");
						novaPlaca.setUltimoComentario(ultimoComentario);
						novaPlaca.inserePlaca();
						JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");
						textArea.setText("");
						textFieldSerial.setText("");
						
					}//Fim do else onde se faz o cadastro da placa.			
					
				}//Fim da condicional se o botão de reparo estiver selecionado
				
				//Início da condicional caso o botão da planta interna seja selecionada
				if(rdbtnPlantaInterna.isSelected()){
					
					verifica = novaPlaca.verificaPlaca(serial);
					//Condição caso a placa já esteja cadastrada
					if(verifica==true){						

						//JOptionPane.showMessageDialog(null, "Placa Já Cadastrada");
						
						buttonSalvar.setEnabled(false);
						
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaPlaca = "select * from placa where serialPlaca='"+serial+"'";
							rs = conecta.stm.executeQuery(buscaPlaca);
							 
								while(rs.next()){
									status = rs.getString("statusPlaca");
									String buscaCidade = "select * from cidade where idcidade='"+rs.getInt("cidade")+"'";
									RS = conecta.stm.executeQuery(buscaCidade);
									
									while(RS.next()){
										cidade = RS.getString("cidadeNome");
										
										String buscaEstacao = "select * from estacao where idCidade='"+RS.getInt("idcidade")+"'";
										RS1 = conecta.stm.executeQuery(buscaEstacao);
										
										while(RS1.next()){
											estacao = RS1.getString("estacaoSigla");
										}
										
										String buscaModelo = "select * from modeloplaca where partnumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
										rsModelo = conecta.stm.executeQuery(buscaModelo);
										
										while(rsModelo.next()){
											modelo = rsModelo.getString("partnumberModelo");
										}
									}									
									
									     if("Almox".equals(status)){
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(true);
												textArea.setText(rs.getString("observacoes"));
												textFieldLocal.setText(rs.getString("localPlaca"));
												comboBoxPartNumber.setSelectedItem(modelo);
												textFieldSerial.setText(rs.getString("serialPlaca"));
											}else if("Reparo".equals(status)){
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												textFieldSerial.setText(rs.getString("serialPlaca"));
												comboBoxEnviado.setSelectedItem(cidade);
												textArea.setText(rs.getString("observacoes"));
												comboBoxPartNumber.setSelectedItem(modelo);
												dateChooser.setDate(rs.getDate("dataEnvio"));
											}else if("Filial".equals(status)){
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												dateChooser.setDate(rs.getDate("dataEnvio"));
												comboBoxEnviado.setSelectedItem(cidade);
												textArea.setText(rs.getString("observacoes"));	
												dateChooser.setDate(rs.getDate("dataEnvio"));
											}else if("Planta".equals(status)){
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												dateChooser.setDate(rs.getDate("dataEnvio"));
												comboBoxUtilizado.setSelectedItem(cidade);
												comboBoxEstacao.setSelectedItem(estacao);
											}									     
									     
								}//Fim de while							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}							
						
					}//Fim da condicional caso a placa esteja cadastrada
					//Início da sessão para cadastramento caso não esteja cadastrada
					else{	
						
						if((comboBoxUtilizado.getSelectedItem()=="Selecione Cidade")||(comboBoxEstacao.getSelectedItem()==null)){
							JOptionPane.showMessageDialog(null, "Selecione Os Campos Solicitados");
						}
						else{
							try{
								ConectaBanco conecta = new ConectaBanco();
								conecta.conectaBanco();
								
								String buscaIndiceModelo = "select * from modeloplaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndiceModelo);
								
								while(rs.next()){
									indiceModelo = rs.getInt("idModelo");
								}
								
								String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxUtilizado.getSelectedItem()+"'";
								RS = conecta.stm.executeQuery(buscaIndiceCidade);
								
								while(RS.next()){
									indiceCidade = RS.getInt("idcidade");									
								}
								
								String buscaIndiceEstacao = "select * from estacao where estacaoSigla='"+comboBoxEstacao.getSelectedItem()+"'";
								RS1 = conecta.stm.executeQuery(buscaIndiceEstacao);
								
								while(RS1.next()){
									indiceEstacao = RS1.getInt("idEstacao");									
								}
								
								String buscaModelo = "select * from modeloplaca where partnumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
								rsModelo = conecta.stm.executeQuery(buscaModelo);
								
								while(rsModelo.next()){
									modelo = rsModelo.getString("partnumberModelo");
								}								
								
							}catch(SQLException e){
								
							}						
						
						novaPlaca.setLocalPlaca(null);
						novaPlaca.setIndiceModelo(indiceModelo);
						novaPlaca.setSerialPlaca(serial);
						novaPlaca.setDataEnvio(dateChooser.getDate());
						novaPlaca.setIndiceCidade(indiceCidade);
						novaPlaca.setIndiceEstacao(indiceEstacao);
						novaPlaca.setObervacoes(textArea.getText());
						novaPlaca.setTecnicaPlaca(tecnicaPlaca);
						novaPlaca.setStatusPlaca("Planta");
						novaPlaca.setUltimoComentario(ultimoComentario);
						novaPlaca.inserePlaca();
						JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");
						textArea.setText("");
						textFieldSerial.setText("");
						}//Fim do else
						
					}//Fim do else onde se faz o cadastro da placa nova.			
						
				}//Fim da condição para cadastro em Planta interna.
				
				//Início da condicional caso a opção Outra Filial seja selecionada
				if(rdbtnOutraFilial.isSelected()){
					
					verifica = novaPlaca.verificaPlaca(serial);
					
					//Início da verificação caso a placa já esteja cadastrada
					if(verifica==true){						

						//JOptionPane.showMessageDialog(null, "Placa Já Cadastrada");
						buttonSalvar.setEnabled(false);
						
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaPlaca = "select * from placa where serialPlaca='"+serial+"'";
							rs = conecta.stm.executeQuery(buscaPlaca);
							
								while(rs.next()){
									status = rs.getString("statusPlaca");
									String buscaCidade = "select * from cidade where idcidade='"+rs.getInt("cidade")+"'";
									RS = conecta.stm.executeQuery(buscaCidade);
									
									while(RS.next()){
										cidade = RS.getString("cidadeNome");
										
										String buscaEstacao = "select * from estacao where idCidade='"+RS.getInt("idcidade")+"'";
										RS1 = conecta.stm.executeQuery(buscaEstacao);
										
										while(RS1.next()){
											estacao = RS1.getString("estacaoSigla");
										}
										
										String buscaModelo = "select * from modeloplaca where partnumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
										rsModelo = conecta.stm.executeQuery(buscaModelo);
										
										while(rsModelo.next()){
											modelo = rsModelo.getString("partnumberModelo");
										}
									}									
									
									     if("Almox".equals(status)){
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(true);
												textArea.setText(rs.getString("observacoes"));
												textFieldLocal.setText(rs.getString("localPlaca"));
												comboBoxPartNumber.setSelectedItem(modelo);
												textFieldSerial.setText(rs.getString("serialPlaca"));
											}else if("Reparo".equals(status)){
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												textFieldSerial.setText(rs.getString("serialPlaca"));
												comboBoxEnviado.setSelectedItem(cidade);
												textArea.setText(rs.getString("observacoes"));
												comboBoxPartNumber.setSelectedItem(modelo);
												dateChooser.setDate(rs.getDate("dataEnvio"));
											}else if("Filial".equals(status)){
												rdbtnPlantaInterna.setSelected(false);
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												dateChooser.setDate(rs.getDate("dataEnvio"));
												comboBoxEnviado.setSelectedItem(cidade);
												textArea.setText(rs.getString("observacoes"));	
												dateChooser.setDate(rs.getDate("dataEnvio"));
											}else if("Planta".equals(status)){
												rdbtnAlmox.setSelected(false);
												rdbtnEnvioPReparo.setSelected(false);
												rdbtnOutraFilial.setSelected(false);
												rdbtnPlantaInterna.setSelected(true);
												textFieldLocal.setText("");
												textFieldLocal.setEnabled(false);
												dateChooser.setDate(rs.getDate("dataEnvio"));
												comboBoxUtilizado.setSelectedItem(cidade);
												comboBoxEstacao.setSelectedItem(estacao);
											}									     
									     
								}//Fim de while							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}							
					}//Fim da condicional caso a placa já esteja cadastrada
					
					//Início da sessão caso a placa não esteja cadastrada
					else{
						
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							
							String buscaIndiceModelo = "select * from modeloPlaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
							rs = conecta.stm.executeQuery(buscaIndiceModelo);
							
							while(rs.next()){
								indiceModelo = rs.getInt("idModelo");
							}
							
							String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxEnviado.getSelectedItem()+"'";
							RS = conecta.stm.executeQuery(buscaIndiceCidade);
							
							while(RS.next()){
								indiceCidade = RS.getInt("idcidade");
							}
							
						}catch(SQLException e){
							
						}						
						
						novaPlaca.setLocalPlaca(null);
						novaPlaca.setIndiceModelo(indiceModelo);
						novaPlaca.setSerialPlaca(serial);
						novaPlaca.setDataEnvio(dateChooser.getDate());
						novaPlaca.setIndiceCidade(indiceCidade);
						novaPlaca.setObervacoes(textArea.getText());
						novaPlaca.setIndiceEstacao(1);
						novaPlaca.setTecnicaPlaca(tecnicaPlaca);
						novaPlaca.setStatusPlaca("Filial");
						novaPlaca.setUltimoComentario(ultimoComentario);
						novaPlaca.inserePlaca();
						JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso");
						textArea.setText("");
						textFieldSerial.setText("");						
						
					}//Fim do else					
					
				}//Fim da condicional caso a opção Outra Filial seja selecionada
			}
		});
		buttonSalvar.setToolTipText("Salvar");
		buttonSalvar.setIcon(new ImageIcon(TelaPlaca.class.getResource("/imagens/mltech/Salvar.png")));
		buttonSalvar.setBounds(0, 0, 46, 33);
		contentPane.add(buttonSalvar);
		
		JButton buttonSair = new JButton("");
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonSair.setToolTipText("Sair");
		buttonSair.setIcon(new ImageIcon(TelaPlaca.class.getResource("/imagens/mltech/Sair.png")));
		buttonSair.setBounds(173, 0, 46, 33);
		contentPane.add(buttonSair);
		
		JLabel lblLocalizaoAlmox = new JLabel("Localiza\u00E7\u00E3o Almox");
		lblLocalizaoAlmox.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocalizaoAlmox.setBounds(10, 119, 135, 14);
		contentPane.add(lblLocalizaoAlmox);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setBounds(10, 135, 103, 20);
		contentPane.add(textFieldLocal);
		textFieldLocal.setColumns(10);
		
		JLabel lblPartnumber = new JLabel("Part-Number");
		lblPartnumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPartnumber.setBounds(145, 119, 85, 14);
		contentPane.add(lblPartnumber);
		
		comboBoxPartNumber = new JComboBox();
		comboBoxPartNumber.setModel(new DefaultComboBoxModel(new String[] {"Selecione Modelo"}));
		comboBoxPartNumber.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				ResultSet rs = null;
				String descricao = (String)comboBoxPartNumber.getSelectedItem();
				
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					String buscaDescricao = "select nomeModelo from modeloplaca where partNumberModelo='"+descricao+"'";
					rs = conecta.stm.executeQuery(buscaDescricao);
					
					while(rs.next()){
					descricao = rs.getString("nomeModelo");	
					textFieldDescricao.setText(descricao);
					break;
					}					
					
				}catch(SQLException ev){
					JOptionPane.showMessageDialog(null, ev);
				}
				
			}
		});
		comboBoxPartNumber.setBounds(145, 135, 146, 20);
		contentPane.add(comboBoxPartNumber);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescrio.setBounds(316, 119, 72, 14);
		contentPane.add(lblDescrio);
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setBounds(316, 135, 227, 20);
		contentPane.add(textFieldDescricao);
		textFieldDescricao.setColumns(10);
		
		JLabel lblNSerial = new JLabel("N\u00BA Serial");
		lblNSerial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNSerial.setBounds(565, 119, 72, 14);
		contentPane.add(lblNSerial);
		
		textFieldSerial = new JTextField();
		textFieldSerial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if((evt.getKeyCode()==KeyEvent.VK_DELETE)||(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
					buttonSalvar.setEnabled(true);
					buttonAtualizar.setEnabled(false);
					textArea.setText("");
					
				}
			}
		});
		textFieldSerial.setBounds(565, 135, 135, 20);
		contentPane.add(textFieldSerial);
		textFieldSerial.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Data de Envio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 174, 83, 14);
		contentPane.add(lblNewLabel);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 188, 123, 20);
		contentPane.add(dateChooser);
		
		JLabel lblEnviadoPara = new JLabel("Enviado Para:");
		lblEnviadoPara.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnviadoPara.setBounds(145, 174, 97, 14);
		contentPane.add(lblEnviadoPara);
		
		comboBoxEnviado = new JComboBox();
		comboBoxEnviado.setModel(new DefaultComboBoxModel(new String[] {"Selecione Cidade"}));
		comboBoxEnviado.setBounds(145, 188, 204, 20);
		contentPane.add(comboBoxEnviado);
		
		JLabel lblUtilizadoaEm = new JLabel("Utilizado(a) em:");
		lblUtilizadoaEm.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUtilizadoaEm.setBounds(363, 174, 109, 14);
		contentPane.add(lblUtilizadoaEm);
		
		comboBoxUtilizado = new JComboBox();
		comboBoxUtilizado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = null;
				ResultSet RS = null;
				int indiceCidade = 0;
				comboBoxEstacao.removeAllItems();
				String cidade = (String) comboBoxUtilizado.getSelectedItem();
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					//Busca o índice da cidade selecionada
					String buscaIndiceCidade = "select idcidade from cidade where cidadeNome= '"+comboBoxUtilizado.getSelectedItem()+"'";
					RS = conecta.stm.executeQuery(buscaIndiceCidade);
					while(RS.next()){
						indiceCidade = RS.getInt("idcidade");
					}//Fim do método onde busca o índice da cidade				
					
					String buscaEstacao = "select * from estacao where idcidade='"+indiceCidade+"'";
					rs = conecta.stm.executeQuery(buscaEstacao);
					while(rs.next()){						
						comboBoxEstacao.addItem(rs.getString("estacaoSigla"));
					}					
					
				}catch(SQLException ev){
					JOptionPane.showMessageDialog(null, ev);
				}
			}
		});
		comboBoxUtilizado.setModel(new DefaultComboBoxModel(new String[] {"Selecione Cidade"}));
		comboBoxUtilizado.setBounds(364, 188, 217, 20);
		contentPane.add(comboBoxUtilizado);
		
		JLabel lblEstao = new JLabel("Esta\u00E7\u00E3o:");
		lblEstao.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstao.setBounds(592, 174, 56, 14);
		contentPane.add(lblEstao);
		
		comboBoxEstacao = new JComboBox();
		comboBoxEstacao.setBounds(591, 188, 109, 20);
		contentPane.add(comboBoxEstacao);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 44, 456, 63);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(10, 0, 46, 14);
		panel.add(lblStatus);
		
		rdbtnAlmox = new JRadioButton("Almox");
		rdbtnAlmox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnAlmox.isSelected()){
					/**
					if((rdbtnEnvioPReparo.isSelected())||(rdbtnOutraFilial.isSelected())||(rdbtnPlantaInterna.isSelected())){
						JOptionPane.showMessageDialog(null, "Selecione Apenas Uma Opção");
					}*/
					if(rdbtnEnvioPReparo.isSelected()){
						rdbtnEnvioPReparo.setSelected(false);
					}else if(rdbtnOutraFilial.isSelected()){
						rdbtnOutraFilial.setSelected(false);
					}else if(rdbtnPlantaInterna.isSelected()){
						rdbtnPlantaInterna.setSelected(false);
					}
					dateChooser.setEnabled(false);
					comboBoxEnviado.setEnabled(false);
					comboBoxUtilizado.setEnabled(false);
					comboBoxEstacao.setEnabled(false);
				}else{
					dateChooser.setEnabled(true);
					comboBoxEnviado.setEnabled(true);
					comboBoxUtilizado.setEnabled(true);
					comboBoxEstacao.setEnabled(true);
				}
			}
		});
		rdbtnAlmox.setBounds(6, 19, 65, 23);
		panel.add(rdbtnAlmox);
		
		rdbtnEnvioPReparo = new JRadioButton("Envio P/ Reparo");
		rdbtnEnvioPReparo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnEnvioPReparo.isSelected()){
					/**
					if((rdbtnAlmox.isSelected())||(rdbtnOutraFilial.isSelected())||(rdbtnPlantaInterna.isSelected())){
						JOptionPane.showMessageDialog(null, "Selecione Apenas Uma Opção");
					}*/
					if(rdbtnAlmox.isSelected()){
						rdbtnAlmox.setSelected(false);
					}else if(rdbtnPlantaInterna.isSelected()){
						rdbtnPlantaInterna.setSelected(false);
					}else if(rdbtnOutraFilial.isSelected()){
						rdbtnOutraFilial.setSelected(false);
					}
					textFieldLocal.setEnabled(false);
					comboBoxEstacao.setEnabled(false);
					comboBoxUtilizado.setEnabled(false);
					comboBoxAlmox.setEnabled(false);
				}else{
					textFieldLocal.setEnabled(true);
					comboBoxEstacao.setEnabled(true);
					comboBoxUtilizado.setEnabled(true);	
					comboBoxAlmox.setEnabled(true);
				}
			}
		});
		rdbtnEnvioPReparo.setBounds(77, 19, 125, 23);
		panel.add(rdbtnEnvioPReparo);
		
		rdbtnPlantaInterna = new JRadioButton("Planta Interna");
		rdbtnPlantaInterna.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnPlantaInterna.isSelected()){
					/**
					if((rdbtnAlmox.isSelected())||(rdbtnEnvioPReparo.isSelected())||(rdbtnOutraFilial.isSelected())){
						JOptionPane.showMessageDialog(null, "Selecione Apenas Uma Opção");
					}else	*/
					if(rdbtnAlmox.isSelected()){
						rdbtnAlmox.setSelected(false);
					}else if(rdbtnEnvioPReparo.isSelected()){
						rdbtnEnvioPReparo.setSelected(false);
					}else if(rdbtnOutraFilial.isSelected()){
						rdbtnOutraFilial.setSelected(false);
					}
					comboBoxEnviado.setEnabled(false);
					dateChooser.setEnabled(false);
					textFieldLocal.setEnabled(false);
					comboBoxAlmox.setEnabled(false);
					
				}else{
					comboBoxEnviado.setEnabled(true);
					dateChooser.setEnabled(true);
					textFieldLocal.setEnabled(true);
					comboBoxAlmox.setEnabled(true);
				}
			}
		});
		rdbtnPlantaInterna.setBounds(204, 19, 108, 23);
		panel.add(rdbtnPlantaInterna);
		
		rdbtnOutraFilial = new JRadioButton("Outra Filial");
		rdbtnOutraFilial.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnOutraFilial.isSelected()){
					/**
					if((rdbtnAlmox.isSelected())||(rdbtnEnvioPReparo.isSelected())||(rdbtnPlantaInterna.isSelected())){
						JOptionPane.showMessageDialog(null, "Selecione Apenas Uma Opção");
					}*/
					if(rdbtnAlmox.isSelected()){
						rdbtnAlmox.setSelected(false);
					}else if(rdbtnEnvioPReparo.isSelected()){
						rdbtnEnvioPReparo.setSelected(false);
					}else if(rdbtnPlantaInterna.isSelected()){
						rdbtnPlantaInterna.setSelected(false);
					}
						textFieldLocal.setEnabled(false);
						comboBoxUtilizado.setEnabled(false);
						comboBoxEstacao.setEnabled(false);
						comboBoxAlmox.setEnabled(false);
				
				}else{
					textFieldLocal.setEnabled(true);
					comboBoxUtilizado.setEnabled(true);
					comboBoxEstacao.setEnabled(true);	
					comboBoxAlmox.setEnabled(true);
				}
			}
		});
		rdbtnOutraFilial.setBounds(338, 19, 86, 23);
		panel.add(rdbtnOutraFilial);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaes.setBounds(10, 282, 78, 14);
		contentPane.add(lblObservaes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 296, 575, 121);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textArea.setEditable(false);
		
		JButton buttonPesquisar = new JButton("");
		buttonPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String serial = JOptionPane.showInputDialog("Informe o Serial da Placa");
				recebeSerial(serial);
				
				/*
				boolean verifica;
				ResultSet rs = null;
				ResultSet RS = null;
				ResultSet RS1 = null;
				String status = null;
				String modelo = null;
				String tecnica = null;
				String cidade = null;
				
				//Pesquisa Se Placa Já Está Cadastrada
				String serial = JOptionPane.showInputDialog("Informe o Serial da Placa");
				
				if(serial==null){
					
				}else{					
						Placa placa = new Placa();	
						serial = serial.replace(" ", "").toUpperCase();
						verifica = placa.verificaPlaca(serial);
						if(verifica==false){
							JOptionPane.showMessageDialog(null, "Placa Não Cadastrada");
						}else if(verifica==true){
							buttonSalvar.setEnabled(false);
							buttonAtualizar.setEnabled(true);
								try{
									ConectaBanco conecta = new ConectaBanco();
									conecta.conectaBanco();
									
									String buscaStatus = "select * from placa where serialPlaca='"+serial+"'";
									rs = conecta.stm.executeQuery(buscaStatus);
									
									while(rs.next()){
										status=rs.getString("statusPlaca");
										tecnica = rs.getString("tecnicaPlaca");
										String buscaModelo = "select * from modeloplaca where idModelo='"+rs.getString("modelo")+"'";
										RS = conecta.stm.executeQuery(buscaModelo);
										
										String buscaCidade = "select * from cidade where idcidade='"+rs.getInt("cidade")+"'";
										RS1 = conecta.stm.executeQuery(buscaCidade);
										
										while(RS1.next()){
											cidade = RS1.getString("cidadeNome");
										}
										
										while(RS.next()){
											modelo = RS.getString("partNumberModelo");											
										}
									
									if("Almox".equals(status)){
										
										if("TX".equals(tecnica)){
											rdbtnTX.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("CX".equals(tecnica)){
											rdbtnCx.setSelected(true);
											rdbtnTX.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("DADOS".equals(tecnica)){
											rdbtnDados.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnTX.setSelected(false);
										}
										
										rdbtnAlmox.setSelected(true);
										textFieldLocal.setText(rs.getString("localPlaca"));
										comboBoxPartNumber.setSelectedItem(modelo);
										textFieldSerial.setText(serial);
										dateChooser.setDate(rs.getDate("dataEnvio"));
										comboBoxAlmox.setSelectedItem(rs.getString("almoxPlaca"));
										comboBoxEnviado.setSelectedItem("Selecione Cidade");
										comboBoxUtilizado.setSelectedItem("Selecione Cidade");
										textArea.setText(rs.getString("observacoes"));										
										
										
									}//Fim da condição caso o status seja Almox
									else
									//Início da condição caso o status seja Filial
									if("Filial".equals(status)){
										
										if("TX".equals(tecnica)){
											rdbtnTX.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("CX".equals(tecnica)){
											rdbtnCx.setSelected(true);
											rdbtnTX.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("DADOS".equals(tecnica)){
											rdbtnDados.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnTX.setSelected(false);
										}
										
										rdbtnOutraFilial.setSelected(true);
										comboBoxPartNumber.setSelectedItem(modelo);
										textFieldSerial.setText(serial);
										dateChooser.setDate(rs.getDate("dataEnvio"));
										comboBoxEnviado.setSelectedItem(cidade);
										textArea.setText(rs.getString("observacoes"));										
										
									}//Fim da condição caso o status seja Filial
									else if("Reparo".equals(status)){
										
										if("TX".equals(tecnica)){
											rdbtnTX.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("CX".equals(tecnica)){
											rdbtnCx.setSelected(true);
											rdbtnTX.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("DADOS".equals(tecnica)){
											rdbtnDados.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnTX.setSelected(false);
										}
										
										rdbtnEnvioPReparo.setSelected(true);
										comboBoxEnviado.setSelectedItem(cidade);
										textArea.setText(rs.getString("observacoes"));
										textFieldSerial.setText(serial);
										comboBoxPartNumber.setSelectedItem(modelo);
										dateChooser.setDate(rs.getDate("dataEnvio"));
										
										
									}//Fim da condição caso o status seja Reparo
									else if("Planta".equals(status)){
										
										if("TX".equals(tecnica)){
											rdbtnTX.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("CX".equals(tecnica)){
											rdbtnCx.setSelected(true);
											rdbtnTX.setSelected(false);
											rdbtnDados.setSelected(false);
										}else if("DADOS".equals(tecnica)){
											rdbtnDados.setSelected(true);
											rdbtnCx.setSelected(false);
											rdbtnTX.setSelected(false);
										}
										
										comboBoxUtilizado.setSelectedItem(cidade);
										textArea.setText(rs.getString("observacoes"));
										comboBoxPartNumber.setSelectedItem(modelo);
										rdbtnPlantaInterna.setSelected(true);
										textFieldSerial.setText(serial);
			
			
									}//Fim da condição caso o status seja Planta
									}//Fim do while
								}catch(SQLException e){
									
								}								
						}				
				}
			*/}
		});
		buttonPesquisar.setToolTipText("Pesquisar");
		buttonPesquisar.setIcon(new ImageIcon(TelaPlaca.class.getResource("/imagens/mltech/Pesquisar.png")));
		buttonPesquisar.setBounds(43, 0, 46, 33);
		contentPane.add(buttonPesquisar);
		
		JButton buttonLimpar = new JButton("");
		buttonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textFieldLocal.setText("");
				textFieldSerial.setText("");
				comboBoxEstacao.setSelectedItem("");
				comboBoxPartNumber.setSelectedItem("Selecione Modelo");
				comboBoxEnviado.setSelectedItem("Selecione Cidade");
				comboBoxUtilizado.setSelectedItem("Selecione Cidade");				
				
			}
		});
		buttonLimpar.setIcon(new ImageIcon(TelaPlaca.class.getResource("/imagens/mltech/Limpar (Copy).png")));
		buttonLimpar.setBounds(130, 0, 46, 33);
		contentPane.add(buttonLimpar);
		
		buttonAtualizar = new JButton("");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Placa novaPlaca = new Placa();
				String tecnicaPlaca;
				String serial = textFieldSerial.getText().replace(" ", "").toUpperCase();
				String almox = (String)comboBoxAlmox.getSelectedItem();
				int indiceModelo = 0;
				int indiceCidade = 0;
				int indiceEstacao = 0;
				ResultSet rs = null;
				ResultSet RS = null;
				ResultSet RS1 = null;
				if(rdbtnTX.isSelected()){
					tecnicaPlaca="TX";
				}else if(rdbtnCx.isSelected()){
					tecnicaPlaca="CX";
				}else{
					tecnicaPlaca="DADOS";
				}				
				
				//Início do código caso a opção Almox seja selecionada
				if(rdbtnAlmox.isSelected()){
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaIndiceModelo = "select * from modeloPlaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaIndiceModelo);
						
						while(rs.next()){
							indiceModelo = rs.getInt("idModelo");											
						}										
						
					}catch(SQLException e){
						
					}
									
					novaPlaca.setSerialPlaca(serial);
					novaPlaca.setLocalPlaca(textFieldLocal.getText());
					novaPlaca.setDataEnvio(null);
					novaPlaca.setObervacoes(textArea.getText());
					novaPlaca.setIndiceModelo(indiceModelo);
					novaPlaca.setStatusPlaca("Almox");
					novaPlaca.setIndiceCidade(1);
					novaPlaca.setIndiceEstacao(1);
					novaPlaca.setTecnicaPlaca(tecnicaPlaca);
					novaPlaca.setAlmoxPlaca(almox);
					novaPlaca.setUltimoComentario(ultimoComentario);
					novaPlaca.atualizaPlaca();
					JOptionPane.showMessageDialog(null, "Cadastro Atualizado");
										
				}//Fim da Opção caso a opção Almox seja selecionada
				else
				//Início do código caso a opção Reparo seja selecionada
				if(rdbtnEnvioPReparo.isSelected()){
					//Lógica para descobrir qual id da cidade selecionada e o id do modelo
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaIndiceModelo = "select * from modeloPlaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaIndiceModelo);
						
						while(rs.next()){
							indiceModelo = rs.getInt("idModelo");								
						}										
						
						String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxEnviado.getSelectedItem()+"'";
						RS = conecta.stm.executeQuery(buscaIndiceCidade);
						
						while(RS.next()){
							indiceCidade = RS.getInt("idcidade");
						}
						
					}catch(SQLException e){
						
					}					
					
					novaPlaca.setSerialPlaca(serial);
					novaPlaca.setLocalPlaca(null);
					novaPlaca.setIndiceModelo(indiceModelo);
					novaPlaca.setDataEnvio(dateChooser.getDate());
					novaPlaca.setIndiceCidade(indiceCidade);
					novaPlaca.setIndiceEstacao(1);
					novaPlaca.setObervacoes(textArea.getText());
					novaPlaca.setTecnicaPlaca(tecnicaPlaca);
					novaPlaca.setStatusPlaca("Reparo");
					novaPlaca.setAlmoxPlaca(null);
					novaPlaca.setUltimoComentario(novoComentario);
					novaPlaca.atualizaPlaca();
					JOptionPane.showMessageDialog(null, "Cadastro Atualizado");
					
				}//Fim da opção caso a opção Reparo seja selecionada
				else
				//Início do código caso a opção Planta Interna seja selecionada
				if(rdbtnPlantaInterna.isSelected()){
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaIndiceModelo = "select * from modeloplaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaIndiceModelo);
						
						while(rs.next()){
							indiceModelo = rs.getInt("idModelo");
						}
						
						String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxUtilizado.getSelectedItem()+"'";
						RS = conecta.stm.executeQuery(buscaIndiceCidade);
						
						while(RS.next()){
							indiceCidade = RS.getInt("idcidade");
						}
						
						String buscaIndiceEstacao = "select * from estacao where idCidade='"+indiceCidade+"'";
						RS1 = conecta.stm.executeQuery(buscaIndiceEstacao);
						
						while(RS1.next()){
							indiceEstacao = RS1.getInt("idEstacao");
						}						
						
					}catch(SQLException e){
						
					}
					
					novaPlaca.setSerialPlaca(serial);
					novaPlaca.setLocalPlaca(null);
					novaPlaca.setIndiceModelo(indiceModelo);
					novaPlaca.setDataEnvio(null);
					novaPlaca.setIndiceCidade(indiceCidade);
					novaPlaca.setIndiceEstacao(indiceEstacao);
					novaPlaca.setObervacoes(textArea.getText());
					novaPlaca.setTecnicaPlaca(tecnicaPlaca);
					novaPlaca.setStatusPlaca("Planta");
					novaPlaca.setAlmoxPlaca(null);
					novaPlaca.setUltimoComentario(novoComentario);
					novaPlaca.atualizaPlaca();
					JOptionPane.showMessageDialog(null, "Cadastro Atualizado");
					
				}//Fim da condicional caso a opção Planta Interna seja selecionada
				else
				//Início do código caso a opção Outra Filial seja selecionada
				if(rdbtnOutraFilial.isSelected()){
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String buscaIndiceModelo = "select * from modeloPlaca where partNumberModelo='"+comboBoxPartNumber.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaIndiceModelo);
						
						while(rs.next()){
							indiceModelo = rs.getInt("idModelo");
						}
						
						String buscaIndiceCidade = "select * from cidade where cidadeNome='"+comboBoxEnviado.getSelectedItem()+"'";
						RS = conecta.stm.executeQuery(buscaIndiceCidade);
						
						while(RS.next()){
							indiceCidade = RS.getInt("idcidade");
						}
						
					}catch(SQLException e){
						
					}
					
					novaPlaca.setSerialPlaca(serial);
					novaPlaca.setLocalPlaca(null);
					novaPlaca.setIndiceModelo(indiceModelo);
					novaPlaca.setDataEnvio(dateChooser.getDate());
					novaPlaca.setIndiceCidade(indiceCidade);
					novaPlaca.setObervacoes(textArea.getText());
					novaPlaca.setIndiceEstacao(1);
					novaPlaca.setTecnicaPlaca(tecnicaPlaca);
					novaPlaca.setStatusPlaca("Filial");
					novaPlaca.setAlmoxPlaca(null);
					novaPlaca.setUltimoComentario(ultimoComentario);
					novaPlaca.atualizaPlaca();
					JOptionPane.showMessageDialog(null, "Cadastro Atualizado");					
					
				}//Fim da condicional caso a opção Outra Filial seja selecionada				
			}
		});
		buttonAtualizar.setToolTipText("Atualizar");
		buttonAtualizar.setIcon(new ImageIcon(TelaPlaca.class.getResource("/imagens/mltech/1248318776 (Copy).png")));
		buttonAtualizar.setBounds(87, 0, 46, 33);
		contentPane.add(buttonAtualizar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Equipamento", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(476, 33, 246, 82);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnTX = new JRadioButton("TX");
		rdbtnTX.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnTX.isSelected()){
					if((rdbtnCx.isSelected())||(rdbtnDados.isSelected())){
						
						rdbtnCx.setSelected(false);
						rdbtnDados.setSelected(false);						

						ResultSet rs = null;
						String status = "TX";
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaModeloTX = "select * from modeloplaca where statusModelo='"+status+"'";
							rs = conecta.stm.executeQuery(buscaModeloTX);
							while(rs.next()){
								comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
							}							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}
						
					}else{
						ResultSet rs = null;
						String status = "TX";
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaModeloTX = "select * from modeloplaca where statusModelo='"+status+"'";
							rs = conecta.stm.executeQuery(buscaModeloTX);
							while(rs.next()){
								comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
							}							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}
					}
				}else{
					comboBoxPartNumber.removeAllItems();
					comboBoxPartNumber.addItem("Selecione Modelo");
				}
			}
		});
		rdbtnTX.setBounds(6, 18, 57, 23);
		panel_1.add(rdbtnTX);
		
		rdbtnCx = new JRadioButton("CX");
		rdbtnCx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnCx.isSelected()){
					if((rdbtnTX.isSelected())||(rdbtnDados.isSelected())){
						
						rdbtnTX.setSelected(false);
						rdbtnDados.setSelected(false);						

						ResultSet rs = null;
						String status = "CX";
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaModeloCX = "select * from modeloplaca where statusModelo='"+status+"'";
							rs = conecta.stm.executeQuery(buscaModeloCX);
							while(rs.next()){
								comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
							}							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}						
						
					}else{
						ResultSet rs = null;
						String status = "CX";
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaModeloCX = "select * from modeloplaca where statusModelo='"+status+"'";
							rs = conecta.stm.executeQuery(buscaModeloCX);
							while(rs.next()){
								comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
							}							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}
					}
				}else{
					comboBoxPartNumber.removeAllItems();
					comboBoxPartNumber.addItem("Selecione Modelo");
				}			
					
				}
			
		});
		rdbtnCx.setBounds(6, 44, 67, 23);
		panel_1.add(rdbtnCx);
		
		rdbtnDados = new JRadioButton("Dados");
		rdbtnDados.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnDados.isSelected()){
					if((rdbtnCx.isSelected())||(rdbtnTX.isSelected())){
						
						rdbtnCx.setSelected(false);
						rdbtnTX.setSelected(false);						

						ResultSet rs = null;
						String status = "Dados";
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaModeloTX = "select * from modeloplaca where statusModelo='"+status+"'";
							rs = conecta.stm.executeQuery(buscaModeloTX);
							while(rs.next()){
								comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
							}							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}
					}else{
						ResultSet rs = null;
						String status = "Dados";
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							String buscaModeloTX = "select * from modeloplaca where statusModelo='"+status+"'";
							rs = conecta.stm.executeQuery(buscaModeloTX);
							while(rs.next()){
								comboBoxPartNumber.addItem(rs.getString("partNumberModelo"));
							}							
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}
					}
				}else{
					comboBoxPartNumber.removeAllItems();
					comboBoxPartNumber.addItem("Selecione Modelo");
				}
			}
		});
		rdbtnDados.setBounds(94, 18, 77, 23);
		panel_1.add(rdbtnDados);
		
		insereCidade();
		textFieldDescricao.setEditable(false);
		
		JLabel lblAlmox = new JLabel("Almox");
		lblAlmox.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlmox.setBounds(10, 228, 46, 14);
		contentPane.add(lblAlmox);
		
		comboBoxAlmox = new JComboBox();
		comboBoxAlmox.setModel(new DefaultComboBoxModel(new String[] {"Selecione o Almox", "Caxias do Sul", "Cruz Alta", "Erechim", "Palmeira das Miss\u00F5es", "Passo Fundo", "Pelotas", "Porto Alegre", "Santa Maria", "Santo \u00C2ngelo", "Uruguaiana"}));
		comboBoxAlmox.setBounds(10, 244, 166, 20);
		contentPane.add(comboBoxAlmox);
		
		JButton btnNovo = new JButton("");
		btnNovo.setToolTipText("Inserir Informa\u00E7\u00F5es");
		btnNovo.setIcon(new ImageIcon(TelaPlaca.class.getResource("/imagens/mltech/Pen-32.png")));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novoComentario = JOptionPane.showInputDialog("Observações");
				Placa novaObservacao = new Placa();
	
				if(textArea.getText().isEmpty()){				
				if(novoComentario==null){
					
				}else{
				textArea.setText(java.time.LocalDate.now()+" "+novoComentario);
				TelaMostraPlacas comentario = new TelaMostraPlacas();
				novaObservacao.setUltimoComentario(novoComentario);
				}
				}else{
					observacaoAnterior = textArea.getText();
					textArea.setText(observacaoAnterior+"\n\n"+java.time.LocalDate.now()+" "+novoComentario);
					novaObservacao.setUltimoComentario(novoComentario);
				}
			}
		});
		btnNovo.setBounds(611, 384, 85, 33);
		contentPane.add(btnNovo);
		buttonAtualizar.setEnabled(false);
	}
}
