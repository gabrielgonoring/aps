package br.com.aps.unip.tela;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.Produto;
import br.com.aps.unip.service.ProdutoService;
import br.com.aps.unip.util.JPAUtil;

public class TelaProdutosCadastradosRelatorio {

	private JFrame frame;
	private final String colunas[]={"ID","NOME","TIPO","DESCRIÇÃO TIPO", "QUANTIDADE","VALOR"};

	/**
	 * Create the application.
	 */
	public TelaProdutosCadastradosRelatorio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 485);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		List<Produto> listaProduto = new ProdutoService(JPAUtil.createEntityManager()).getList();
		
		final DefaultTableModel model = new DefaultTableModel(null,colunas){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int linha, int coluna) {  
                return false;
            }  
        };
		final JTable tbEmpregados = new JTable(model);
		tbEmpregados.setBounds(10, 54, 665, 383);
		frame.getContentPane().add(tbEmpregados);
		JScrollPane scroll = new JScrollPane(tbEmpregados);
		scroll.setVisible(true);
		scroll.setBounds(10, 11, 665, 426);
		frame.getContentPane().add(scroll);
		
		//COLOCAR DADOS NA TABELA
		for(Produto produto : listaProduto) {
			try {
				model.addRow(new String[]{
							String.valueOf(produto.getId()),
							produto.getDescricao(),
							produto.getTipoProduto().getNome(),
							produto.getTipoProduto().getDescricao(),
							String.valueOf(produto.getQuantidade()),
							produto.getValor().toString()
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "não foi possível carregar as vendas");
				System.exit(0);
			}
		}
	}
	public JFrame getFrame() {
		return frame;
	}
}
