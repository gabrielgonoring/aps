package br.com.aps.unip.tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.aps.unip.model.FuncaoEmpregado;
import br.com.aps.unip.model.TipoProduto;
import br.com.aps.unip.service.TipoProdutoService;
import br.com.aps.unip.util.JPAUtil;
import br.com.asp.unip.enumerated.TipoAcaoTela;

public class TipoProdutoTela {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JButton btnApagar;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnBuscar;
	private JComboBox cmbTipoExists ;

	/**
	 * Create the application.
	 */
	public TipoProdutoTela(TipoAcaoTela acaoTela) {
		this.acaoTela = acaoTela;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final TipoProdutoService service =new TipoProdutoService(JPAUtil.createEntityManager());
		
		final List<TipoProduto> listaTipoExists;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 245);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cmbTipoExists = new JComboBox();
		cmbTipoExists.setBounds(10, 11, 317, 20);
		listaTipoExists = service.getList();
		setDadosCmbTipoExists(listaTipoExists);
		frame.getContentPane().add(cmbTipoExists);
		
		
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 42, 117, 14);
		frame.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 67, 257, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 123, 414, 20);
		frame.getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(10, 98, 165, 14);
		frame.getContentPane().add(lblDescrio);
		
////////SALVAR/////////////////////////////////////////
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo salvar o tipo de produto?")) {
						tipoProduto = new TipoProduto();
						tipoProduto.setDescricao(txtDescricao.getText().toString());
						tipoProduto.setNome(txtNome.getText().toString());
						tipoProduto.setAtivo(true);
						if(service.save(tipoProduto)) {
							JOptionPane.showMessageDialog(null, "O tipo do produto foi salvo");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "O tipo de produto não foi salvo, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnSalvar.setBounds(335, 166, 89, 23);
		frame.getContentPane().add(btnSalvar);
/////////////////////////////////////////////////////////////////////
		
////////ALTERAR////////////////////////////////////////////////
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo alterar o tipo do produto?")) {
						tipoProduto.setDescricao(txtDescricao.getText().toString());
						tipoProduto.setNome(txtNome.getText().toString());
						if(service.update(tipoProduto)) {
							JOptionPane.showMessageDialog(null, "O tipo do produto atualizado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "O tipo do produto não atualizado, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnAlterar.setBounds(171, 166, 89, 23);
		btnAlterar.setVisible(false);
		frame.getContentPane().add(btnAlterar);
////////////////////////////////////////////////////////////////////////
		
////////APAGAR///////////////////////////////////////////////
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo apagar o tipo do produto?")) {
						if(service.delete(tipoProduto)) {
							JOptionPane.showMessageDialog(null, "O tipo do produto deletado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao apagar o tipo do produto, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnApagar.setBounds(10, 166, 89, 23);
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
					tipoProduto = listaTipoExists.get(cmbTipoExists.getSelectedIndex());
					txtNome.setText(tipoProduto.getNome());
					txtDescricao.setText(tipoProduto.getDescricao());
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnBuscar.setBounds(337, 10, 89, 23);
		frame.getContentPane().add(btnBuscar);
/////////////////////////////////////////////////////
		if (acaoTela==null||acaoTela==TipoAcaoTela.SALVAR) {
			cmbTipoExists.setVisible(false);
			btnBuscar.setVisible(false);
		}
		else {
			btnSalvar.setVisible(false);
		}
		
	}

	//ATRIBUTOS IMPOTANTES PARA O CÓDIGO
	private TipoProduto tipoProduto;
	private TipoAcaoTela acaoTela;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbTipoExists(List<TipoProduto> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbTipoExists.setModel(comboBoxModel);
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
