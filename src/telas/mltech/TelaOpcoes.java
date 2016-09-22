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
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenuItem mntmCircuito_1 = new JMenuItem("Circuito");
		mnEditar.add(mntmCircuito_1);
		
		JMenuItem mntmPlacamdulo_1 = new JMenuItem("Placa/M\u00F3dulo");
		mnEditar.add(mntmPlacamdulo_1);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mnSobre.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.setToolTipText("Novo");
		button.setIcon(new ImageIcon(TelaOpcoes.class.getResource("/imagens/mltech/Novo.png")));
		button.setBounds(0, 0, 48, 42);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.setToolTipText("Pesquisar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setIcon(new ImageIcon(TelaOpcoes.class.getResource("/imagens/mltech/Pesquisar.png")));
		button_1.setBounds(42, 0, 48, 42);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("Sair");
		button_2.setIcon(new ImageIcon(TelaOpcoes.class.getResource("/imagens/mltech/Sair.png")));
		button_2.setBounds(90, 0, 48, 42);
		contentPane.add(button_2);
	}
}
