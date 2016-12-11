package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;

public class TelaOpcoes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOpcoes frame = new TelaOpcoes();
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
	public TelaOpcoes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaOpcoes.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 429);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenu mnNovo = new JMenu("Novo");
		mnArquivo.add(mnNovo);
		
		JMenuItem mntmCircuito = new JMenuItem("Circuito");
		mntmCircuito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCircuito novoCircuito = new TelaCadastroCircuito();
				novoCircuito.setVisible(true);
			}
		});
		mnNovo.add(mntmCircuito);
		JMenuItem mntmPlacamdulo = new JMenuItem("Placa/M\u00F3dulo");
		mntmPlacamdulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				TelaPlaca novaPlaca = new TelaPlaca();
				novaPlaca.setVisible(true);
			}
		});
		mnNovo.add(mntmPlacamdulo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnEditar = new JMenu("Pesquisar");
		menuBar.add(mnEditar);
		
		JMenuItem mntmCircuito_1 = new JMenuItem("Circuito");
		mntmCircuito_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOpcaoCircuito novoCircuito = new TelaOpcaoCircuito();
				novoCircuito.setVisible(true);
			}
		});
		mnEditar.add(mntmCircuito_1);
		
		JMenuItem mntmPlacamdulo_1 = new JMenuItem("Placa/M\u00F3dulo");
		mntmPlacamdulo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPlaca alteraPlaca = new TelaPlaca();
				alteraPlaca.setVisible(true);
			}
		});
		mnEditar.add(mntmPlacamdulo_1);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSobre novoSobre = new TelaSobre();
				novoSobre.setVisible(true);
			}
		});
		mnSobre.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSccpSistema = new JLabel("SCCM - Sistema de Controle de Circuitos e M\u00F3dulos");
		lblSccpSistema.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSccpSistema.setBounds(77, 155, 451, 34);
		contentPane.add(lblSccpSistema);
	}
}
