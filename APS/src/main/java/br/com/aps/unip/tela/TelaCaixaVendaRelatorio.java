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

import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.service.CompraService;
import br.com.aps.unip.util.JPAUtil;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCaixaVendaRelatorio {

	private JFrame frame;
	private final String colunas[]={"CAIXA","EMPREGADO","DATA","FORMA DE PAGAMENTO","VALOR TOTAL"};
	
	public TelaCaixaVendaRelatorio(Caixa caixa){
		
		try {
			listaCompra = service.getListCompraByCaixa(caixa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		initialize();
	}
	
	public TelaCaixaVendaRelatorio() {
		listaCompra = service.getList();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 487);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		final DefaultTableModel model = new DefaultTableModel(null,colunas){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int linha, int coluna) {  
                return false;
            }  
        };
		final JTable tbCompra = new JTable(model);
		
		tbCompra.setBounds(10, 54, 665, 383);
		frame.getContentPane().add(tbCompra);
		JScrollPane scroll = new JScrollPane(tbCompra);
		scroll.setVisible(true);
		scroll.setBounds(10, 54, 665, 383);
		frame.getContentPane().add(scroll);
		
		JButton btnAbrirSelecionado = new JButton("abrir selecionado");
		btnAbrirSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int index = tbCompra.getSelectedRow();
					if(index<0) 
						throw new RuntimeException("nenhuma compra selecionada");
					Compra c = listaCompra.get(index);
					TelaCaixaVendaRelatoriaDetalhe tela = new TelaCaixaVendaRelatoriaDetalhe(c);
					tela.getFrame().setVisible(true);
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnAbrirSelecionado.setBounds(513, 20, 150, 23);
		frame.getContentPane().add(btnAbrirSelecionado);
		
		for(Compra compra :listaCompra) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
			String data = df.format(compra.getData()).toString();
			try {
				model.addRow(new String[]{
							compra.getCaixa().getNome(),
							compra.getEmpregado().getNome(),
							data,
							compra.getFormaPagamento().getDescricao(),
							service.getValorTotal(compra).toString()
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "não foi possível carregar as vendas");
				System.exit(0);
			}
		}
		
	}
	//ATRIBUTOS IMPORTANTES PARA A TELA
	private List<Compra> listaCompra;
	private CompraService service = new CompraService(JPAUtil.createEntityManager());

	public JFrame getFrame() {
		return frame;
	}
	
}
