package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import classes.mltech.Circuito;
import classes.mltech.ConectaBanco;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class TelaCadastroCircuito extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTextField textFieldCircuito;
	private JTextField textFieldSlotSDHA;
	private JTextField textFieldDidSDHA;
	private JTextField textFieldCliente;
	private JTextField textFieldPortaPDHA;
	private JTextField textFieldDidPDHA;
	private JTextField textFieldSlotSDHB;
	private JTextField textFieldDidSDHB;
	private JTextField textFieldPortaPDHB;
	private JTextField textFieldDidPDHB;
	private JCheckBox chckbxSdh;
	private JCheckBox chckbxPdh;
	private JCheckBox chckbxSdh_1;
	private JCheckBox chckbxPdh_1;
	private JComboBox comboBoxCidadeA;
	private JComboBox comboBoxPDHA;
	private JComboBox comboBoxSDHB;
	private JComboBox comboBoxPortaSDHA;
	private JComboBox comboBoxPortaSDHB;
	private JComboBox comboBoxSDHA;
	private JComboBox comboBoxCidadeB;
	private JComboBox comboBoxPDHB;
	private JComboBox comboBoxEstacaoSDHA;
	private JComboBox comboBoxCidadeSDHA;
	private JComboBox comboBoxCabeceira;
	private int slotA;
	private int slotB;
	private int portaPDHA;
	private int portaPDHB;
	private JButton buttonAtualizar;
	private JButton buttonSalvar;
	private JTextArea textArea;
	private int indexSDHA = 0;
	private int indexSDHB = 0;
	private int indexEstacaoA;
	private int indexEstacaoB;
	private String sdhA;
	private String sdhB;
	private int indexPdhA = 0;
	private int indexPdhB;
	private String pdhA;
	private String pdhB;
	private int indexCidadeA = 0;
	private int indexCidadeB = 0;
	private String cidadeA;
	private String cidadeB;
	private String estacaoA;
	private String estacaoB;
	private JLabel lblLocalidade_3;
	private JComboBox comboBoxCidadeSDHB;
	private JLabel lblEstao_1;
	private JComboBox comboBoxEstacaoSDHB;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnDesativado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCircuito frame = new TelaCadastroCircuito();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void buscaSDH(){
		
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaSDH = "select * from sdh order by sdhNome asc";
			rs = conecta.stm.executeQuery(buscaSDH);
			
			while(rs.next()){
				comboBoxSDHA.addItem(rs.getString("sdhNome"));
				comboBoxSDHB.addItem(rs.getString("sdhNome"));
			}			
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}		
		
	}//Fim do método de buscar SDH
	
	public void buscaRadio(){
		
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaRadio = "select * from radio";
			rs = conecta.stm.executeQuery(buscaRadio);
			
			while(rs.next()){
				comboBoxPDHA.addItem(rs.getString("modeloRadio"));
				comboBoxPDHB.addItem(rs.getString("modeloRadio"));
			}			
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
		}		
	}//fim do método de buscar Rádios
	
	public void buscaCidade(){
		
		ResultSet rs = null;
		
		try{
			ConectaBanco conecta = new ConectaBanco();
			conecta.conectaBanco();
			
			String buscaCidade = "select * from cidade order by cidadeNome asc";
			rs = conecta.stm.executeQuery(buscaCidade);
			
			while(rs.next()){
				comboBoxCidadeA.addItem(rs.getString("cidadeNome"));
				comboBoxCidadeB.addItem(rs.getString("cidadeNome"));
				comboBoxCidadeSDHA.addItem(rs.getString("cidadeNome"));
				comboBoxCidadeSDHB.addItem(rs.getString("cidadeNome"));
			}			
			
		}catch(SQLException e){
			
		}		
		
	}//Fim do método de buscar cidade
	
	

	/**
	 * Create the frame.
	 */
	public TelaCadastroCircuito() {
		setTitle("Cadastro de Circuito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 495);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Circuito novoCircuito = new Circuito();
				String circuitoDesejado = textFieldCircuito.getText();				
				boolean verifica = novoCircuito.verificaCircuito(circuitoDesejado);
				
				if(circuitoDesejado==null){
					
				}else if(verifica==true){
					if(comboBoxCabeceira.getSelectedItem()==""){
					
				textFieldCircuito.setText(circuitoDesejado);
				buttonSalvar.setEnabled(false);
				buttonAtualizar.setEnabled(true);
				ResultSet rs = null;
				ResultSet RS = null;
				String statusA = null;
				String statusB = null;
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();					
					
					String buscaStatus = "select * from circuito where circuitoNumero = '"+circuitoDesejado+"' and cabeceira = ' '";
					rs = conecta.stm.executeQuery(buscaStatus);
				
					if(rs.next()){
						statusA = rs.getString("statusA");
						statusB = rs.getString("statusB");
						indexSDHA = rs.getInt("codSdhA");
						indexSDHB = rs.getInt("codSdhB");
						indexPdhA = rs.getInt("codPdhA");
						indexPdhB = rs.getInt("codPdhB");
						indexCidadeA = rs.getInt("codCidadeA");
						indexCidadeB = rs.getInt("codCidadeB");
						indexEstacaoA = rs.getInt("codEstacaoA");
						indexEstacaoB = rs.getInt("codEstacaoB");
					}
					if(("s".equals(rs.getString("statusA")))&&("sB".equals(rs.getString("statusB")))){
						panel_1.setVisible(true);
						panel_2.setVisible(true);
						panel_3.setVisible(false);
						panel_4.setVisible(false);	
						chckbxSdh.setSelected(true);
						chckbxSdh_1.setSelected(true);
						
													
							String buscaSDH = "select * from sdh where idsdh='"+indexSDHA+"'";
							RS = conecta.stm.executeQuery(buscaSDH);
							
							while(RS.next()){
								sdhA = RS.getString("sdhNome");
							}
							
							String nomeSdhB = "select * from sdh where idsdh='"+indexSDHB+"'";
							RS = conecta.stm.executeQuery(nomeSdhB);
							
							while(RS.next()){
								sdhB = RS.getString("sdhNome");
							}	
							
							String nomeCidade = "select * from cidade where idcidade='"+indexCidadeA+"'";
							RS = conecta.stm.executeQuery(nomeCidade);
							
							while(RS.next()){
								cidadeA = RS.getString("cidadeNome");
							}
							
							String buscaEstacaoA = "select * from estacao where idEstacao='"+indexEstacaoA+"'";
							RS = conecta.stm.executeQuery(buscaEstacaoA);
							
							while(RS.next()){
								estacaoA = RS.getString("estacaoSigla");
							}
							
							String nomeCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
							RS = conecta.stm.executeQuery(nomeCidadeB);
							
							while(RS.next()){
								cidadeB = RS.getString("cidadeNome");
							}
							
							String buscaEstacaoB = "select * from estacao where idEstacao='"+indexEstacaoB+"'";
							RS = conecta.stm.executeQuery(buscaEstacaoB);
							
							while(RS.next()){
								estacaoB = RS.getString("estacaoSigla");
							}
						
						//Carregando dados da ponta A SDH
						textFieldCliente.setText(rs.getString("cliente"));
						textArea.setText(rs.getString("observacoesCircuito"));
						comboBoxSDHA.setSelectedItem(sdhA);
						textFieldSlotSDHA.setText(rs.getString("slotA"));
						comboBoxPortaSDHA.setSelectedItem(rs.getString("portaA"));
						textFieldDidSDHA.setText(rs.getString("didA"));		
						comboBoxCidadeSDHA.setSelectedItem(cidadeA);
						comboBoxEstacaoSDHA.setSelectedItem(estacaoA);
						
						
						//Carregando dados da ponta B SDH
						comboBoxSDHB.setSelectedItem(sdhB);
						textFieldSlotSDHB.setText(rs.getString("slotB"));
						comboBoxPortaSDHB.setSelectedItem(rs.getString("portaB"));
						textFieldDidSDHB.setText(rs.getString("didB"));
						comboBoxCidadeSDHB.setSelectedItem(cidadeB);
						comboBoxEstacaoSDHB.setSelectedItem(estacaoB);
						
					}else if(("s".equals(rs.getString("statusA")))&&("pB".equals(rs.getString("statusB")))){
						panel_1.setVisible(true);
						panel_4.setVisible(true);
						panel_2.setVisible(false);
						panel_3.setVisible(false);
						chckbxSdh.setSelected(true);
						chckbxPdh_1.setSelected(true);
						
						String buscaSDH = "select * from sdh where idsdh='"+indexSDHA+"'";
						RS = conecta.stm.executeQuery(buscaSDH);
						
						while(RS.next()){
							sdhA = RS.getString("sdhNome");
						}
						
						String buscaCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
						RS = conecta.stm.executeQuery(buscaCidadeB);
						
						while(RS.next()){
							cidadeB = RS.getString("cidadeNome");
						}
						
						String buscaPdhB = "select * from radio where idRadio='"+indexPdhB+"'";
						RS = conecta.stm.executeQuery(buscaPdhB);
						
						while(RS.next()){
							pdhB = RS.getString("modeloRadio");
						}
						
						String nomeCidade = "select * from cidade where idcidade='"+indexCidadeA+"'";
						RS = conecta.stm.executeQuery(nomeCidade);
						
						while(RS.next()){
							cidadeA = RS.getString("cidadeNome");
						}
						
						String buscaEstacaoA = "select * from estacao where idEstacao='"+indexEstacaoA+"'";
						RS = conecta.stm.executeQuery(buscaEstacaoA);
						
						while(RS.next()){
							estacaoA = RS.getString("estacaoSigla");
						}
						
						//Carregando dados da ponta A SDH
						textFieldCliente.setText(rs.getString("cliente"));
						textArea.setText(rs.getString("observacoesCircuito"));
						comboBoxSDHA.setSelectedItem(sdhA);
						textFieldSlotSDHA.setText(rs.getString("slotA"));
						comboBoxPortaSDHA.setSelectedItem(rs.getString("portaA"));
						textFieldDidSDHA.setText(rs.getString("didA"));	
						comboBoxCidadeSDHA.setSelectedItem(cidadeA);
						comboBoxEstacaoSDHA.setSelectedItem(estacaoA);
						
						//Carrega dados da ponta B PDH
						comboBoxCidadeB.setSelectedItem(cidadeB);
						comboBoxPDHB.setSelectedItem(pdhB);
						textFieldPortaPDHB.setText(rs.getString("portaB"));
						textFieldDidPDHB.setText(rs.getString("didB"));
						
					}else if(("p".equals(rs.getString("statusA")))&&("pB".equals(rs.getString("statusB")))){
						panel_3.setVisible(true);
						panel_4.setVisible(true);
						panel_1.setVisible(false);
						panel_2.setVisible(false);
						chckbxPdh.setSelected(true);
						chckbxPdh_1.setSelected(true);
						
						String buscaCidadeA = "select * from cidade where idcidade='"+indexCidadeA+"'";
						RS = conecta.stm.executeQuery(buscaCidadeA);
						
						while(RS.next()){
							cidadeA = RS.getString("cidadeNome");
						}
						
						String buscaPdhA = "select * from radio where idRadio='"+indexPdhA+"'";
						RS = conecta.stm.executeQuery(buscaPdhA);
						
						while(RS.next()){
							pdhA = RS.getString("modeloRadio");
						}						
						
						String buscaCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
						RS = conecta.stm.executeQuery(buscaCidadeB);
						
						while(RS.next()){
							cidadeB = RS.getString("cidadeNome");
						}
						
						String buscaPdhB = "select * from radio where idRadio='"+indexPdhB+"'";
						RS = conecta.stm.executeQuery(buscaPdhB);
						
						while(RS.next()){
							pdhB = RS.getString("modeloRadio");
						}						
						
						//Carrega dados ponta A PDH
						textArea.setText(rs.getString("observacoesCircuito"));
						textFieldCliente.setText(rs.getString("cliente"));
						comboBoxCidadeA.setSelectedItem(cidadeA);
						comboBoxPDHA.setSelectedItem(pdhA);
						textFieldPortaPDHA.setText(rs.getString("portaA"));
						textFieldDidPDHA.setText(rs.getString("didA"));
						
						//Carrega dados da ponta B PDH
						comboBoxCidadeB.setSelectedItem(cidadeB);
						comboBoxPDHB.setSelectedItem(pdhB);
						textFieldPortaPDHB.setText(rs.getString("portaB"));
						textFieldDidPDHB.setText(rs.getString("didB"));								
						
					}else if(("p".equals(rs.getString("statusA")))&&("sB".equals(rs.getString("statusB")))){
						panel_3.setVisible(true);
						panel_2.setVisible(true);
						panel_1.setVisible(false);
						panel_4.setVisible(false);
						chckbxPdh.setSelected(true);
						chckbxSdh_1.setSelected(true);
						
						String buscaCidadeA = "select * from cidade where idcidade='"+indexCidadeA+"'";
						RS = conecta.stm.executeQuery(buscaCidadeA);
						
						while(RS.next()){
							cidadeA = RS.getString("cidadeNome");
						}
						
						String buscaPdhA = "select * from radio where idRadio='"+indexPdhA+"'";
						RS = conecta.stm.executeQuery(buscaPdhA);
						
						while(RS.next()){
							pdhA = RS.getString("modeloRadio");
						}		
						
						String nomeSdhB = "select * from sdh where idsdh='"+indexSDHB+"'";
						RS = conecta.stm.executeQuery(nomeSdhB);
						
						while(RS.next()){
							sdhB = RS.getString("sdhNome");
						}
						
						String nomeCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
						RS = conecta.stm.executeQuery(nomeCidadeB);
						
						while(RS.next()){
							cidadeB = RS.getString("cidadeNome");
						}
						
						String buscaEstacaoB = "select * from estacao where idEstacao='"+indexEstacaoB+"'";
						RS = conecta.stm.executeQuery(buscaEstacaoB);
						
						while(RS.next()){
							estacaoB = RS.getString("estacaoSigla");
						}
						
						//Carrega dados ponta A PDH
						textArea.setText(rs.getString("observacoesCircuito"));
						textFieldCliente.setText(rs.getString("cliente"));
						comboBoxCidadeA.setSelectedItem(cidadeA);
						comboBoxPDHA.setSelectedItem(pdhA);
						textFieldPortaPDHA.setText(rs.getString("portaA"));
						textFieldDidPDHA.setText(rs.getString("didA"));
						
						//Carrega dados ponta B SDH
						comboBoxSDHB.setSelectedItem(sdhB);
						textFieldSlotSDHB.setText(rs.getString("slotB"));
						comboBoxPortaSDHB.setSelectedItem(rs.getString("portaB"));
						textFieldDidSDHB.setText(rs.getString("didB"));
						comboBoxCidadeSDHB.setSelectedItem(cidadeB);
						comboBoxEstacaoSDHB.setSelectedItem(estacaoB);
												
					}											
					
				}catch(SQLException ev){
					JOptionPane.showMessageDialog(null, "Selecione Uma Cabeceira Para a LTG");
					textFieldCircuito.setText("");
				}
				
				
				}//Fim do else if caso cabeceira não seja selecionada
					//Início da Lógica caso cabeceira seja selecionada
				else{
					textFieldCircuito.setText(circuitoDesejado);
					buttonSalvar.setEnabled(false);
					buttonAtualizar.setEnabled(true);
					ResultSet rs = null;
					ResultSet RS = null;
					String statusA = null;
					String statusB = null;
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();					
						
						String buscaStatus = "select * from circuito where circuitoNumero = '"+circuitoDesejado+"' and cabeceira='"+comboBoxCabeceira.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(buscaStatus);
					
						if(rs.next()){
							statusA = rs.getString("statusA");
							statusB = rs.getString("statusB");
							indexSDHA = rs.getInt("codSdhA");
							indexSDHB = rs.getInt("codSdhB");
							indexPdhA = rs.getInt("codPdhA");
							indexPdhB = rs.getInt("codPdhB");
							indexCidadeA = rs.getInt("codCidadeA");
							indexCidadeB = rs.getInt("codCidadeB");
							indexEstacaoA = rs.getInt("codEstacaoA");
							indexEstacaoB = rs.getInt("codEstacaoB");							
						}
						if(("s".equals(rs.getString("statusA")))&&("sB".equals(rs.getString("statusB")))){
							panel_1.setVisible(true);
							panel_2.setVisible(true);
							panel_3.setVisible(false);
							panel_4.setVisible(false);	
							chckbxSdh.setSelected(true);
							chckbxSdh_1.setSelected(true);
														
								String buscaSDH = "select * from sdh where idsdh='"+indexSDHA+"'";
								RS = conecta.stm.executeQuery(buscaSDH);
								
								while(RS.next()){
									sdhA = RS.getString("sdhNome");
								}
								
								String nomeSdhB = "select * from sdh where idsdh='"+indexSDHB+"'";
								RS = conecta.stm.executeQuery(nomeSdhB);
								
								while(RS.next()){
									sdhB = RS.getString("sdhNome");
								}
								
								String nomeCidade = "select * from cidade where idcidade='"+indexCidadeA+"'";
								RS = conecta.stm.executeQuery(nomeCidade);
								
								while(RS.next()){
									cidadeA = RS.getString("cidadeNome");
								}
								
								String buscaEstacaoA = "select * from estacao where idEstacao='"+indexEstacaoA+"'";
								RS = conecta.stm.executeQuery(buscaEstacaoA);
								
								while(RS.next()){
									estacaoA = RS.getString("estacaoSigla");
								}
								
								String nomeCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
								RS = conecta.stm.executeQuery(nomeCidadeB);
								
								while(RS.next()){
									cidadeB = RS.getString("cidadeNome");
								}
								
								String buscaEstacaoB = "select * from estacao where idEstacao='"+indexEstacaoB+"'";
								RS = conecta.stm.executeQuery(buscaEstacaoB);
								
								while(RS.next()){
									estacaoB = RS.getString("estacaoSigla");
								}								
							
							//Carregando dados da ponta A SDH
							textFieldCliente.setText(rs.getString("cliente"));
							textArea.setText(rs.getString("observacoesCircuito"));
							comboBoxSDHA.setSelectedItem(sdhA);
							comboBoxCabeceira.setSelectedItem(rs.getString("cabeceira"));
							textFieldSlotSDHA.setText(rs.getString("slotA"));
							comboBoxPortaSDHA.setSelectedItem(rs.getString("portaA"));
							textFieldDidSDHA.setText(rs.getString("didA"));	
							comboBoxCidadeSDHA.setSelectedItem(cidadeA);
							comboBoxEstacaoSDHA.setSelectedItem(estacaoA);							
							
							//Carregando dados da ponta B SDH
							comboBoxSDHB.setSelectedItem(sdhB);
							textFieldSlotSDHB.setText(rs.getString("slotB"));
							comboBoxPortaSDHB.setSelectedItem(rs.getString("portaB"));
							textFieldDidSDHB.setText(rs.getString("didB"));
							comboBoxCidadeSDHB.setSelectedItem(cidadeB);
							comboBoxEstacaoSDHB.setSelectedItem(estacaoB);
							
							
						}else if(("s".equals(rs.getString("statusA")))&&("pB".equals(rs.getString("statusB")))){
							panel_1.setVisible(true);
							panel_4.setVisible(true);
							panel_2.setVisible(false);
							panel_3.setVisible(false);
							chckbxSdh.setSelected(true);
							chckbxPdh_1.setSelected(true);
							
							String buscaSDH = "select * from sdh where idsdh='"+indexSDHA+"'";
							RS = conecta.stm.executeQuery(buscaSDH);
							
							while(RS.next()){
								sdhA = RS.getString("sdhNome");
							}
							
							String buscaCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
							RS = conecta.stm.executeQuery(buscaCidadeB);
							
							while(RS.next()){
								cidadeB = RS.getString("cidadeNome");
							}
							
							String buscaPdhB = "select * from radio where idRadio='"+indexPdhB+"'";
							RS = conecta.stm.executeQuery(buscaPdhB);
							
							while(RS.next()){
								pdhB = RS.getString("modeloRadio");
							}
							
							String nomeCidade = "select * from cidade where idcidade='"+indexCidadeA+"'";
							RS = conecta.stm.executeQuery(nomeCidade);
							
							while(RS.next()){
								cidadeA = RS.getString("cidadeNome");
							}
							
							String buscaEstacaoA = "select * from estacao where idEstacao='"+indexEstacaoA+"'";
							RS = conecta.stm.executeQuery(buscaEstacaoA);
							
							while(RS.next()){
								estacaoA = RS.getString("estacaoSigla");
							}
							
							//Carregando dados da ponta A SDH
							textFieldCliente.setText(rs.getString("cliente"));
							comboBoxCabeceira.setSelectedItem(rs.getString("cabeceira"));
							textArea.setText(rs.getString("observacoesCircuito"));
							comboBoxSDHA.setSelectedItem(sdhA);
							textFieldSlotSDHA.setText(rs.getString("slotA"));
							comboBoxPortaSDHA.setSelectedItem(rs.getString("portaA"));
							textFieldDidSDHA.setText(rs.getString("didA"));	
							comboBoxCidadeSDHA.setSelectedItem(cidadeA);
							comboBoxEstacaoSDHA.setSelectedItem(estacaoA);
							
							//Carrega dados da ponta B PDH
							comboBoxCidadeB.setSelectedItem(cidadeB);
							comboBoxPDHB.setSelectedItem(pdhB);
							textFieldPortaPDHB.setText(rs.getString("portaB"));
							textFieldDidPDHB.setText(rs.getString("didB"));
							
						}else if(("p".equals(rs.getString("statusA")))&&("pB".equals(rs.getString("statusB")))){
							panel_3.setVisible(true);
							panel_4.setVisible(true);
							panel_1.setVisible(false);
							panel_2.setVisible(false);
							chckbxPdh.setSelected(true);
							chckbxPdh_1.setSelected(true);
							
							String buscaCidadeA = "select * from cidade where idcidade='"+indexCidadeA+"'";
							RS = conecta.stm.executeQuery(buscaCidadeA);
							
							while(RS.next()){
								cidadeA = RS.getString("cidadeNome");
							}
							
							String buscaPdhA = "select * from radio where idRadio='"+indexPdhA+"'";
							RS = conecta.stm.executeQuery(buscaPdhA);
							
							while(RS.next()){
								pdhA = RS.getString("modeloRadio");
							}						
							
							String buscaCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
							RS = conecta.stm.executeQuery(buscaCidadeB);
							
							while(RS.next()){
								cidadeB = RS.getString("cidadeNome");
							}
							
							String buscaPdhB = "select * from radio where idRadio='"+indexPdhB+"'";
							RS = conecta.stm.executeQuery(buscaPdhB);
							
							while(RS.next()){
								pdhB = RS.getString("modeloRadio");
							}							
							
							//Carrega dados ponta A PDH
							textArea.setText(rs.getString("observacoesCircuito"));
							textFieldCliente.setText(rs.getString("cliente"));
							comboBoxCabeceira.setSelectedItem(rs.getString("cabeceira"));
							comboBoxCidadeA.setSelectedItem(cidadeA);
							comboBoxPDHA.setSelectedItem(pdhA);
							textFieldPortaPDHA.setText(rs.getString("portaA"));
							textFieldDidPDHA.setText(rs.getString("didA"));
							
							//Carrega dados da ponta B PDH
							comboBoxCidadeB.setSelectedItem(cidadeB);
							comboBoxPDHB.setSelectedItem(pdhB);
							textFieldPortaPDHB.setText(rs.getString("portaB"));
							textFieldDidPDHB.setText(rs.getString("didB"));		
							
							
						}else if(("p".equals(rs.getString("statusA")))&&("sB".equals(rs.getString("statusB")))){
							panel_3.setVisible(true);
							panel_2.setVisible(true);
							panel_1.setVisible(false);
							panel_4.setVisible(false);
							chckbxPdh.setSelected(true);
							chckbxSdh_1.setSelected(true);
							
							String buscaCidadeA = "select * from cidade where idcidade='"+indexCidadeA+"'";
							RS = conecta.stm.executeQuery(buscaCidadeA);
							
							while(RS.next()){
								cidadeA = RS.getString("cidadeNome");
							}
							
							String buscaPdhA = "select * from radio where idRadio='"+indexPdhA+"'";
							RS = conecta.stm.executeQuery(buscaPdhA);
							
							while(RS.next()){
								pdhA = RS.getString("modeloRadio");
							}		
							
							String nomeSdhB = "select * from sdh where idsdh='"+indexSDHB+"'";
							RS = conecta.stm.executeQuery(nomeSdhB);
							
							while(RS.next()){
								sdhB = RS.getString("sdhNome");
							}
							
							String nomeCidadeB = "select * from cidade where idcidade='"+indexCidadeB+"'";
							RS = conecta.stm.executeQuery(nomeCidadeB);
							
							while(RS.next()){
								cidadeB = RS.getString("cidadeNome");
							}
							
							String buscaEstacaoB = "select * from estacao where idEstacao='"+indexEstacaoB+"'";
							RS = conecta.stm.executeQuery(buscaEstacaoB);
							
							while(RS.next()){
								estacaoB = RS.getString("estacaoSigla");
							}
							
							//Carrega dados ponta A PDH
							textArea.setText(rs.getString("observacoesCircuito"));
							textFieldCliente.setText(rs.getString("cliente"));
							comboBoxCabeceira.setSelectedItem(rs.getString("cabeceira"));
							comboBoxCidadeA.setSelectedItem(cidadeA);
							comboBoxPDHA.setSelectedItem(pdhA);
							textFieldPortaPDHA.setText(rs.getString("portaA"));
							textFieldDidPDHA.setText(rs.getString("didA"));
							
							//Carrega dados ponta B SDH
							comboBoxSDHB.setSelectedItem(sdhB);
							textFieldSlotSDHB.setText(rs.getString("slotB"));
							comboBoxPortaSDHB.setSelectedItem(rs.getString("portaB"));
							textFieldDidSDHB.setText(rs.getString("didB"));
							comboBoxCidadeSDHB.setSelectedItem(cidadeB);
							comboBoxEstacaoSDHB.setSelectedItem(estacaoB);												
						}											
						
					}catch(SQLException ev){
						JOptionPane.showMessageDialog(null, "Não Há Circuito Para Essa LTG Na Cabeceira Selecionada");
						textFieldCircuito.setText("");
					}					
				}//Fim do segundo else
			}else{
				JOptionPane.showMessageDialog(null, "Circuito Não Cadastrado no Banco de Dados");
			}
			}
		});
		button_1.setToolTipText("Pesquisar");
		button_1.setIcon(new ImageIcon(TelaCadastroCircuito.class.getResource("/imagens/mltech/Pesquisar.png")));
		button_1.setBounds(43, 0, 45, 31);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_2.setToolTipText("Sair");
		button_2.setIcon(new ImageIcon(TelaCadastroCircuito.class.getResource("/imagens/mltech/Sair.png")));
		button_2.setBounds(126, 0, 45, 31);
		contentPane.add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 42, 274, 393);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCircuito = new JLabel("Circuito");
		lblCircuito.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCircuito.setBounds(109, 11, 54, 14);
		panel.add(lblCircuito);
		
		textFieldCircuito = new JTextField();
		textFieldCircuito.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if((arg0.getKeyCode()==KeyEvent.VK_DELETE)||(arg0.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
					buttonSalvar.setEnabled(true);
					buttonAtualizar.setEnabled(false);
				}
			}
		});
		textFieldCircuito.setBounds(74, 26, 126, 20);
		panel.add(textFieldCircuito);
		textFieldCircuito.setColumns(10);
		
		JLabel lblPontaA = new JLabel("Ponta A");
		lblPontaA.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPontaA.setBounds(10, 101, 61, 14);
		panel.add(lblPontaA);
		
		chckbxSdh = new JCheckBox("SDH");
		chckbxSdh.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxSdh.isSelected()){
					panel_1.setVisible(true);
				}else{
					panel_1.setVisible(false);
				}
			}
		});
		chckbxSdh.setBounds(10, 122, 54, 23);
		panel.add(chckbxSdh);
		
		chckbxPdh = new JCheckBox("PDH");
		chckbxPdh.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxPdh.isSelected()){
					panel_3.setVisible(true);
				}else{
					panel_3.setVisible(false);
				}
			}
		});
		chckbxPdh.setBounds(74, 122, 54, 23);
		panel.add(chckbxPdh);
		
		JLabel lblNewLabel = new JLabel("Ponta B");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 165, 61, 14);
		panel.add(lblNewLabel);
		
		chckbxSdh_1 = new JCheckBox("SDH");
		chckbxSdh_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxSdh_1.isSelected()){
					panel_2.setVisible(true);
				}else{
					panel_2.setVisible(false);
				}
			}
		});
		chckbxSdh_1.setBounds(10, 186, 61, 23);
		panel.add(chckbxSdh_1);
		
		chckbxPdh_1 = new JCheckBox("PDH");
		chckbxPdh_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxPdh_1.isSelected()){
					panel_4.setVisible(true);
				}else{
					panel_4.setVisible(false);
				}
			}
		});
		chckbxPdh_1.setBounds(76, 186, 61, 23);
		panel.add(chckbxPdh_1);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 247, 254, 135);
		panel.add(textArea);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(10, 55, 46, 14);
		panel.add(lblCliente);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(10, 70, 129, 20);
		panel.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaes.setBounds(10, 222, 81, 14);
		panel.add(lblObservaes);
		
		JLabel lblCabeceira = new JLabel("Cabeceira");
		lblCabeceira.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCabeceira.setBounds(149, 55, 73, 14);
		panel.add(lblCabeceira);
		
		comboBoxCabeceira = new JComboBox();
		comboBoxCabeceira.setModel(new DefaultComboBoxModel(new String[] {"", "PMM", "SAN", "SMA"}));
		comboBoxCabeceira.setBounds(149, 70, 88, 20);
		panel.add(comboBoxCabeceira);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(136, 122, 126, 113);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.setBounds(8, 21, 89, 25);
		panel_5.add(rdbtnAtivo);
		
		rdbtnDesativado = new JRadioButton("Desativado");
		rdbtnDesativado.setBounds(8, 51, 110, 25);
		panel_5.add(rdbtnDesativado);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(284, 42, 365, 161);
		contentPane.add(layeredPane);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ponta A PDH", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_3.setBounds(0, 0, 365, 161);
		layeredPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblLocalidade = new JLabel("Localidade");
		lblLocalidade.setBounds(10, 25, 67, 14);
		panel_3.add(lblLocalidade);
		
		comboBoxCidadeA = new JComboBox();
		comboBoxCidadeA.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxCidadeA.setBounds(10, 41, 200, 20);
		panel_3.add(comboBoxCidadeA);
		
		JLabel lblEquipamento_2 = new JLabel("Equipamento");
		lblEquipamento_2.setBounds(220, 25, 80, 14);
		panel_3.add(lblEquipamento_2);
		
		comboBoxPDHA = new JComboBox();
		comboBoxPDHA.setBounds(220, 41, 102, 20);
		panel_3.add(comboBoxPDHA);
		
		JLabel lblPorta_2 = new JLabel("Porta");
		lblPorta_2.setBounds(10, 70, 46, 14);
		panel_3.add(lblPorta_2);
		
		textFieldPortaPDHA = new JTextField();
		textFieldPortaPDHA.setBounds(10, 85, 86, 20);
		panel_3.add(textFieldPortaPDHA);
		textFieldPortaPDHA.setColumns(10);
		
		JLabel lblDid_2 = new JLabel("DID");
		lblDid_2.setBounds(108, 70, 46, 14);
		panel_3.add(lblDid_2);
		
		textFieldDidPDHA = new JTextField();
		textFieldDidPDHA.setBounds(105, 85, 126, 20);
		panel_3.add(textFieldDidPDHA);
		textFieldDidPDHA.setColumns(10);
		panel_3.setVisible(false);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ponta A SDH", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(0, 0, 365, 161);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEquipamento = new JLabel("Equipamento");
		lblEquipamento.setBounds(10, 21, 108, 14);
		panel_1.add(lblEquipamento);
		
		comboBoxSDHA = new JComboBox();
		comboBoxSDHA.setBounds(10, 39, 85, 20);
		panel_1.add(comboBoxSDHA);
		
		JLabel lblSlot = new JLabel("Slot");
		lblSlot.setBounds(105, 21, 36, 14);
		panel_1.add(lblSlot);
		
		textFieldSlotSDHA = new JTextField();
		textFieldSlotSDHA.setBounds(103, 39, 59, 20);
		panel_1.add(textFieldSlotSDHA);
		textFieldSlotSDHA.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta");
		lblPorta.setBounds(169, 21, 46, 14);
		panel_1.add(lblPorta);
		
		comboBoxPortaSDHA = new JComboBox();
		comboBoxPortaSDHA.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63"}));
		comboBoxPortaSDHA.setBounds(172, 39, 71, 20);
		panel_1.add(comboBoxPortaSDHA);
		
		JLabel lblDid = new JLabel("DID");
		lblDid.setBounds(249, 21, 46, 14);
		panel_1.add(lblDid);
		
		textFieldDidSDHA = new JTextField();
		textFieldDidSDHA.setBounds(253, 39, 86, 20);
		panel_1.add(textFieldDidSDHA);
		textFieldDidSDHA.setColumns(10);
		
		JLabel lblLocalidade_2 = new JLabel("Localidade");
		lblLocalidade_2.setBounds(10, 70, 91, 14);
		panel_1.add(lblLocalidade_2);
		
		comboBoxCidadeSDHA = new JComboBox();
		comboBoxCidadeSDHA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs = null;
				ResultSet RS = null;
				int indiceCidade = 0;
				comboBoxEstacaoSDHA.removeAllItems();
				String cidade = (String) comboBoxCidadeSDHA.getSelectedItem();
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					//Busca o índice da cidade selecionada
					String buscaIndiceCidade = "select idcidade from cidade where cidadeNome= '"+comboBoxCidadeSDHA.getSelectedItem()+"'";
					RS = conecta.stm.executeQuery(buscaIndiceCidade);
					while(RS.next()){
						indiceCidade = RS.getInt("idcidade");
					}//Fim do método onde busca o índice da cidade				
					
					String buscaEstacao = "select * from estacao where idcidade='"+indiceCidade+"'";
					rs = conecta.stm.executeQuery(buscaEstacao);
					while(rs.next()){						
						comboBoxEstacaoSDHA.addItem(rs.getString("estacaoSigla"));
					}					
					
				}catch(SQLException ev){
					JOptionPane.showMessageDialog(null, ev);
				}
			}
		});
		comboBoxCidadeSDHA.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxCidadeSDHA.setBounds(10, 88, 205, 20);
		panel_1.add(comboBoxCidadeSDHA);
		
		JLabel lblEstao = new JLabel("Esta\u00E7\u00E3o");
		lblEstao.setBounds(236, 70, 59, 14);
		panel_1.add(lblEstao);
		
		comboBoxEstacaoSDHA = new JComboBox();
		comboBoxEstacaoSDHA.setBounds(236, 88, 86, 20);
		panel_1.add(comboBoxEstacaoSDHA);
		//Inicia os painéis não visíveis
		panel_1.setVisible(false);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(284, 214, 365, 210);
		contentPane.add(layeredPane_1);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Ponta B PDH", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_4.setBounds(0, 0, 365, 210);
		layeredPane_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblLocalidade_1 = new JLabel("Localidade");
		lblLocalidade_1.setBounds(10, 25, 78, 14);
		panel_4.add(lblLocalidade_1);
		
		comboBoxCidadeB = new JComboBox();
		comboBoxCidadeB.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxCidadeB.setBounds(10, 42, 199, 20);
		panel_4.add(comboBoxCidadeB);
		
		JLabel lblEquipamento_3 = new JLabel("Equipamento");
		lblEquipamento_3.setBounds(219, 25, 78, 14);
		panel_4.add(lblEquipamento_3);
		
		comboBoxPDHB = new JComboBox();
		comboBoxPDHB.setBounds(219, 42, 95, 20);
		panel_4.add(comboBoxPDHB);
		
		JLabel lblPorta_3 = new JLabel("Porta");
		lblPorta_3.setBounds(10, 89, 46, 14);
		panel_4.add(lblPorta_3);
		
		textFieldPortaPDHB = new JTextField();
		textFieldPortaPDHB.setBounds(10, 105, 86, 20);
		panel_4.add(textFieldPortaPDHB);
		textFieldPortaPDHB.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DID");
		lblNewLabel_1.setBounds(115, 89, 46, 14);
		panel_4.add(lblNewLabel_1);
		
		textFieldDidPDHB = new JTextField();
		textFieldDidPDHB.setBounds(113, 105, 86, 20);
		panel_4.add(textFieldDidPDHB);
		textFieldDidPDHB.setColumns(10);
		panel_4.setVisible(false);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Ponta B SDH", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_2.setBounds(0, 0, 365, 210);
		layeredPane_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblEquipamento_1 = new JLabel("Equipamento");
		lblEquipamento_1.setBounds(10, 26, 81, 14);
		panel_2.add(lblEquipamento_1);
		
		comboBoxSDHB = new JComboBox();
		comboBoxSDHB.setBounds(10, 44, 81, 20);
		panel_2.add(comboBoxSDHB);
		
		JLabel lblSlot_1 = new JLabel("Slot");
		lblSlot_1.setBounds(101, 26, 46, 14);
		panel_2.add(lblSlot_1);
		
		textFieldSlotSDHB = new JTextField();
		textFieldSlotSDHB.setBounds(101, 44, 60, 20);
		panel_2.add(textFieldSlotSDHB);
		textFieldSlotSDHB.setColumns(10);
		
		JLabel lblPorta_1 = new JLabel("Porta");
		lblPorta_1.setBounds(171, 26, 46, 14);
		panel_2.add(lblPorta_1);
		
		comboBoxPortaSDHB = new JComboBox();
		comboBoxPortaSDHB.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63"}));
		comboBoxPortaSDHB.setBounds(171, 44, 67, 20);
		panel_2.add(comboBoxPortaSDHB);
		
		JLabel lblDid_1 = new JLabel("DID");
		lblDid_1.setBounds(246, 26, 46, 14);
		panel_2.add(lblDid_1);
		
		textFieldDidSDHB = new JTextField();
		textFieldDidSDHB.setBounds(248, 44, 86, 20);
		panel_2.add(textFieldDidSDHB);
		textFieldDidSDHB.setColumns(10);
		
		lblLocalidade_3 = new JLabel("Localidade");
		lblLocalidade_3.setBounds(10, 83, 81, 14);
		panel_2.add(lblLocalidade_3);
		
		comboBoxCidadeSDHB = new JComboBox();
		comboBoxCidadeSDHB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				ResultSet RS = null;
				int indiceCidade = 0;
				comboBoxEstacaoSDHB.removeAllItems();
				String cidade = (String) comboBoxCidadeSDHB.getSelectedItem();
				try{
					ConectaBanco conecta = new ConectaBanco();
					conecta.conectaBanco();
					
					//Busca o índice da cidade selecionada
					String buscaIndiceCidade = "select idcidade from cidade where cidadeNome= '"+comboBoxCidadeSDHB.getSelectedItem()+"'";
					RS = conecta.stm.executeQuery(buscaIndiceCidade);
					while(RS.next()){
						indiceCidade = RS.getInt("idcidade");
					}//Fim do método onde busca o índice da cidade				
					
					String buscaEstacao = "select * from estacao where idcidade='"+indiceCidade+"'";
					rs = conecta.stm.executeQuery(buscaEstacao);
					while(rs.next()){						
						comboBoxEstacaoSDHB.addItem(rs.getString("estacaoSigla"));
					}					
					
				}catch(SQLException ev){
					JOptionPane.showMessageDialog(null, ev);
				}
			}
		});
		comboBoxCidadeSDHB.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBoxCidadeSDHB.setBounds(10, 101, 213, 20);
		panel_2.add(comboBoxCidadeSDHB);
		
		lblEstao_1 = new JLabel("Esta\u00E7\u00E3o");
		lblEstao_1.setBounds(246, 83, 60, 14);
		panel_2.add(lblEstao_1);
		
		comboBoxEstacaoSDHB = new JComboBox();
		comboBoxEstacaoSDHB.setBounds(246, 101, 88, 20);
		panel_2.add(comboBoxEstacaoSDHB);
		panel_2.setVisible(false);
		
		buttonSalvar = new JButton("");
		buttonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flagCliente = false;
				boolean flagCircuito = false;
				boolean flagSlotA = false;
				boolean flagSlotB = false;
				boolean flagPortaSDHA = false;
				boolean flagPortaSDHB = false;
				boolean flagCidadeB = false;
				boolean flagCidadeA = false;
				boolean flagPortaPDHB = false;
				boolean flagPortaPDHA = false;
				int valorSlotA = 0;
				int valorSlotB = 0;
				int indiceCidadeA = 0;
				int indiceCidadeB = 0;
				ResultSet rs = null;
				
				//Verifica se o nome do cliente foi inserido
				if(textFieldCliente.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o Nome do Cliente");
				}else{
					flagCliente = true;
				}
				
				//Verifica se o número do circuito foi inserido
				if(textFieldCircuito.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Informe o Número do Circuito");
				}else{
					flagCircuito = true;
				}
				
				if((chckbxPdh.isSelected()==false)&&(chckbxSdh.isSelected()==false)&&(chckbxPdh_1.isSelected()==false)&&(chckbxSdh_1.isSelected()==false)){
					JOptionPane.showMessageDialog(null, "Selecione Uma Tecnologia Para as Pontas");
				}else if(chckbxSdh.isSelected()&&(chckbxSdh_1.isSelected())){
					if(textFieldSlotSDHA.getText().isEmpty()){
						
					}else{
						flagSlotA = true;
					}if(textFieldSlotSDHB.getText().isEmpty()){
						
					}else{
						flagSlotB = true;
					}	
						//Verifica o dado inserido no slot A
						if(textFieldSlotSDHA.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Informe Um Slot Válido Para Ponta A");
						}else{
							flagSlotA = true;
						}
						//Verifica o dado inserido no slot B
						if((valorSlotB<0)||(textFieldSlotSDHB.getText().isEmpty())){
							JOptionPane.showMessageDialog(null, "Informe Um Slot Válido Para Ponta B");
						}else{
							flagSlotB = true;
						}
						
						//Verifica se foi escolhido porta na ponta A
						if(comboBoxPortaSDHA.getSelectedItem()==""){
							JOptionPane.showMessageDialog(null, "Selecione Uma Porta Válida Para Ponta A");
						}else{
							flagPortaSDHA = true;
						}
						
						//Verifica se foi escolhida porta na ponta B
						if(comboBoxPortaSDHB.getSelectedItem()==""){
							JOptionPane.showMessageDialog(null, "Selecione Uma Porta Válida Para Ponta B");
						}else{
							flagPortaSDHB = true;
						}
				}//Fim da lógica caso seja selecionado SDH nas duas pontas
				else if((chckbxSdh.isSelected())&&(chckbxPdh_1.isSelected())){
							if(textFieldSlotSDHA.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe Um Slot Para a Ponta A");
					}else{
						flagSlotA = true;
					}
							
							//Verifica se foi escolhido porta na ponta A
							if(comboBoxPortaSDHA.getSelectedItem()==""){
								JOptionPane.showMessageDialog(null, "Selecione Uma Porta Válida Para Ponta A");
							}else{
								flagPortaSDHA = true;
							}
							//Verifica se foi selecionada uma cidade para Ponta B
							if(comboBoxCidadeB.getSelectedItem()==""){
								JOptionPane.showMessageDialog(null, "Informe Uma Localidade para Ponta B");
							}else{
								flagCidadeB = true;
							}
							
							if(textFieldPortaPDHB.getText().isEmpty()){
								JOptionPane.showMessageDialog(null, "Informe Uma Porta Válida Para o Rádio da Ponta B");
							}else{
								int valorPortaPDHB = Integer.parseInt(textFieldPortaPDHB.getText());
									if(valorPortaPDHB<1){
										JOptionPane.showMessageDialog(null, "Informe Um Valor Válido Para Porta da Ponta B");
									}else{
										flagPortaPDHB = true;
									}
							}					
				}//Fim da lógica caso seja selecionado SDH na ponta A e PDH na ponta B
				else if((chckbxPdh.isSelected())&&(chckbxSdh_1.isSelected())){
						if(comboBoxCidadeA.getSelectedItem()==""){
							JOptionPane.showMessageDialog(null, "Informe Uma Localidade para Ponta A");
						}else{
							flagCidadeA = true;
						}
						if(textFieldPortaPDHA.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Informe Um Valor Válido Para Porta da Ponta A");
						}else{
								int valorPortaPDHA = Integer.parseInt(textFieldPortaPDHA.getText());
								if(valorPortaPDHA<1){
									JOptionPane.showMessageDialog(null, "Informe Um Valor Válido Para Porta da Ponta A");
								}else{
									flagPortaPDHA = true;
								}
						}
						//Início da Lógica Para Verificação dos Dados do SDH na ponta B
						if(textFieldSlotSDHB.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Informe Um Slot Válido Para SDH na Ponta B");
						}else{
							flagSlotB = true;
						}	
						if(comboBoxPortaSDHB.getSelectedItem()==""){
							JOptionPane.showMessageDialog(null, "Selecione Uma Porta Válida Para Ponta A");
						}else{
							flagPortaSDHB = true;
						}					
					
				}//Fim da lógica caso seja selecionado PDH na ponta A e SDH na ponta B
				//Início da Lógica Caso Seja Selecionado PDH nas duas pontas
				if((chckbxPdh.isSelected())&&(chckbxPdh_1.isSelected())){
					//Lógica Para Verificação dos Dados do PDH na ponta A
					if(comboBoxCidadeA.getSelectedItem()==""){
						JOptionPane.showMessageDialog(null, "Informe Uma Localidade para Ponta A");
					}else{
						flagCidadeA = true;
					}
					if(textFieldPortaPDHA.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe Um Valor Válido Para Porta da Ponta A");
					}else{
							int valorPortaPDHA = Integer.parseInt(textFieldPortaPDHA.getText());
							if(valorPortaPDHA<1){
								JOptionPane.showMessageDialog(null, "Informe Um Valor Válido Para Porta da Ponta A");
							}else{
								flagPortaPDHA = true;
							}
					}
					//Lógica Para Verificação dos Dados do PDH na ponta B
					if(comboBoxCidadeB.getSelectedItem()==""){
						JOptionPane.showMessageDialog(null, "Informe Uma Localidade para Ponta B");
					}else{
						flagCidadeB = true;
					}
					
					if(textFieldPortaPDHB.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Informe Uma Porta Válida Para o Rádio da Ponta B");
					}else{
						int valorPortaPDHB = Integer.parseInt(textFieldPortaPDHB.getText());
							if(valorPortaPDHB<1){
								JOptionPane.showMessageDialog(null, "Informe Um Valor Válido Para Porta da Ponta B");
							}else{
								flagPortaPDHB = true;
							}
					}						
					
				}//Fim da Lógica Caso Seja Selecionado PDH nas duas pontas
				
				if((chckbxSdh.isSelected())&&(chckbxSdh_1.isSelected())){
					
						if((flagCircuito==true)&&(flagCliente==true)&&(flagPortaSDHA==true)&&(flagPortaSDHB==true)&&(flagSlotA==true)&&(flagSlotB==true)){
							Circuito novoCircuito = new Circuito();
							boolean verifica = novoCircuito.verificaCircuito(textFieldCircuito.getText());
							if(verifica==true){
								JOptionPane.showMessageDialog(null, "Circuito Já Cadastrado");
								buttonSalvar.setEnabled(false);
							}else{
								try{
									ConectaBanco conecta = new ConectaBanco();
									conecta.conectaBanco();
									
									String indiceSDHA = "select * from sdh where sdhNome='"+comboBoxSDHA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(indiceSDHA);
									
									while(rs.next()){
										indexSDHA = rs.getInt("idsdh");
									}
									
									String indiceSDHB = "select * from sdh where sdhNome='"+comboBoxSDHB.getSelectedItem()+"'"; 
									rs = conecta.stm.executeQuery(indiceSDHB);
									
									while(rs.next()){
										indexSDHB = rs.getInt("idsdh");
									}
									
									String buscaIndice = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(buscaIndice);
									
									while(rs.next()){
										indiceCidadeA = rs.getInt("idcidade");
									}
									
									String buscaIndiceB = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHB.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(buscaIndiceB);
									
									while(rs.next()){
										indiceCidadeB = rs.getInt("idcidade");
									}
									
									String indiceEstacaoA = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(indiceEstacaoA);
									
									while(rs.next()){
										indexEstacaoA = rs.getInt("idEstacao");
									}
									
									String indiceEstacaoB = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHB.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(indiceEstacaoB);
									
									while(rs.next()){
										indexEstacaoB = rs.getInt("idEstacao");
									}									
									
								}catch(SQLException e){
									JOptionPane.showMessageDialog(null, e);
								}
							novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
							novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
							novoCircuito.setObervacoes(textArea.getText());
							novoCircuito.setEquipamentoSDHA(indexSDHA);
							novoCircuito.setSlotA(textFieldSlotSDHA.getText());
							novoCircuito.setPortaA((String)comboBoxPortaSDHA.getSelectedItem());
							novoCircuito.setDidA(textFieldDidSDHA.getText());
							novoCircuito.setEquipamentoSDHB(indexSDHB);
							novoCircuito.setSlotB(textFieldSlotSDHB.getText());
							novoCircuito.setPortaB((String)comboBoxPortaSDHB.getSelectedItem());
							novoCircuito.setDidB(textFieldDidSDHB.getText());
							novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
							novoCircuito.setStatusA("s");
							novoCircuito.setStatusB("sB");
							novoCircuito.setCidadeA(indiceCidadeA);
							novoCircuito.setCidadeB(indiceCidadeB);
							novoCircuito.setEstacaoA(indexEstacaoA);
							novoCircuito.setEstacaoB(indexEstacaoB);	
							if(rdbtnAtivo.isSelected()){
								novoCircuito.setStatusCircuito("ativo");
							}else if(rdbtnDesativado.isSelected()){
								novoCircuito.setStatusCircuito("desativado");
							}
							novoCircuito.insereCircuito();
							textFieldCircuito.setText("");
							textFieldCliente.setText("");
							textArea.setText("");
							textFieldSlotSDHA.setText("");
							textFieldSlotSDHB.setText("");
							textFieldDidSDHA.setText("");
							textFieldDidSDHB.setText("");
							}
						}
					
				}//Fim da lógica de verificação e inserção dos dados para SDH nas duas pontas
				//Início da lógica Para Inserção de Dados para SDH na ponta A e PDH na ponta B
				if((chckbxSdh.isSelected())&&(chckbxPdh_1.isSelected())){
					
						if((flagCircuito==true)&&(flagCliente==true)&&(flagPortaSDHA==true)&&(flagSlotA==true)&&(flagCidadeB==true)&&(flagPortaPDHB==true)){
							Circuito novoCircuito = new Circuito();
							boolean verifica = novoCircuito.verificaCircuito(textFieldCircuito.getText());
							if(verifica==true){
								JOptionPane.showMessageDialog(null, "Circuito Já Cadastrado");
								buttonSalvar.setEnabled(false);
							}else{
								
								try{
									ConectaBanco conecta = new ConectaBanco();
									conecta.conectaBanco();
									
									String buscaIndice = "select * from cidade where cidadeNome='"+comboBoxCidadeB.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(buscaIndice);
									
									while(rs.next()){
										indiceCidadeB = rs.getInt("idcidade");
									}
									
									String indiceSDHA = "select * from sdh where sdhNome='"+comboBoxSDHA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(indiceSDHA);
									
									while(rs.next()){
										indexSDHA = rs.getInt("idsdh");
									}
									
									String buscaIndex = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(buscaIndex);
									
									while(rs.next()){
										indiceCidadeA = rs.getInt("idcidade");
									}
									
									String indiceEstacaoA = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(indiceEstacaoA);
									
									while(rs.next()){
										indexEstacaoA = rs.getInt("idEstacao");
									}
									
								}catch(SQLException e){
									JOptionPane.showMessageDialog(null, e);
								}
								novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
								novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
								novoCircuito.setObervacoes(textArea.getText());
								novoCircuito.setEquipamentoSDHA(indexSDHA);
								novoCircuito.setSlotA(textFieldSlotSDHA.getText());
								novoCircuito.setPortaA((String)comboBoxPortaSDHA.getSelectedItem());
								novoCircuito.setDidA(textFieldDidSDHA.getText());
								novoCircuito.setCidadeB(indiceCidadeB);
								novoCircuito.setEquipamentoPDHB(comboBoxPDHB.getSelectedIndex());
								novoCircuito.setPortaB(textFieldPortaPDHB.getText());
								novoCircuito.setDidB(textFieldDidPDHB.getText());
								novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
								novoCircuito.setStatusA("s");
								novoCircuito.setStatusB("pB");
								novoCircuito.setCidadeA(indiceCidadeA);
								novoCircuito.setEstacaoA(indexEstacaoA);
								novoCircuito.setEstacaoB(1);
								if(rdbtnAtivo.isSelected()){
									novoCircuito.setStatusCircuito("ativo");
								}else if(rdbtnDesativado.isSelected()){
									novoCircuito.setStatusCircuito("desativado");
								}
								novoCircuito.insereCircuito();
								textFieldCircuito.setText("");
								textFieldCliente.setText("");
								textArea.setText("");
								textFieldSlotSDHA.setText("");
								textFieldDidSDHA.setText("");
								textFieldPortaPDHB.setText("");
								textFieldDidPDHB.setText("");								
							}							
						}					
				}//Fim da lógica de verificação e inserção dos dados para SDH na ponta A e PDH na ponta B
				//Início da Lógica de verificação e inserção dos dados para PDH na ponta A e SDH na ponta B
				if((chckbxPdh.isSelected())&&(chckbxSdh_1.isSelected())){
					
					Circuito novoCircuito = new Circuito();
					boolean verifica = novoCircuito.verificaCircuito(textFieldCircuito.getText());
					
						if((flagCidadeA==true)&&(flagPortaPDHA==true)&&(flagCircuito==true)&&(flagCliente==true)&&(flagPortaSDHB==true)&&(flagSlotB==true)){
							if(verifica==true){
								JOptionPane.showMessageDialog(null, "Circuito Já Cadastrado");
								buttonSalvar.setEnabled(false);
							}else{
								try{
									ConectaBanco conecta = new ConectaBanco();
									conecta.conectaBanco();
									
									String buscaIndice = "select * from cidade where cidadeNome='"+comboBoxCidadeA.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(buscaIndice);
									
									while(rs.next()){
										indiceCidadeA = rs.getInt("idcidade");
									}
									
									String indiceSDHB = "select * from sdh where sdhNome='"+comboBoxSDHB.getSelectedItem()+"'"; 
									rs = conecta.stm.executeQuery(indiceSDHB);
									
									while(rs.next()){
										indexSDHB = rs.getInt("idsdh");
									}
									
									String buscaIndiceB = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHB.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(buscaIndiceB);
									
									while(rs.next()){
										indiceCidadeB = rs.getInt("idcidade");
									}
									
									String indiceEstacaoB = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHB.getSelectedItem()+"'";
									rs = conecta.stm.executeQuery(indiceEstacaoB);
									
									while(rs.next()){
										indexEstacaoB = rs.getInt("idEstacao");
									}
									
								}catch(SQLException e){
									JOptionPane.showMessageDialog(null, e);
								}
								novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
								novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
								novoCircuito.setObervacoes(textArea.getText());
								novoCircuito.setCidadeA(indiceCidadeA);
								novoCircuito.setEquipamentoPDHA(comboBoxPDHA.getSelectedIndex()+1);
								novoCircuito.setPortaA(textFieldPortaPDHA.getText());
								novoCircuito.setDidA(textFieldDidPDHA.getText());
								novoCircuito.setEquipamentoSDHB(indexSDHB);
								novoCircuito.setSlotB(textFieldSlotSDHB.getText());
								novoCircuito.setPortaB((String)comboBoxPortaSDHB.getSelectedItem());
								novoCircuito.setDidB(textFieldDidSDHB.getText());
								novoCircuito.setStatusA("p");
								novoCircuito.setStatusB("sB");
								novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
								novoCircuito.setCidadeB(indiceCidadeB);
								novoCircuito.setEstacaoB(indexEstacaoB);
								novoCircuito.setEstacaoA(1);
								if(rdbtnAtivo.isSelected()){
									novoCircuito.setStatusCircuito("ativo");
								}else if(rdbtnDesativado.isSelected()){
									novoCircuito.setStatusCircuito("desativado");
								}
								novoCircuito.insereCircuito();
								textFieldCircuito.setText("");
								textFieldCliente.setText("");
								textArea.setText("");
								textFieldPortaPDHA.setText("");
								textFieldDidPDHA.setText("");
							}							
						}					
					
				}//Fim da Lógica de verificação e inserção dos dados para PDH na ponta A e SDH na ponta B
				//Início da Lógica de verificação e inserção de dados para PDH nas duas pontas
				if((chckbxPdh.isSelected())&&(chckbxPdh_1.isSelected())){
					Circuito novoCircuito = new Circuito();
					boolean verifica = novoCircuito.verificaCircuito(textFieldCircuito.getText());
					if((flagCidadeA==true)&&(flagCidadeB==true)&&(flagCircuito==true)&&(flagCliente==true)&&(flagPortaPDHA==true)&&(flagPortaPDHB==true)){
						
						try{
							ConectaBanco conecta = new ConectaBanco();
							conecta.conectaBanco();
							
							String indexCidadeA = "select * from cidade where cidadeNome='"+comboBoxCidadeA.getSelectedItem()+"'";
							rs = conecta.stm.executeQuery(indexCidadeA);
							while(rs.next()){
								indiceCidadeA = rs.getInt("idcidade");
							}
							
							String indexCidadeB = "select * from cidade where cidadeNome='"+comboBoxCidadeB.getSelectedItem()+"'";
							rs = conecta.stm.executeQuery(indexCidadeB);
							
							while(rs.next()){
								indiceCidadeB = rs.getInt("idcidade");
							}
							novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
							novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
							novoCircuito.setObervacoes(textArea.getText());
							novoCircuito.setCidadeA(indiceCidadeA);
							novoCircuito.setEquipamentoPDHA(comboBoxPDHA.getSelectedIndex()+1);
							novoCircuito.setPortaA(textFieldPortaPDHA.getText());
							novoCircuito.setDidA(textFieldDidPDHA.getText());
							novoCircuito.setCidadeB(indiceCidadeB);
							novoCircuito.setEquipamentoPDHB(comboBoxPDHB.getSelectedIndex());
							novoCircuito.setPortaB(textFieldPortaPDHB.getText());
							novoCircuito.setDidB(textFieldDidPDHB.getText());
							novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
							novoCircuito.setStatusA("p");
							novoCircuito.setStatusB("pB");
							if(rdbtnAtivo.isSelected()){
								novoCircuito.setStatusCircuito("ativo");
							}else if(rdbtnDesativado.isSelected()){
								novoCircuito.setStatusCircuito("desativado");
							}
							novoCircuito.insereCircuito();
							textFieldCircuito.setText("");
							textFieldCliente.setText("");
							textArea.setText("");
							textFieldDidPDHA.setText("");
							textFieldDidPDHB.setText("");
							textFieldPortaPDHA.setText("");
							textFieldPortaPDHB.setText("");
							
						}catch(SQLException e){
							JOptionPane.showMessageDialog(null, e);
						}						
					}					
				}//Fim da Lógica de verificação e inserção de dados para PDH nas duas pontas
				
			}
		});//Fim do ActionPerformed do botão Salvar
		buttonSalvar.setToolTipText("Salvar");
		buttonSalvar.setIcon(new ImageIcon(TelaCadastroCircuito.class.getResource("/imagens/mltech/Salvar.png")));
		buttonSalvar.setBounds(0, 0, 45, 31);
		contentPane.add(buttonSalvar);
		
		buttonAtualizar = new JButton("");
		buttonAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Circuito novoCircuito = new Circuito();
				ResultSet rs = null;
				int valorSlotA = 0;
				int valorSlotB = 0;
				int indiceCidadeA = 0;
				int indiceCidadeB = 0;
				String numeroCircuito = textFieldCircuito.getText();
				String nomeCabeceira = (String)comboBoxCabeceira.getSelectedItem();
				
				if((chckbxSdh.isSelected())&&(chckbxSdh_1.isSelected())){
					
							try{
								ConectaBanco conecta = new ConectaBanco();
								conecta.conectaBanco();
								
								String indiceSDHA = "select * from sdh where sdhNome='"+comboBoxSDHA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(indiceSDHA);
								
								while(rs.next()){
									indexSDHA = rs.getInt("idsdh");
								}
								
								String indiceSDHB = "select * from sdh where sdhNome='"+comboBoxSDHB.getSelectedItem()+"'"; 
								rs = conecta.stm.executeQuery(indiceSDHB);
								
								while(rs.next()){
									indexSDHB = rs.getInt("idsdh");
								}
								
								String buscaIndice = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndice);
								
								while(rs.next()){
									indiceCidadeA = rs.getInt("idcidade");
								}
								
								String buscaIndiceB = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHB.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndiceB);
								
								while(rs.next()){
									indiceCidadeB = rs.getInt("idcidade");
								}
								
								String indiceEstacaoA = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(indiceEstacaoA);
								
								while(rs.next()){
									indexEstacaoA = rs.getInt("idEstacao");
								}
								
								String indiceEstacaoB = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHB.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(indiceEstacaoB);
								
								while(rs.next()){
									indexEstacaoB = rs.getInt("idEstacao");
								}									
								
							}catch(SQLException e){
								JOptionPane.showMessageDialog(null, e);
							}
						novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
						novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
						novoCircuito.setObervacoes(textArea.getText());
						novoCircuito.setEquipamentoSDHA(indexSDHA);
						novoCircuito.setSlotA(textFieldSlotSDHA.getText());
						novoCircuito.setPortaA((String)comboBoxPortaSDHA.getSelectedItem());
						novoCircuito.setDidA(textFieldDidSDHA.getText());
						novoCircuito.setEquipamentoSDHB(indexSDHB);
						novoCircuito.setSlotB(textFieldSlotSDHB.getText());
						novoCircuito.setPortaB((String)comboBoxPortaSDHB.getSelectedItem());
						novoCircuito.setDidB(textFieldDidSDHB.getText());
						novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
						novoCircuito.setStatusA("s");
						novoCircuito.setStatusB("sB");
						novoCircuito.setCidadeA(indiceCidadeA);
						novoCircuito.setCidadeB(indiceCidadeB);
						novoCircuito.setEstacaoA(indexEstacaoA);
						novoCircuito.setEstacaoB(indexEstacaoB);	
						if(rdbtnAtivo.isSelected()){
							novoCircuito.setStatusCircuito("ativo");
						}else if(rdbtnDesativado.isSelected()){
							novoCircuito.setStatusCircuito("desativado");
						}
						novoCircuito.atualizaCircuito(numeroCircuito, nomeCabeceira);
						textFieldCircuito.setText("");
						textFieldCliente.setText("");
						textArea.setText("");
						textFieldSlotSDHA.setText("");
						textFieldSlotSDHB.setText("");
						textFieldDidSDHA.setText("");
						textFieldDidSDHB.setText("");
					    buttonSalvar.setEnabled(true);
					    buttonAtualizar.setEnabled(false);
				
			}//Fim da lógica de verificação e inserção dos dados para SDH nas duas pontas
			//Início da lógica Para Inserção de Dados para SDH na ponta A e PDH na ponta B
			if((chckbxSdh.isSelected())&&(chckbxPdh_1.isSelected())){
							
							try{
								ConectaBanco conecta = new ConectaBanco();
								conecta.conectaBanco();
								
								String buscaIndice = "select * from cidade where cidadeNome='"+comboBoxCidadeB.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndice);
								
								while(rs.next()){
									indiceCidadeB = rs.getInt("idcidade");
								}
								
								String indiceSDHA = "select * from sdh where sdhNome='"+comboBoxSDHA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(indiceSDHA);
								
								while(rs.next()){
									indexSDHA = rs.getInt("idsdh");
								}
								
								String buscaIndex = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndex);
								
								while(rs.next()){
									indiceCidadeA = rs.getInt("idcidade");
								}
								
								String indiceEstacaoA = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(indiceEstacaoA);
								
								while(rs.next()){
									indexEstacaoA = rs.getInt("idEstacao");
								}
								
							}catch(SQLException e){
								JOptionPane.showMessageDialog(null, e);
							}
							novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
							novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
							novoCircuito.setObervacoes(textArea.getText());
							novoCircuito.setEquipamentoSDHA(indexSDHA);
							novoCircuito.setSlotA(textFieldSlotSDHA.getText());
							novoCircuito.setPortaA((String)comboBoxPortaSDHA.getSelectedItem());
							novoCircuito.setDidA(textFieldDidSDHA.getText());
							novoCircuito.setCidadeB(indiceCidadeB);
							novoCircuito.setEquipamentoPDHB(comboBoxPDHB.getSelectedIndex());
							novoCircuito.setPortaB(textFieldPortaPDHB.getText());
							novoCircuito.setDidB(textFieldDidPDHB.getText());
							novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
							novoCircuito.setStatusA("s");
							novoCircuito.setStatusB("pB");
							novoCircuito.setCidadeA(indiceCidadeA);
							novoCircuito.setEstacaoA(indexEstacaoA);
							novoCircuito.setEstacaoB(1);
							if(rdbtnAtivo.isSelected()){
								novoCircuito.setStatusCircuito("ativo");
							}else if(rdbtnDesativado.isSelected()){
								novoCircuito.setStatusCircuito("desativado");
							}
							novoCircuito.atualizaCircuito(numeroCircuito, nomeCabeceira);
							textFieldCircuito.setText("");
							textFieldCliente.setText("");
							textArea.setText("");
							textFieldSlotSDHA.setText("");
							textFieldDidSDHA.setText("");
							textFieldPortaPDHB.setText("");
							textFieldDidPDHB.setText("");	
							buttonSalvar.setEnabled(true);
						    buttonAtualizar.setEnabled(false);
													
										
			}//Fim da lógica de verificação e inserção dos dados para SDH na ponta A e PDH na ponta B
			//Início da Lógica de verificação e inserção dos dados para PDH na ponta A e SDH na ponta B
			if((chckbxPdh.isSelected())&&(chckbxSdh_1.isSelected())){

							try{
								ConectaBanco conecta = new ConectaBanco();
								conecta.conectaBanco();
								
								String buscaIndice = "select * from cidade where cidadeNome='"+comboBoxCidadeA.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndice);
								
								while(rs.next()){
									indiceCidadeA = rs.getInt("idcidade");
								}
								
								String indiceSDHB = "select * from sdh where sdhNome='"+comboBoxSDHB.getSelectedItem()+"'"; 
								rs = conecta.stm.executeQuery(indiceSDHB);
								
								while(rs.next()){
									indexSDHB = rs.getInt("idsdh");
								}
								
								String buscaIndiceB = "select * from cidade where cidadeNome='"+comboBoxCidadeSDHB.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(buscaIndiceB);
								
								while(rs.next()){
									indiceCidadeB = rs.getInt("idcidade");
								}
								
								String indiceEstacaoB = "select * from estacao where estacaoSigla='"+comboBoxEstacaoSDHB.getSelectedItem()+"'";
								rs = conecta.stm.executeQuery(indiceEstacaoB);
								
								while(rs.next()){
									indexEstacaoB = rs.getInt("idEstacao");
								}
								
							}catch(SQLException e){
								JOptionPane.showMessageDialog(null, e);
							}
							novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
							novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
							novoCircuito.setObervacoes(textArea.getText());
							novoCircuito.setCidadeA(indiceCidadeA);
							novoCircuito.setEquipamentoPDHA(comboBoxPDHA.getSelectedIndex()+1);
							novoCircuito.setPortaA(textFieldPortaPDHA.getText());
							novoCircuito.setDidA(textFieldDidPDHA.getText());
							novoCircuito.setEquipamentoSDHB(indexSDHB);
							novoCircuito.setSlotB(textFieldSlotSDHB.getText());
							novoCircuito.setPortaB((String)comboBoxPortaSDHB.getSelectedItem());
							novoCircuito.setDidB(textFieldDidSDHB.getText());
							novoCircuito.setStatusA("p");
							novoCircuito.setStatusB("sB");
							novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
							novoCircuito.setCidadeB(indiceCidadeB);
							novoCircuito.setEstacaoB(indexEstacaoB);
							novoCircuito.setEstacaoA(1);
							if(rdbtnAtivo.isSelected()){
								novoCircuito.setStatusCircuito("ativo");
							}else if(rdbtnDesativado.isSelected()){
								novoCircuito.setStatusCircuito("desativado");
							}
							novoCircuito.atualizaCircuito(numeroCircuito, nomeCabeceira);
							textFieldCircuito.setText("");
							textFieldCliente.setText("");
							textArea.setText("");
							textFieldPortaPDHA.setText("");
							textFieldDidPDHA.setText("");		
							buttonSalvar.setEnabled(true);
						    buttonAtualizar.setEnabled(false);
										
				
			}//Fim da Lógica de verificação e inserção dos dados para PDH na ponta A e SDH na ponta B
			//Início da Lógica de verificação e inserção de dados para PDH nas duas pontas
			if((chckbxPdh.isSelected())&&(chckbxPdh_1.isSelected())){
					
					try{
						ConectaBanco conecta = new ConectaBanco();
						conecta.conectaBanco();
						
						String indexCidadeA = "select * from cidade where cidadeNome='"+comboBoxCidadeA.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(indexCidadeA);
						while(rs.next()){
							indiceCidadeA = rs.getInt("idcidade");
						}
						
						String indexCidadeB = "select * from cidade where cidadeNome='"+comboBoxCidadeB.getSelectedItem()+"'";
						rs = conecta.stm.executeQuery(indexCidadeB);
						
						while(rs.next()){
							indiceCidadeB = rs.getInt("idcidade");
						}
						novoCircuito.setCliente(textFieldCliente.getText().toUpperCase());
						novoCircuito.setNumeroCircuito(textFieldCircuito.getText());
						novoCircuito.setObervacoes(textArea.getText());
						novoCircuito.setCidadeA(indiceCidadeA);
						novoCircuito.setEquipamentoPDHA(comboBoxPDHA.getSelectedIndex()+1);
						novoCircuito.setPortaA(textFieldPortaPDHA.getText());
						novoCircuito.setDidA(textFieldDidPDHA.getText());
						novoCircuito.setCidadeB(indiceCidadeB);
						novoCircuito.setEquipamentoPDHB(comboBoxPDHB.getSelectedIndex());
						novoCircuito.setPortaB(textFieldPortaPDHB.getText());
						novoCircuito.setDidB(textFieldDidPDHB.getText());
						novoCircuito.setCabeceira((String)comboBoxCabeceira.getSelectedItem());
						novoCircuito.setStatusA("p");
						novoCircuito.setStatusB("pB");
						if(rdbtnAtivo.isSelected()){
							novoCircuito.setStatusCircuito("ativo");
						}else if(rdbtnDesativado.isSelected()){
							novoCircuito.setStatusCircuito("desativado");
						}
						novoCircuito.atualizaCircuito(numeroCircuito, nomeCabeceira);
						textFieldCircuito.setText("");
						textFieldCliente.setText("");
						textArea.setText("");
						textFieldDidPDHA.setText("");
						textFieldDidPDHB.setText("");
						textFieldPortaPDHA.setText("");
						textFieldPortaPDHB.setText("");
						buttonSalvar.setEnabled(true);
					    buttonAtualizar.setEnabled(false);
						
					}catch(SQLException e){
						JOptionPane.showMessageDialog(null, e);
					}						
									
			}//Fim da Lógica de verificação e inserção de dados para PDH nas duas pontas
			
		}
				
		});
		buttonAtualizar.setToolTipText("Atualizar");
		buttonAtualizar.setIcon(new ImageIcon(TelaCadastroCircuito.class.getResource("/imagens/mltech/1248318776 (Copy).png")));
		buttonAtualizar.setBounds(84, 0, 45, 31);
		contentPane.add(buttonAtualizar);
		
		buscaSDH();
		buscaRadio();
		buscaCidade();
		buttonAtualizar.setEnabled(false);
	}
}
