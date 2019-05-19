package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.service.CaixaService;
import br.com.aps.unip.util.JPAUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaOpenVendaRelatorio {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public TelaOpenVendaRelatorio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final List<Caixa> listaCaixa = new CaixaService(JPAUtil.createEntityManager()).getList();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 321, 190);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JComboBox cmbCaixa = new JComboBox();
		cmbCaixa.setBounds(10, 11, 285, 20);
		frame.getContentPane().add(cmbCaixa);
		
		String[] itensCombo = new String[listaCaixa.size()];
		for(int i=0;i<listaCaixa.size();i++) {
			itensCombo[i]=listaCaixa.get(i).getNome();
		}
		
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>(itensCombo);
		cmbCaixa.setModel(comboBoxModel);
		
		
		JButton btnVerTodos = new JButton("Ver todos os caixas");
		btnVerTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCaixaVendaRelatorio tela = new TelaCaixaVendaRelatorio();
				tela.getFrame().setVisible(true);
			}
		});
		btnVerTodos.setBounds(10, 117, 285, 23);
		frame.getContentPane().add(btnVerTodos);
		
		JButton btnVerCaixaSelecionado = new JButton("Ver caixa selecionado");
		btnVerCaixaSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TelaCaixaVendaRelatorio telaCaixaVendaRelatorio = new TelaCaixaVendaRelatorio(listaCaixa.get(cmbCaixa.getSelectedIndex()));
					telaCaixaVendaRelatorio.getFrame().setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnVerCaixaSelecionado.setBounds(10, 83, 285, 23);
		frame.getContentPane().add(btnVerCaixaSelecionado);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
