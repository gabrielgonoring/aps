package br.com.aps.unip.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import br.com.aps.unip.data.EmpregadoDAO;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.service.EmpregadoService;
import br.com.aps.unip.util.JPAUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.awt.event.ActionEvent;

public class LoginTela {

	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginTela window = new LoginTela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginTela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 249);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(92, 59, 242, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(92, 115, 242, 20);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpregadoService service = new EmpregadoService(JPAUtil.createEntityManager());
				try {
					Empregado empregado = service.login(txtUsuario.getText().toString(), txtSenha.getText().toString());
					TelaPrincipal tela = new TelaPrincipal(empregado);
					tela.getFrame().setVisible(true);
					frame.dispose();
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao realizar o login, detalhe do erro:\n\n"+e.getMessage());
				}
				
			}
		});
		btnLogar.setBounds(245, 167, 89, 23);
		frame.getContentPane().add(btnLogar);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(92, 34, 46, 14);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(92, 90, 46, 14);
		frame.getContentPane().add(lblSenha);
	}
}
