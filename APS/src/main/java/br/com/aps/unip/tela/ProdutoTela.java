package br.com.aps.unip.tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.Produto;
import br.com.aps.unip.model.TipoProduto;
import br.com.aps.unip.service.ProdutoService;
import br.com.aps.unip.util.JPAUtil;
import br.com.asp.unip.enumerated.TipoAcaoTela;

public class ProdutoTela {

	private JFrame frame;
	private JTextField txtDescricao;
	private JTextField txtQuantidade;
	private JFormattedTextField txtValor;
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnApagar;
	private JButton btnBuscar;
	private JComboBox cmbProdutoExists;
	private JComboBox cmbTipo;

	/**
	 * Create the application.
	 */
	public ProdutoTela(TipoAcaoTela acaoTela) {
		this.acaoTela = acaoTela;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final ProdutoService service = new ProdutoService(JPAUtil.createEntityManager());
		
		final List<TipoProduto> listaTipoProdutos;
		final List<Produto> listaProdutoExists;
		
		listaProdutoExists = service.getList();
		listaTipoProdutos = service.getListTipoProduto();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 262);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cmbProdutoExists = new JComboBox();
		cmbProdutoExists.setBounds(10, 11, 414, 20);
		setDadosCmbProdutoExists(listaProdutoExists);
		frame.getContentPane().add(cmbProdutoExists);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 76, 130, 14);
		frame.getContentPane().add(lblDescricao);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 101, 414, 20);
		frame.getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblTipoDoProduto = new JLabel("Tipo:");
		lblTipoDoProduto.setBounds(10, 132, 99, 14);
		frame.getContentPane().add(lblTipoDoProduto);
		
		cmbTipo = new JComboBox();
		cmbTipo.setBounds(10, 157, 256, 20);
		setDadosCmbTipo(listaTipoProdutos);
		frame.getContentPane().add(cmbTipo);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(276, 132, 74, 14);
		frame.getContentPane().add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(276, 157, 74, 20);
		txtQuantidade.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (!((e.getKeyChar() >= KeyEvent.VK_0 && 
                        e.getKeyChar() <= KeyEvent.VK_9) || 
                       (e.getKeyChar() == KeyEvent.VK_ENTER || 
                        e.getKeyChar() == KeyEvent.VK_SPACE || 
                        e.getKeyChar() == KeyEvent.VK_BACK_SPACE))) {
                     e.consume();
                 }
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		
		txtValor = new JFormattedTextField();
		txtValor.setBounds(355, 157, 69, 20);
		MaskFormatter maskValor;
		try {
			maskValor = new MaskFormatter("#####.##");
			maskValor.install(txtValor);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame.getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(355, 132, 46, 14);
		frame.getContentPane().add(lblValor);
		
////////SALVAR/////////////////////////////////////////
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo salvar o produto?")) {
						checkCompoVazio();
						produto = new Produto();
						produto.setDescricao(txtDescricao.getText().toString());
						produto.setQuantidade(Integer.valueOf(txtQuantidade.getText().toString()));
						produto.setTipoProduto(listaTipoProdutos.get(getIndexCombobox(cmbTipo, "tipo do produto")));
						produto.setValor(new BigDecimal(txtValor.getText().toString().replace(" ", "0")));
						produto.setAtivo(true);
						if(service.save(produto)) {
							JOptionPane.showMessageDialog(null, "Produto foi salvo");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Produto não foi salvo, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnSalvar.setBounds(335, 188, 89, 23);
		frame.getContentPane().add(btnSalvar);
/////////////////////////////////////////////////////////////////////
		
////////ALTERAR////////////////////////////////////////////////
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo alterar o produto?")) {
						checkCompoVazio();
						produto.setDescricao(txtDescricao.getText().toString());
						produto.setQuantidade(Integer.valueOf(txtQuantidade.getText().toString()));
						produto.setTipoProduto(listaTipoProdutos.get(getIndexCombobox(cmbTipo, "tipo do produto")));
						produto.setValor(new BigDecimal(txtValor.getText().toString().replace(" ", "0")));
						produto.setAtivo(true);
						if(service.update(produto)) {
							JOptionPane.showMessageDialog(null, "Produto atualizado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Produto não atualizado, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnAlterar.setBounds(177, 188, 89, 23);
		btnAlterar.setVisible(false);
		frame.getContentPane().add(btnAlterar);
////////////////////////////////////////////////////////////////////////
		
////////APAGAR///////////////////////////////////////////////
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo apagar o produto?")) {
						if(service.delete(produto)) {
							JOptionPane.showMessageDialog(null, "Produto deletado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao apagar o produto, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnApagar.setBounds(10, 188, 89, 23);
		btnApagar.setVisible(false);
		frame.getContentPane().add(btnApagar);
/////////////////////////////////////////////////////////////////	
		
		
////////BUSCAR////////////////////////////
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnAlterar.setVisible(true);
					btnApagar.setVisible(true);
					
					produto = listaProdutoExists.get(cmbProdutoExists.getSelectedIndex());
					txtDescricao.setText(produto.getDescricao());
					txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
					//setar valor
					String valor = produto.getValor().toString();
					int l = 5 - valor.indexOf(".");
					for(int i = 0; i<l;i++)
						valor="0"+valor;
					txtValor.setText(valor.replace(".", ""));
					cmbTipo.setSelectedIndex(getIndexTipo(listaTipoProdutos,produto.getTipoProduto()));
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnBuscar.setBounds(335, 42, 89, 23);
		frame.getContentPane().add(btnBuscar);
/////////////////////////////////////////////////////
		
		if (acaoTela==null||acaoTela==TipoAcaoTela.SALVAR) {
			cmbProdutoExists.setVisible(false);
			btnBuscar.setVisible(false);
		}
		else {
			btnSalvar.setVisible(false);
		}
		
		
	}
	
	//ATRIBUTOS NECESSÁRIO PARA A TELA
	private Produto produto;
	private TipoAcaoTela acaoTela;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbTipo(List<TipoProduto> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbTipo.setModel(comboBoxModel);
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbProdutoExists(List<Produto> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getDescricao();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbProdutoExists.setModel(comboBoxModel);
	}
	
	private int getIndexTipo(List<TipoProduto> lista, TipoProduto tipoProduto) {
		int index = -1;
		for(int i=0;i<lista.size();i++) {
			if (lista.get(i).getId()==tipoProduto.getId()) {
				//pegar index da função na lista
				index=i;
				//parar for
				i=lista.size();
			}
		}
		return index;
	}
	
	private void checkCompoVazio() {
		if(txtQuantidade.getText().toString().length()<=0)
			throw new ValorInvalidoException("Informe um valor para a quantidade do produto");
		if(txtValor.getText().toString().replace(" ", "").replace(".", "").length()<=0) 
			throw new ValorInvalidoException("Informe um valor para o valor do produto");
	}
	
	private int getIndexCombobox(JComboBox comboBox, String nomeCampo) {
		// TODO Auto-generated method stub
		int index = comboBox.getSelectedIndex();
		if(index<0) {
			throw new ValorInvalidoException("Selecione um valor para o atributo "+nomeCampo);
		}
		return index;
	}
	
	private boolean confirmarAcao(String titulo, String mensagem) {
		int opcion = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);

		if (opcion == 0) { 
		   return true;
		} else {
		   return false;
		}
	}
}
