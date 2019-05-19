package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.FuncaoEmpregado;
import br.com.aps.unip.service.EmpregadoService;
import br.com.aps.unip.util.JPAUtil;
import br.com.asp.unip.enumerated.TipoAcaoTela;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class EmpregadoTela {

	private JFrame frame;
	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtData;
	private JTextField txtEstado;
	private JTextField txtCidade;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtUsuario;
	private JTextField txtSenha;
	private JFormattedTextField txtCEP;
	private JCheckBox chckbxTemSupervisor;
	private JComboBox cmbSupervisor;
	private JComboBox cmbEmpregadosExists;
	private JComboBox cmbFuncao;
	private JButton btnAlterar;
	private JButton btnSalvar ;
	private JButton btnApagar;
	private JButton btnBuscar;

	/**
	 * Create the application.
	 */
	public EmpregadoTela(TipoAcaoTela acaoTela) {
		this.acaoTela = acaoTela;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final EmpregadoService service = new EmpregadoService(JPAUtil.createEntityManager());
		
		//PEGANDO TODOS OS EMPREGADOS ATIVOS PARA LISTAR NO COMBOBOX DE PESQUISA E PARA GERENTES
		final List<Empregado> listaEmpregadoExists = service.getList();
		//PEGANDO TODAS AS LISTAS DE FUNCÕES EXISTENTES
		final List<FuncaoEmpregado> listaFuncoes = service.getListFuncao();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 531, 496);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 64, 495, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 39, 67, 14);
		frame.getContentPane().add(lblNome);
		
		txtCPF = new JFormattedTextField();
		txtCPF.setBounds(10, 121, 140, 20);
		frame.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		MaskFormatter maskCPF;
		try {
			maskCPF = new MaskFormatter("###.###.###-##");
			maskCPF.install(txtCPF);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			
		}
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 96, 46, 14);
		frame.getContentPane().add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(184, 95, 83, 14);
		frame.getContentPane().add(lblTelefone);
		
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setBounds(184, 121, 150, 20);
		frame.getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		MaskFormatter maskTelefone;
		try {
			maskTelefone = new MaskFormatter("(##)#####-####");
			maskTelefone.install(txtTelefone);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			
		}
		
		JLabel lblDataDeEntrada = new JLabel("Data de Entrada:");
		lblDataDeEntrada.setBounds(369, 96, 112, 14);
		frame.getContentPane().add(lblDataDeEntrada);
		
		txtData = new JFormattedTextField();
		txtData.setColumns(10);
		txtData.setBounds(367, 121, 138, 20);
		frame.getContentPane().add(txtData);
		MaskFormatter maskData;
		try {
			maskData = new MaskFormatter("##/##/####");
			maskData.install(txtData);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		txtEstado = new JTextField();
		txtEstado.setBounds(10, 406, 150, 20);
		frame.getContentPane().add(txtEstado);
		txtEstado.setColumns(10);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(318, 350, 187, 20);
		frame.getContentPane().add(txtCidade);
		txtCidade.setColumns(10);
		
		txtRua = new JTextField();
		txtRua.setBounds(10, 292, 390, 20);
		frame.getContentPane().add(txtRua);
		txtRua.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(10, 350, 295, 20);
		frame.getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(410, 292, 95, 20);
		frame.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(10, 177, 140, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(160, 177, 86, 20);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 266, 46, 14);
		frame.getContentPane().add(lblRua);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(410, 266, 71, 14);
		frame.getContentPane().add(lblNumero);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 325, 67, 14);
		frame.getContentPane().add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(318, 325, 46, 14);
		frame.getContentPane().add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(10, 381, 46, 14);
		frame.getContentPane().add(lblEstado);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(170, 381, 46, 14);
		frame.getContentPane().add(lblCep);
		
		JLabel lblUsurio = new JLabel("Usuário:");
		lblUsurio.setBounds(10, 152, 104, 14);
		frame.getContentPane().add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(160, 152, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		cmbFuncao = new JComboBox();
		cmbFuncao.setBounds(256, 177, 249, 20);
		setDadosCmbFuncao(listaFuncoes);
		frame.getContentPane().add(cmbFuncao);
		
		JLabel lblFuno = new JLabel("Função:");
		lblFuno.setBounds(256, 152, 124, 14);
		frame.getContentPane().add(lblFuno);
		
		cmbSupervisor = new JComboBox();
		cmbSupervisor.setBounds(10, 235, 495, 20);
		setDadosCmbgerente(listaEmpregadoExists);
		cmbSupervisor.setEnabled(false);
		cmbSupervisor.setVisible(false);
		frame.getContentPane().add(cmbSupervisor);
		
		JLabel lblGerente = new JLabel("Supervisor:");
		lblGerente.setBounds(10, 210, 140, 14);
		frame.getContentPane().add(lblGerente);
		
		chckbxTemSupervisor = new JCheckBox("Tem supervisor");
		chckbxTemSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxTemSupervisor.isSelected()){
					cmbSupervisor.setEnabled(true);
					cmbSupervisor.setVisible(true);
				}
				else {
					cmbSupervisor.setEnabled(false);
					cmbSupervisor.setVisible(false);
				}
					
			}
		});
		chckbxTemSupervisor.setBounds(401, 205, 104, 23);
		frame.getContentPane().add(chckbxTemSupervisor);
		
		txtCEP = new JFormattedTextField();
		txtCEP.setBounds(170, 406, 86, 20);
		txtCEP.addKeyListener(new KeyListener() {
			
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
		frame.getContentPane().add(txtCEP);
		txtCEP.setColumns(10);
		MaskFormatter maskValor;
		try {
			maskValor = new MaskFormatter("########");
			maskValor.install(txtCEP);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		cmbEmpregadosExists = new JComboBox();
		cmbEmpregadosExists.setBounds(10, 8, 407, 20);
		setDadosCmbEmpregadoExists(listaEmpregadoExists);
		frame.getContentPane().add(cmbEmpregadosExists);
		
///////BUSCAR/////////////////////////////////
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					btnAlterar.setVisible(true);
					btnApagar.setVisible(true);
					
					//SELECIONAR EMPREGADO
					empregado = listaEmpregadoExists.get(cmbEmpregadosExists.getSelectedIndex());
					
					txtNome.setText(empregado.getNome());
					txtCPF.setText(empregado.getCpf());
					txtTelefone.setText(empregado.getTelefone());
					DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
					String dataEntrada = df.format(empregado.getDataEntrada()).toString().replace("", ""); // imprimirá: 17/03/08
					txtData.setText(dataEntrada);
					txtUsuario.setText(empregado.getUsuario());
					txtSenha.setText(empregado.getSenha());
					cmbFuncao.setSelectedIndex(getIndexFuncao(listaFuncoes, empregado.getFuncao()));
					
					//GERENTE 
					if (empregado.getSupervisor().getId()==empregado.getId()) {
						cmbSupervisor.setEnabled(false);
						cmbSupervisor.setVisible(false);
						chckbxTemSupervisor.setSelected(false);
					}else {
						int index = getIndexGerente(listaEmpregadoExists, empregado.getSupervisor());
						cmbSupervisor.setEnabled(true);
						cmbSupervisor.setVisible(true);
						chckbxTemSupervisor.setSelected(true);
						cmbSupervisor.setSelectedIndex(index);
					}
					
					txtRua.setText(empregado.getRua());
					txtNumero.setText(empregado.getNumeroEndereco());
					txtBairro.setText(empregado.getBairro());
					txtCidade.setText(empregado.getCidade());
					txtEstado.setText(empregado.getEstado());
					txtCEP.setText(empregado.getCep());
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar a busca, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnBuscar.setBounds(427, 7, 78, 23);
		frame.getContentPane().add(btnBuscar);
////////////////////////////////////////////////////
		
////////SALVAR////////////////////////////////////
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo salvar o empregado?")) {
						empregado = new Empregado();
						empregado.setNome(txtNome.getText().toString());
						empregado.setCpf(txtCPF.getText().toString().replace(" ", "").replace(".", "").replace("-", ""));
						empregado.setTelefone(txtTelefone.getText().toString().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
						empregado.setDataEntrada(getDateFormated(txtData.getText().toString()));
						empregado.setUsuario(txtUsuario.getText().toString());
						empregado.setSenha(txtSenha.getText().toString());
						
						empregado.setFuncao(listaFuncoes.get(getIndexCombobox(cmbFuncao, "funcão")));
						//GERENTE
						Empregado gerente;
						if(cmbSupervisor.isVisible()) {
							
							gerente = new Empregado();
							gerente=listaEmpregadoExists.get(getIndexCombobox(cmbSupervisor, "supervidor"));
						}
						else 
							gerente = empregado;
						empregado.setSupervisor(gerente);
						empregado.setRua(txtRua.getText().toString());
						empregado.setNumeroEndereco(txtNumero.getText().toString());
						empregado.setBairro(txtBairro.getText().toString());
						empregado.setCidade(txtCidade.getText().toString());
						empregado.setEstado(txtEstado.getText().toString());
						empregado.setCep(txtCEP.getText().toString().trim());
 						empregado.setAtivo(true);
						if(service.save(empregado)) {
							JOptionPane.showMessageDialog(null, "Empregado foi salvo");
							frame.dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
 					JOptionPane.showMessageDialog(null, "Empregado não foi salvo, detalhe do erro:\n\n"+e.getMessage());
				}
			}
		});
		btnSalvar.setBounds(410, 389, 89, 23);
		
		frame.getContentPane().add(btnSalvar);
///////////////////////////////////////////////////
		
////////ALTERAR/////////////////////////////////////
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo alterar os dados do empregado?")) {
						empregado.setNome(txtNome.getText().toString());
						empregado.setCpf(txtCPF.getText().toString().replace(" ", "").replace(".", "").replace("-", ""));
						empregado.setTelefone(txtTelefone.getText().toString().replaceAll("[(]", "").replace(")", "").replace("-", "").replace(" ", ""));
						empregado.setDataEntrada(getDateFormated(txtData.getText().toString()));
						empregado.setUsuario(txtUsuario.getText().toString());
						empregado.setSenha(txtSenha.getText().toString());
						empregado.setFuncao(listaFuncoes.get(getIndexCombobox(cmbFuncao, "função")));
						//GERENTE
						Empregado gerente;
						if(cmbSupervisor.isVisible()) {
							gerente = new Empregado();
							gerente=listaEmpregadoExists.get(getIndexCombobox(cmbSupervisor, "supervidor"));
						}
						else 
							gerente = empregado;
						empregado.setSupervisor(gerente);
						empregado.setRua(txtRua.getText().toString());
						empregado.setNumeroEndereco(txtNumero.getText().toString());
						empregado.setBairro(txtBairro.getText().toString());
						empregado.setCidade(txtCidade.getText().toString());
						empregado.setEstado(txtEstado.getText().toString());
						empregado.setAtivo(true);
						empregado.setCep(txtCEP.getText().toString().trim());
						if(service.update(empregado)) {
							JOptionPane.showMessageDialog(null, "Empregado atualizado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Empregado não atualizado, detalhe do erro:\n\n"+e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnAlterar.setBounds(410, 423, 89, 23);
		btnAlterar.setVisible(false);
		frame.getContentPane().add(btnAlterar);
///////////////////////////////////////////////////////
		
		
////////APAGAR//////////////////////////////////////
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(confirmarAcao("Continuar?", "Deseja mesmo apagar o empregado?")) {
						if(service.delete(empregado)) {
							JOptionPane.showMessageDialog(null, "Empregado deletado");
							frame.dispose();
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro ao apagar o empregado, detalhe do erro:\n\n"+e1.getMessage());
				}
			}
		});
		btnApagar.setBounds(311, 423, 89, 23);
		btnApagar.setVisible(false);
		frame.getContentPane().add(btnApagar);
/////////////////////////////////////////////////////////////
		
		if (acaoTela==null||acaoTela==TipoAcaoTela.SALVAR) {
			cmbEmpregadosExists.setVisible(false);
			btnBuscar.setVisible(false);
		}
		else {
			btnSalvar.setVisible(false);
		}
		
	}
	
	//ATIBUTOS USADOS NA TELA 
	private Empregado empregado;
	private TipoAcaoTela acaoTela;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbEmpregadoExists(List<Empregado> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbEmpregadosExists.setModel(comboBoxModel);
	}
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbgerente(List<Empregado> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbSupervisor.setModel(comboBoxModel);
	}
	
	
	@SuppressWarnings("unchecked")
	private void setDadosCmbFuncao(List<FuncaoEmpregado> lista) {
		String[] itensCombo = new String[lista.size()];
		for(int i=0;i<lista.size();i++) {
			itensCombo[i]=lista.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbFuncao.setModel(comboBoxModel);
	}
	
	private int getIndexFuncao(List<FuncaoEmpregado> lista,  FuncaoEmpregado funcaoEmpregado) {
		int index = -1;
		for(int i=0;i<lista.size();i++) {
			if (lista.get(i).getId()==funcaoEmpregado.getId()) {
				//pegar index da função na lista
				index=i;
				//parar for
				i=lista.size();
			}
		}
		return index;
	}
	private int getIndexCombobox(JComboBox comboBox, String nomeCampo) {
		// TODO Auto-generated method stub
		int index = comboBox.getSelectedIndex();
		if(index<0) {
			throw new ValorInvalidoException("Selecione um valor para o atributo "+nomeCampo);
		}
		return index;
	}
	private int getIndexGerente(List<Empregado> lista,Empregado empregado) {
		int index = -1;
		for(int i=0;i<lista.size();i++) {
			if (lista.get(i).getId()==empregado.getId()) {
				//pegar index da função na lista
				index=i;
				//parar for
				i=lista.size();
			}
		}
		return index;
	}

	private Date getDateFormated(String strData){
		// TODO Auto-generated method stub
		
		if(strData.replace(" ", "").replace("/", "").length()<=0)
			throw new ValorInvalidoException("A data não pode ser nula");
		
		
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date;
		try {
			date = simpleDateFormat.parse(txtData.getText().toString().replace("/", "-"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ValorInvalidoException("a data informada é inválida");
		}
		
		return date;
	}
	
	private boolean confirmarAcao(String titulo, String mensagem) {
		int opcion = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);

		if (opcion == 0) 
		   return true;
		else 
		   return false;
		
	}
}
