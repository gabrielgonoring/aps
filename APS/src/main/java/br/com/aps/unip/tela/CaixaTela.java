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
import javax.transaction.Transactional.TxType;

import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.FuncaoEmpregado;
import br.com.aps.unip.service.CaixaService;
import br.com.aps.unip.util.JPAUtil;
import br.com.asp.unip.enumerated.TipoAcaoTela;

public class CaixaTela {

	private JFrame frame;
	private JTextField txtNome;
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnApagar;
	private JButton btnBuscar;
	private JComboBox cmbCaixasExists;


	/**
	 * Create the application.
	 */
	public CaixaTela(TipoAcaoTela acaoTela) {
		this.acaoTela=acaoTela;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final CaixaService service = new CaixaService(JPAUtil.createEntityManager());
		

		final List<Caixa> listaCaixaExists;
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 170);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cmbCaixasExists = new JComboBox();
		cmbCaixasExists.setBounds(10, 11, 315, 20);
		listaCaixaExists = service.getList();
		setDadosCmbCaixaExists(listaCaixaExists);
		frame.getContentPane().add(cmbCaixasExists);
		
		JLabel lblNomeDoCaixa = new JLabel("Nome do caixa:");
		lblNomeDoCaixa.setBounds(10, 42, 168, 14);
		frame.getContentPane().add(lblNomeDoCaixa);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 67, 315, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
////////SALVAR/////////////////////////////////////////
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo salvar o caixa?")) {
						caixa = new Caixa();
						caixa.setNome(txtNome.getText().toString());
						caixa.setAtivo(true);
						if(service.save(caixa)) {
							JOptionPane.showMessageDialog(null, "Caixa foi salvo");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Caixa não foi salvo, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnSalvar.setBounds(335, 98, 89, 23);
		frame.getContentPane().add(btnSalvar);
/////////////////////////////////////////////////////////////////////
		
////////ALTERAR////////////////////////////////////////////////
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo alterar o caixa?")) {
						caixa.setNome(txtNome.getText().toString());
						if(service.update(caixa)) {
							JOptionPane.showMessageDialog(null, "Caixa atualizado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Caixa não atualizado, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnAlterar.setBounds(173, 98, 89, 23);
		btnAlterar.setVisible(false);
		frame.getContentPane().add(btnAlterar);
////////////////////////////////////////////////////////////////////////
		
////////APAGAR///////////////////////////////////////////////
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo apagar o caixa?")) {
						if(service.delete(caixa)) {
							JOptionPane.showMessageDialog(null, "Caixa deletado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao apagar o caixa, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnApagar.setBounds(10, 98, 89, 23);
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
					caixa = listaCaixaExists.get(cmbCaixasExists.getSelectedIndex());
					txtNome.setText(caixa.getNome());
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnBuscar.setBounds(335, 10, 89, 23);
		frame.getContentPane().add(btnBuscar);
/////////////////////////////////////////////////////
		
		if (acaoTela==null||acaoTela==TipoAcaoTela.SALVAR) {
			cmbCaixasExists.setVisible(false);
			btnBuscar.setVisible(false);
		}
		else {
			btnSalvar.setVisible(false);
		}
		
	}
	
	//ATIBUTOS IMPORTANTES PARA A TELA
	private Caixa caixa;
	private TipoAcaoTela acaoTela;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbCaixaExists(List<Caixa> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbCaixasExists.setModel(comboBoxModel);
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
