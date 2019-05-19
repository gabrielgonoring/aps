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
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.ItemCompra;
import br.com.aps.unip.service.CompraService;
import br.com.aps.unip.util.JPAUtil;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaCaixaVendaRelatoriaDetalhe {

	private JFrame frame;
	private final String colunas[]={"NOME","QUANTIDADE","VALOR"};
	
	/**
	 * Create the application.
	 */
	public TelaCaixaVendaRelatoriaDetalhe(Compra compra) {
		setCompra(compra);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CompraService service = new CompraService(JPAUtil.createEntityManager());
		
		List<ItemCompra> listaItens = null;
		try {
			listaItens = service.getListItemByCompra(compra);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao abrir os detalhe, pois ocoreu um erro ao caregar os itens");
			frame.dispose();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 699, 487);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final DefaultTableModel model = new DefaultTableModel(null,colunas){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int linha, int coluna) {  
                return false;
            }  
        };
		JTable tbCompra = new JTable(model);
		
		tbCompra.setBounds(10, 108, 665, 329);
		frame.getContentPane().add(tbCompra);
		JScrollPane scroll = new JScrollPane(tbCompra);
		scroll.setVisible(true);
		scroll.setBounds(10, 108, 665, 329);
		frame.getContentPane().add(scroll);
		
		JLabel newLabel2 = new JLabel("Caixa:");
		newLabel2.setBounds(10, 11, 53, 14);
		frame.getContentPane().add(newLabel2);
		
		JLabel lblCaixa = new JLabel(compra.getCaixa().getNome());
		lblCaixa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCaixa.setBounds(10, 36, 288, 14);
		frame.getContentPane().add(lblCaixa);
		
		JLabel lblEmpregado = new JLabel(compra.getEmpregado().getNome());
		lblEmpregado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmpregado.setBounds(308, 36, 367, 14);
		frame.getContentPane().add(lblEmpregado);
		
		JLabel lblNewLabel = new JLabel("Empregado:");
		lblNewLabel.setBounds(308, 11, 92, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDataDaCompra = new JLabel("Data da Compra:");
		lblDataDaCompra.setBounds(10, 61, 145, 14);
		frame.getContentPane().add(lblDataDaCompra);
		
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
		String data = df.format(compra.getData()).toString();
		JLabel lblData = new JLabel(data);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblData.setBounds(10, 83, 128, 14);
		frame.getContentPane().add(lblData);
		
		JLabel newlabel4 = new JLabel("Forma de Pagamento:");
		newlabel4.setBounds(308, 61, 177, 14);
		frame.getContentPane().add(newlabel4);
		
		JLabel lblFormapagamento = new JLabel(compra.getFormaPagamento().getDescricao());
		lblFormapagamento.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFormapagamento.setBounds(308, 83, 355, 14);
		frame.getContentPane().add(lblFormapagamento);
		
		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(162, 61, 136, 14);
		frame.getContentPane().add(lblValorTotal);
		
		JLabel txtValorTotal=null;
		try {
			txtValorTotal = new JLabel(service.getValorTotal(compra).toString());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Falha ao abrir os detalhe, pois ocoreu um erro o valor total");
			e1.printStackTrace();
			frame.dispose();
		}
		txtValorTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtValorTotal.setBounds(162, 84, 128, 14);
		frame.getContentPane().add(txtValorTotal);
		
		for(ItemCompra item :listaItens) {
			try {
				model.addRow(new String[]{
							item.getProduto().getDescricao(),
							String.valueOf(item.getQuantidade()),
							item.getValor().toString()
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "não foi possível carregar as vendas");
				frame.dispose();
			}
		}
		
	}
	//ATRIBUTOS PARA A TELA
	private Compra compra;
	
	public void setCompra(Compra compra) {
		if(compra==null)
			throw new ValorInvalidoException("A compra não pode ser nula");
		this.compra = compra;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
