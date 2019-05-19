package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRelatorios {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public TelaRelatorios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 333);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnVendas = new JButton("Vendas nos caixas");
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOpenVendaRelatorio tela = new TelaOpenVendaRelatorio();
				tela.getFrame().setVisible(true);
			}
		});
		btnVendas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVendas.setBounds(10, 11, 433, 47);
		frame.getContentPane().add(btnVendas);
		
		JButton btnEmpregados = new JButton("Gastos com salários");
		btnEmpregados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaGastosSalarioRelatorio tela = new TelaGastosSalarioRelatorio();
				tela.getFrame().setVisible(true);
			}
		});
		btnEmpregados.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEmpregados.setBounds(10, 69, 433, 47);
		frame.getContentPane().add(btnEmpregados);
		
		JButton btnProdutosCadastrados = new JButton("Produtos Cadastrados");
		btnProdutosCadastrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaProdutosCadastradosRelatorio tela = new TelaProdutosCadastradosRelatorio();
				tela.getFrame().setVisible(true);
			}
		});
		btnProdutosCadastrados.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnProdutosCadastrados.setBounds(10, 127, 433, 47);
		frame.getContentPane().add(btnProdutosCadastrados);
	}
	public JFrame getFrame() {
		return frame;
	}
}
