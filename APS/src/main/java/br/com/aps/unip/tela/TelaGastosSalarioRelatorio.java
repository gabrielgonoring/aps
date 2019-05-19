package br.com.aps.unip.tela;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.service.EmpregadoService;
import br.com.aps.unip.util.JPAUtil;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaGastosSalarioRelatorio {

	private JFrame frame;
	private final String colunas[]={"ID","NOME EMPREGADO","DATA DE ENTRADA","FUNÇÃO","SALÁRIO"};
	
	/**
	 * Create the application.
	 */
	public TelaGastosSalarioRelatorio() {
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
		
		List<Empregado> listaEmpregado =  new EmpregadoService(JPAUtil.createEntityManager()).getList();
		
		final DefaultTableModel model = new DefaultTableModel(null,colunas){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int linha, int coluna) {  
                return false;
            }  
        };
		final JTable tbEmpregados = new JTable(model);
		
		//COLOCAR DADOS NA TABELA
		for(Empregado empregado : listaEmpregado) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
			String data = df.format(empregado.getDataEntrada()).toString();
			try {
				model.addRow(new String[]{
							String.valueOf(empregado.getId()),
							empregado.getNome(),
							data,
							empregado.getFuncao().getNome(),
							empregado.getFuncao().getSalario().toString()
						});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "não foi possível carregar as vendas");
				System.exit(0);
			}
		}
		
		tbEmpregados.setBounds(10, 54, 665, 383);
		frame.getContentPane().add(tbEmpregados);
		JScrollPane scroll = new JScrollPane(tbEmpregados);
		scroll.setVisible(true);
		scroll.setBounds(10, 54, 665, 383);
		frame.getContentPane().add(scroll);
		
		JLabel lblTotalGastoCom = new JLabel("Total gasto com salários:");
		lblTotalGastoCom.setBounds(10, 11, 147, 14);
		frame.getContentPane().add(lblTotalGastoCom);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(167, 11, 282, 14);
		frame.getContentPane().add(lblTotal);
		
		Double count=0.0;
		for (int i=0; i<=tbEmpregados.getRowCount()-1;i++) {
		count+=Double.parseDouble(tbEmpregados.getValueAt(i, 4).toString());
		}
		DecimalFormat df = new DecimalFormat("0.##");
		lblTotal.setText(df.format(count));
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
