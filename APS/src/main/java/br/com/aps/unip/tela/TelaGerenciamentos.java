package br.com.aps.unip.tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.asp.unip.enumerated.TipoAcaoTela;

public class TelaGerenciamentos {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciamentos window = new TelaGerenciamentos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaGerenciamentos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 436);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGerenciarProduto = new JButton("Gerencia Produtos");
		btnGerenciarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoTela tela = new ProdutoTela(TipoAcaoTela.GERENCIAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnGerenciarProduto.setBounds(10, 11, 373, 55);
		frame.getContentPane().add(btnGerenciarProduto);
		
		JButton btnGerenciarEmpregado = new JButton("Gerenciar Empregados");
		btnGerenciarEmpregado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpregadoTela tela = new EmpregadoTela(TipoAcaoTela.GERENCIAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnGerenciarEmpregado.setBounds(10, 143, 373, 55);
		frame.getContentPane().add(btnGerenciarEmpregado);
		
		JButton btnGerenciarFuno = new JButton("Gerenciar Funções de Empregado");
		btnGerenciarFuno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FuncaoEmpregadoTela tela = new FuncaoEmpregadoTela(TipoAcaoTela.GERENCIAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnGerenciarFuno.setBounds(10, 209, 373, 55);
		frame.getContentPane().add(btnGerenciarFuno);
		
		JButton btnGerenciarTipo = new JButton("Gerenciar Tipos de Produto");
		btnGerenciarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TipoProdutoTela tela = new TipoProdutoTela(TipoAcaoTela.GERENCIAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnGerenciarTipo.setBounds(10, 77, 373, 55);
		frame.getContentPane().add(btnGerenciarTipo);
		
		JButton btnGerenciarCaixa = new JButton("Gerenciar Caixas");
		btnGerenciarCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CaixaTela tela = new CaixaTela(TipoAcaoTela.GERENCIAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnGerenciarCaixa.setBounds(10, 275, 373, 55);
		frame.getContentPane().add(btnGerenciarCaixa);
		
		JButton btnGerenciarForma = new JButton("Gerenciar Formas de Pagamento");
		btnGerenciarForma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormaPagamentoTela tela = new FormaPagamentoTela(TipoAcaoTela.GERENCIAR);
				tela.getFrame().setVisible(true);
			}
		});
		btnGerenciarForma.setBounds(10, 341, 373, 55);
		frame.getContentPane().add(btnGerenciarForma);
	}
	public JFrame getFrame() {
		return frame;
	}
}
