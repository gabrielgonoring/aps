package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;

import br.com.asp.unip.enumerated.TipoAcaoTela;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastros {

	private JFrame frame;

	
	public TelaCadastros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 376, 428);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCadastroProduto = new JButton("Cadastro de Produto");
		btnCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoTela tela = new ProdutoTela(TipoAcaoTela.SALVAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnCadastroProduto.setBounds(10, 11, 351, 54);
		frame.getContentPane().add(btnCadastroProduto);
		
		JButton btnCadastroDeEmpregado = new JButton("Cadastro de Empregado");
		btnCadastroDeEmpregado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpregadoTela tela = new EmpregadoTela(TipoAcaoTela.SALVAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnCadastroDeEmpregado.setBounds(10, 141, 351, 54);
		frame.getContentPane().add(btnCadastroDeEmpregado);
		
		JButton btnCadastroDeFuno = new JButton("Cadastro de Função de Empregado");
		btnCadastroDeFuno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FuncaoEmpregadoTela tela = new FuncaoEmpregadoTela(TipoAcaoTela.SALVAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnCadastroDeFuno.setBounds(10, 206, 351, 54);
		frame.getContentPane().add(btnCadastroDeFuno);
		
		JButton btnCadastroDeTipo = new JButton("Cadastro de Tipo de Produto");
		btnCadastroDeTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TipoProdutoTela tela = new TipoProdutoTela(TipoAcaoTela.SALVAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnCadastroDeTipo.setBounds(10, 76, 351, 54);
		frame.getContentPane().add(btnCadastroDeTipo);
		
		JButton btnCadastroDeCaixa = new JButton("Cadastro de Caixa");
		btnCadastroDeCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CaixaTela tela = new CaixaTela(TipoAcaoTela.SALVAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnCadastroDeCaixa.setBounds(10, 271, 351, 54);
		frame.getContentPane().add(btnCadastroDeCaixa);
		
		JButton btnCadastroDeForma = new JButton("Cadastro de Forma de Pagamento");
		btnCadastroDeForma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormaPagamentoTela tela = new FormaPagamentoTela(TipoAcaoTela.SALVAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnCadastroDeForma.setBounds(10, 336, 351, 54);
		frame.getContentPane().add(btnCadastroDeForma);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
