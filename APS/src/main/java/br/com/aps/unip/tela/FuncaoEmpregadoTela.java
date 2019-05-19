package br.com.aps.unip.tela;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.FormaPagamento;
import br.com.aps.unip.model.FuncaoEmpregado;
import br.com.aps.unip.service.FuncaoEmpregadoService;
import br.com.aps.unip.util.JPAUtil;
import br.com.asp.unip.enumerated.TipoAcaoTela;

import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class FuncaoEmpregadoTela {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JFormattedTextField txtSalario;
	private JComboBox cmbFuncaoExists;
	private JButton btnApagar;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnBuscar;

	/**
	 * Create the application.
	 */
	public FuncaoEmpregadoTela(TipoAcaoTela acaoTela) {
		this.acaoTela =acaoTela;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final FuncaoEmpregadoService service = new FuncaoEmpregadoService(JPAUtil.createEntityManager());
		

		final List<FuncaoEmpregado> listaFuncoesExistentes;
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 329);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 73, 73, 14);
		frame.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 98, 414, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(10, 129, 97, 14);
		frame.getContentPane().add(lblDescrio);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 154, 414, 20);
		frame.getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblSalrio = new JLabel("Salário:");
		lblSalrio.setBounds(10, 185, 79, 14);
		frame.getContentPane().add(lblSalrio);
		
		
		txtSalario = new JFormattedTextField();
		txtSalario.setBounds(10, 210, 86, 20);
		frame.getContentPane().add(txtSalario);
		txtSalario.setColumns(10);
		MaskFormatter maskSalario;
		try {
			maskSalario = new MaskFormatter("#####.##");
			maskSalario.install(txtSalario);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
////////SALVAR/////////////////////////////////////////
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo salvar a função?")) {
						checkSalario();
						funcaoEmpregado = new FuncaoEmpregado();
						funcaoEmpregado.setDescricao(txtDescricao.getText().toString());
						funcaoEmpregado.setNome(txtNome.getText().toString());
						funcaoEmpregado.setSalario(new BigDecimal(txtSalario.getText().toString().replace(" ", "0")));
						funcaoEmpregado.setAtivo(true);
						if(service.save(funcaoEmpregado)) {
							JOptionPane.showMessageDialog(null, "Função foi salva");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Função não foi salva, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnSalvar.setBounds(335, 256, 89, 23);
		frame.getContentPane().add(btnSalvar);
/////////////////////////////////////////////////////////////////////
		
////////ALTERAR////////////////////////////////////////////////
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo alterar a função?")) {
						checkSalario();
						funcaoEmpregado.setDescricao(txtDescricao.getText().toString());
						funcaoEmpregado.setNome(txtNome.getText().toString());
						funcaoEmpregado.setSalario(new BigDecimal(txtSalario.getText().toString().replace(" ", "0")));
						if(service.update(funcaoEmpregado)) {
							JOptionPane.showMessageDialog(null, "Função atualizada");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Função não atualizada, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnAlterar.setBounds(174, 256, 89, 23);
		btnAlterar.setVisible(false);
		frame.getContentPane().add(btnAlterar);
////////////////////////////////////////////////////////////////////////
		
////////APAGAR///////////////////////////////////////////////
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo apagar a função?")) {
						if(service.delete(funcaoEmpregado)) {
							JOptionPane.showMessageDialog(null, "Função deletada");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao apagar a função, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnApagar.setBounds(18, 256, 89, 23);
		btnApagar.setVisible(false);
		frame.getContentPane().add(btnApagar);
/////////////////////////////////////////////////////////////////	
		
		
		cmbFuncaoExists = new JComboBox();
		cmbFuncaoExists.setBounds(10, 22, 414, 20);
		listaFuncoesExistentes = service.getList();
		setDadosCmbFuncaoExists(listaFuncoesExistentes);
		frame.getContentPane().add(cmbFuncaoExists);

		
////////BUSCAR////////////////////////////
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnAlterar.setVisible(true);
					btnApagar.setVisible(true);
					funcaoEmpregado = listaFuncoesExistentes.get(cmbFuncaoExists.getSelectedIndex());
					txtNome.setText(funcaoEmpregado.getNome());
					txtDescricao.setText(funcaoEmpregado.getDescricao());
					//setar valor
					String salario = funcaoEmpregado.getSalario().toString();
					int l = 5 - salario.indexOf(".");
					for(int i = 0; i<l;i++)
						salario="0"+salario;
					txtSalario.setText(salario.replace(".", ""));
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnBuscar.setBounds(335, 53, 89, 23);
		frame.getContentPane().add(btnBuscar);
/////////////////////////////////////////////////////
		
		if (acaoTela==null||acaoTela==TipoAcaoTela.SALVAR) {
			cmbFuncaoExists.setVisible(false);
			btnBuscar.setVisible(false);
		}
		else {
			btnSalvar.setVisible(false);
		}
		
	}
	
	private void checkSalario() {
		// TODO Auto-generated method stub
		if(txtSalario.getText().toString().replace(" ", "").replace(".", "").length()<=0)
			throw new ValorInvalidoException("Informe um valor para o salário");
	}
	
	//ATRIBUTO ULTILIZADOS NA TELA
	private FuncaoEmpregado funcaoEmpregado;
	private TipoAcaoTela acaoTela;
	
	public JFrame getFrame() {
		return this.frame;
	}

	@SuppressWarnings("unchecked")
	private void setDadosCmbFuncaoExists(List<FuncaoEmpregado> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbFuncaoExists.setModel(comboBoxModel);
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
