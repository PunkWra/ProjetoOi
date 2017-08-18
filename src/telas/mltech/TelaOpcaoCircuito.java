package telas.mltech;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;

public class TelaOpcaoCircuito extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnCircuitosPorEstao;
	private JRadioButton rdbtnPorNmeroDe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOpcaoCircuito frame = new TelaOpcaoCircuito();
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
	public TelaOpcaoCircuito() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaOpcaoCircuito.class.getResource("/imagens/mltech/thCGK4S3UD.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnCircuitosPorEstao = new JRadioButton("Circuitos Por Esta\u00E7\u00E3o");
		rdbtnCircuitosPorEstao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnCircuitosPorEstao.isSelected()){
					rdbtnPorNmeroDe.setSelected(false);
				}
			}
		});
		rdbtnCircuitosPorEstao.setBounds(40, 42, 168, 25);
		contentPane.add(rdbtnCircuitosPorEstao);
		
		rdbtnPorNmeroDe = new JRadioButton("Por N\u00FAmero de Circuito");
		rdbtnPorNmeroDe.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnPorNmeroDe.isSelected()){
					rdbtnCircuitosPorEstao.setSelected(false);
				}
			}
		});
		rdbtnPorNmeroDe.setBounds(40, 83, 165, 25);
		contentPane.add(rdbtnPorNmeroDe);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TelaOpcaoCircuito.class.getResource("/imagens/mltech/Cancelar (Copy).png")));
		btnSair.setBounds(291, 154, 97, 25);
		contentPane.add(btnSair);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Chama a janela para mostrar circuitos por estação e equipamento
				if(rdbtnCircuitosPorEstao.isSelected()){
					TelaMostraCircuito novaTela = new TelaMostraCircuito();
					novaTela.setVisible(true);
				}else if(rdbtnPorNmeroDe.isSelected()){
					TelaCadastroCircuito novaTela = new TelaCadastroCircuito();
					novaTela.setVisible(true);
				}
				
			}
		});
		btnOk.setIcon(new ImageIcon(TelaOpcaoCircuito.class.getResource("/imagens/mltech/Aceitar (Copy).png")));
		btnOk.setBounds(182, 154, 97, 25);
		contentPane.add(btnOk);
	}
}
