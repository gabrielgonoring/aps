package br.com.aps.unip.tela;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.FormaPagamento;
import br.com.aps.unip.model.ItemCompra;
import br.com.aps.unip.model.Produto;
import br.com.aps.unip.model.TipoProduto;
import br.com.aps.unip.service.CompraService;
import br.com.aps.unip.util.JPAUtil;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.persistence.Table;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CompraTela {

	private JFrame frame;
	private JFormattedTextField txtProdutoId;
	private JComboBox cmbCaixa;
	private JComboBox cmbFormaPag;
	private JComboBox cmbProduto;
	private JLabel lblValortotal;
	
	
    private final String colunas[]={"ID","DESCIÇÃO","VALOR","QUANTIDADE"};

    private JTable tbItem;

    /**
	 * Create the application.
	 */
	public CompraTela(Empregado empregado) {
		setEmpregado(empregado);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final CompraService service = new CompraService(JPAUtil.createEntityManager());
		
		final List<FormaPagamento> listaFormaPag = service.getListFormaPagamento();
		final List<Caixa> listaCaixa = service.getListCaixa();
		final List<Produto> listProduto = service.getListProduto();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 491);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cmbFormaPag = new JComboBox();
		cmbFormaPag.setBounds(281, 34, 357, 20);
		setDadosCmbFormaPag(listaFormaPag);
		frame.getContentPane().add(cmbFormaPag);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento:");
		lblFormaDePagamento.setBounds(281, 9, 156, 14);
		frame.getContentPane().add(lblFormaDePagamento);
		
		cmbCaixa = new JComboBox();
		cmbCaixa.setBounds(10, 34, 261, 20);
		setDadosCmbCaixa(listaCaixa);
		frame.getContentPane().add(cmbCaixa);
		
		JLabel lblCaixa = new JLabel("Caixa:");
		lblCaixa.setBounds(10, 11, 62, 14);
		frame.getContentPane().add(lblCaixa);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 65, 628, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblProdutos = new JLabel("Produto por nome:");
		lblProdutos.setBounds(10, 78, 122, 14);
		frame.getContentPane().add(lblProdutos);
		
		cmbProduto = new JComboBox();
		cmbProduto.setBounds(10, 103, 427, 20);
		setDadosCmbProduto(listProduto);
		frame.getContentPane().add(cmbProduto);
		
		JLabel newLable = new JLabel("Valor total:");
		newLable.setFont(new Font("Tahoma", Font.BOLD, 12));
		newLable.setBounds(330, 422, 78, 14);
		frame.getContentPane().add(newLable);
		
		lblValortotal = new JLabel("0.00");
		lblValortotal.setBounds(418, 422, 90, 14);
		frame.getContentPane().add(lblValortotal);
		
		
		txtProdutoId = new JFormattedTextField("");
		txtProdutoId.setBounds(447, 103, 90, 20);
		//ACEITAR APENAS NÚMEROS
		txtProdutoId.addKeyListener(new KeyListener() {
			
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
		frame.getContentPane().add(txtProdutoId);
		
		JLabel lblProdutoPorId = new JLabel("Produto por id:");
		lblProdutoPorId.setBounds(446, 78, 90, 14);
		frame.getContentPane().add(lblProdutoPorId);
		
		final DefaultTableModel model = new DefaultTableModel(null,colunas){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int linha, int coluna) {  
                return false;
            }  
        };
		tbItem = new JTable(model);
		tbItem.setBounds(10, 132, 628, 309);
		frame.getContentPane().add(tbItem);
		JScrollPane scroll = new JScrollPane(tbItem);
		scroll.setVisible(true);
		scroll.setBounds(10, 134, 628, 273);
		frame.getContentPane().add(scroll);
		
		JButton btnApagarItem = new JButton("apagar item");
		btnApagarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tbItem.getModel();
				try{
					int index = tbItem.getSelectedRow();
					if(index<0)
						throw new RuntimeException("nenhum item selecionado");
					model.removeRow(index);
					setTotalLblValorTotal();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Erro ao apagar o item da compra detalhe do erro "+e.getMessage());
				}
			}
		});
		btnApagarItem.setBounds(10, 418, 122, 23);
		frame.getContentPane().add(btnApagarItem);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto p = listProduto.get(cmbProduto.getSelectedIndex());
				String qtd = JOptionPane.showInputDialog("Digite a quantidade desejada");
				try {
					checkItemExiste(p);
					if(qtd!=null) {
						checkQuantidadeProdutoValida(Integer.parseInt(qtd), p);
						
						model.addRow(new String[]{String.valueOf(p.getId()),p.getDescricao(),p.getValor().toString(),qtd});
						
						setTotalLblValorTotal();
					}
				}
				catch (ValorInvalidoException e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
				catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null,"Insira apenas números na quantidade");
				}
			}
		});
		btnAdicionar.setBounds(142, 74, 89, 23);
		frame.getContentPane().add(btnAdicionar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Compra compra = new Compra();
					compra.setCaixa(listaCaixa.get(cmbCaixa.getSelectedIndex()));
					compra.setEmpregado(empregado);
					compra.setFormaPagamento(listaFormaPag.get(cmbFormaPag.getSelectedIndex()));
					compra.setData(new Date());
					
					List<ItemCompra> listaItens = new ArrayList<ItemCompra>();
					for(int i=0; i < tbItem.getRowCount(); i++) {
						ItemCompra item = new ItemCompra();
						
						item.setCompra(compra);
						item.setQuantidade(Integer.valueOf((String) tbItem.getValueAt(i, 3)));
						item.setValor(new BigDecimal((String)tbItem.getValueAt(i, 2)));
						item.setProduto(new Produto(Integer.valueOf((String) tbItem.getValueAt(i, 0))));
						
						listaItens.add(item);
						
					}
					
					if(service.save(compra, listaItens)) {
						JOptionPane.showMessageDialog(null,"Compra salva com sucesso");
						frame.dispose();
					}
					
				}catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Erro ao salvar a compra, detalhe do erro:\n"+e1.getMessage());
				}
			}
		});
		btnSalvar.setBounds(549, 418, 89, 23);
		frame.getContentPane().add(btnSalvar);
       
		JButton btnAdicionar2 = new JButton("Adicionar");
		btnAdicionar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkCompoVazio();
					Produto p = service.findProdutoByid(Integer.valueOf(txtProdutoId.getText().toString()));
					String qtd = JOptionPane.showInputDialog("Digite a quantidade desejada");
					try {
						checkItemExiste(p);
						
						if(qtd!=null) {
							checkQuantidadeProdutoValida(Integer.parseInt(qtd), p);
							
							model.addRow(new String[]{String.valueOf(p.getId()),p.getDescricao(),p.getValor().toString(),qtd});
							
							setTotalLblValorTotal();
						}
					}
					catch (ValorInvalidoException e1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,"Insira apenas números na quantidade");
					}
				}catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n"+e1.getMessage());
				}
			}
		});
		btnAdicionar2.setBounds(549, 102, 89, 23);
		frame.getContentPane().add(btnAdicionar2);
		
	}
	
	//ATRIBUTO IMPORTANTE PARA O FUNCIONAMENTO DA TELA
	private Empregado empregado;
	
	public void setEmpregado(Empregado empregado) {
		if(empregado==null)
			throw new ValorInvalidoException("o empregado não pode ser nulo");
		this.empregado = empregado;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbCaixa(List<Caixa> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbCaixa.setModel(comboBoxModel);
	}
	@SuppressWarnings("unchecked")
	private void setDadosCmbFormaPag(List<FormaPagamento> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getDescricao();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbFormaPag.setModel(comboBoxModel);
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbProduto(List<Produto> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getDescricao();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbProduto.setModel(comboBoxModel);
	}
	
	private void checkCompoVazio() {
		if(txtProdutoId.getText().toString().length()<=0)
			throw new ValorInvalidoException("Informe um valor para o id do produto");
	} 
	
	private void checkQuantidadeProdutoValida(Integer quantidade, Produto p) {
		if(quantidade>p.getQuantidade())
			throw new ValorInvalidoException("Não é possível selecionar a quantidade desejada, pois ela excede a quantidade disponível no estoque");
	}
	
	private void checkItemExiste(Produto p) {
		for (int i=0; i<=tbItem.getRowCount()-1;i++) {
			Integer id = Integer.valueOf(tbItem.getValueAt(i, 0).toString());
			if(id==p.getId())
				throw new ValorInvalidoException("O produto já foi inserido na compra");
		}
	}
	
	
	private void setTotalLblValorTotal() {
		Double count=0.0;
		for (int i=0; i<=tbItem.getRowCount()-1;i++) {
		count+=Double.parseDouble(tbItem.getValueAt(i, 2).toString())*Double.parseDouble(tbItem.getValueAt(i, 3).toString());
		}
		lblValortotal.setText(count.toString());
	}
	
	private boolean confirmarAcao(String titulo, String mensagem) {
		int opcion = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);

		if (opcion == 0) { //The ISSUE is here
		   System.out.print("si");
		   return true;
		} else {
		   System.out.print("no");
		   return false;
		}
	}
}
