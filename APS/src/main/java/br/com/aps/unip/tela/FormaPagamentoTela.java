package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.aps.unip.model.FormaPagamento;
import br.com.aps.unip.service.FormaPagamentoService;
import br.com.aps.unip.util.JPAUtil;
import br.com.asp.unip.enumerated.TipoAcaoTela;

import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FormaPagamentoTela {

	private JFrame frame;
	private JTextField txtDescricao;
	private JComboBox cmbFormPagExists;

	/**
	 * Create the application.
	 */
	public FormaPagamentoTela(TipoAcaoTela acaoTela) {
		this.acaoTela=acaoTela;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final FormaPagamentoService service = new FormaPagamentoService(JPAUtil.createEntityManager());
		
		final List<FormaPagamento> listaFormaPagamentoExist = service.getList();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 343, 221);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(33, 57, 114, 14);
		panel.add(lblDescricao);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(33, 84, 248, 20);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
////////SALVAR FORMA DE PAGAMENTO///////////////////		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo salvar a forma de pagamento?")) {
						if(service.save(new FormaPagamento(txtDescricao.getText().toString(),true))) {
							JOptionPane.showMessageDialog(null, "Forma de pagamento foi salva");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Forma de pagamento não foi salva, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnSalvar.setBounds(228, 148, 89, 23);
		panel.add(btnSalvar);
///////////////////////////////////////////////////
		
		cmbFormPagExists = new JComboBox();
		cmbFormPagExists.setBounds(31, 11, 144, 20);
		panel.add(cmbFormPagExists);
		setDadosCmbFormPagExists(listaFormaPagamentoExist);
		
////////BUSCAR ELEMENTO///////////////////////
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int index = cmbFormPagExists.getSelectedIndex();
					formaPagamento = listaFormaPagamentoExist.get(index);
					txtDescricao.setText(formaPagamento.getDescricao());
					btnAlterar.setVisible(true);
					btnApagar.setVisible(true);
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n\n"+e.getMessage());
					txtDescricao.setText("");
				}
			}
		});
		
		btnBuscar.setBounds(192, 10, 89, 23);
		panel.add(btnBuscar);
///////////////////////////////////////////////

////////ALTERAR FORMA DE PAGAMENTO///////////////
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, listFormaPagamentoExist.get(index).getDescricao());
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo alterar a forma de pagamento?")) {
						formaPagamento.setDescricao(txtDescricao.getText().toString());
						if(service.update(formaPagamento)) {
							JOptionPane.showMessageDialog(null, "Forma de pagamento atualizada");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Forma de pagamento não atualizada, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnAlterar.setBounds(118, 148, 89, 23);
		btnAlterar.setVisible(false);
		panel.add(btnAlterar);
/////////////////////////////////////////////////
		
		
////////APAGAR FORMA DE PAGAMENTO/////////////////		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo apagar a forma de pagamento?")) {
						if(service.delete(formaPagamento)) {
							JOptionPane.showMessageDialog(null, "Forma de pagamento deletada");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao apagar a forma de pagamento, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnApagar.setBounds(10, 148, 89, 23);
		btnApagar.setVisible(false);
		panel.add(btnApagar);
///////////////////////////////////////////////////
		
		if (acaoTela==null||acaoTela==TipoAcaoTela.SALVAR) {
			cmbFormPagExists.setVisible(false);
			btnBuscar.setVisible(false);
		}
		else {
			btnSalvar.setVisible(false);
		}
	
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbFormPagExists(List<FormaPagamento> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getDescricao();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbFormPagExists.setModel(comboBoxModel);
	}
	
	/**
	 * Esse método mostra na tela do usuário uma caixa de confirmação se ele quer ou não continuar
	 * */
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
	
	//ATRIBUTOS PARA ULTILIZAÇÃO NA CLASSE
	private FormaPagamento formaPagamento;
	private TipoAcaoTela acaoTela;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	private JButton btnBuscar;
	private JButton btnAlterar;
	private JButton btnApagar;
}
