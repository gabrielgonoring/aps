package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.Empregado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.transaction.Transactional.TxType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaPrincipal {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public TelaPrincipal(Empregado empregado) {
		this.empregado = empregado;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 479, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(10, 36, 89, 23);
		frame.getContentPane().add(btnSair);
		
		JButton btnNewButton = new JButton("Telas de cadastro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaCadastros().getFrame().setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 74, 217, 151);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnTelasDeGerenciamento = new JButton("Telas de Gerenciamento");
		btnTelasDeGerenciamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaGerenciamentos tela = new TelaGerenciamentos();
				tela.getFrame().setVisible(true);
			}
		});
		btnTelasDeGerenciamento.setBounds(237, 74, 217, 151);
		frame.getContentPane().add(btnTelasDeGerenciamento);
		
		JLabel lblEmpregado = new JLabel("Usuário");
		lblEmpregado.setBounds(93, 11, 360, 14);
		frame.getContentPane().add(lblEmpregado);
		
		JLabel lblNewLabel = new JLabel("Empregado:");
		lblNewLabel.setBounds(10, 11, 73, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblEmpregado.setText(empregado.getNome());
		
		JButton btnRealizarCompra = new JButton("Realizar Compra");
		btnRealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CompraTela tela = new CompraTela(empregado);
					tela.getFrame().setVisible(true);
				}catch (ValorInvalidoException e) {
					JOptionPane.showMessageDialog(null,"erro ao abrir a tela de realização de compra, detalhe do erro: "+e.getMessage());
				}
			}
		});
		btnRealizarCompra.setBounds(237, 236, 217, 151);
		frame.getContentPane().add(btnRealizarCompra);
		
		JButton btnRelatrios = new JButton("Relatórios");
		btnRelatrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorios tela = new TelaRelatorios();
				tela.getFrame().setVisible(true);
			}
		});
		btnRelatrios.setBounds(10, 236, 217, 151);
		frame.getContentPane().add(btnRelatrios);
	}
	
	private Empregado empregado;
	
	public JFrame getFrame() {
		return frame;
	}
}
