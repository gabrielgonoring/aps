package br.com.aps.unip.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.aps.unip.data.EmpregadoDAO;
import br.com.aps.unip.data.FormaPagamentoDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.FormaPagamento;
import br.com.aps.unip.model.FuncaoEmpregado;

public class EmpregadoService {
	
	private EmpregadoDAO dao;
	private EntityManager em;
	public EmpregadoService(EntityManager em) {
		dao = new EmpregadoDAO(em);
		this.em=em;
	}
	
	public Empregado login(String usuario, String senha)throws Exception {
		if (usuario==null||usuario.equals("")) 
			throw new ValorInvalidoException("O valor de usuario não pode ser nulo");
		
		if(senha==null|| senha.equals(""))
			throw new ValorInvalidoException("O valor da senha não pode ser nulo");
	
		try {
			return dao.login(usuario, senha);
		} 
		catch (NoResultException e) {
			throw new ValorInvalidoException("Usuário ou senha inválido");
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
	
	public List<FuncaoEmpregado> getListFuncao(){
		return new FuncaoEmpregadoService(em).getList();
	}
	
	public boolean save(Empregado empregado) throws Exception {
		
		try {
			checkIllegalArguments(empregado);
			
			em.getTransaction().begin();
			
			dao.save(empregado);
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<Empregado>getList() {
		return dao.getList();
	}
	
	public boolean update(Empregado empregado) throws Exception {
		try {
			checkIllegalArguments(empregado);
			em.getTransaction().begin();
			dao.update(empregado);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean delete(Empregado empregado)throws Exception{
		try {
			em.getTransaction().begin();
			dao.delete(empregado);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	private void checkIllegalArguments(Empregado empregado) {
		if(empregado.getNome() == null)
			throw new ValorInvalidoException("o nome do empregado não pode ser nulo");
		if(empregado.getCpf() == null)
			throw new ValorInvalidoException("o cpf do empregado não pode ser nulo");
		if(empregado.getDataEntrada() == null)
			throw new ValorInvalidoException("a data do empregado não pode ser nula");
		if(empregado.getUsuario() == null)
			throw new ValorInvalidoException("o usuário do empregado não pode ser nulo");
		if(empregado.getSenha() == null)
			throw new ValorInvalidoException("a senha do empregado não pode ser nula");
		if(empregado.getFuncao() == null)
			throw new ValorInvalidoException("a função do empregado não pode ser nula");
		if(empregado.getSupervisor() == null)
			throw new ValorInvalidoException("o supervisor do empregado não pode ser nulo, ele mesmo precisa ser o seu supervisor caso não\n tenha outro empregado para o supervisionar");
		if(empregado.getRua() == null)
			throw new ValorInvalidoException("a rua do empregado não pode ser nula");
		if(empregado.getNumeroEndereco() == null)
			throw new ValorInvalidoException("o numero do endereço do empregado não pode ser nulo");
		if(empregado.getCidade() == null)
			throw new ValorInvalidoException("a cidade do empregado não pode ser nula");
		if(empregado.getEstado() == null)
			throw new ValorInvalidoException("o estado do empregado não pode ser nulo");
		if(empregado.getCep() == null)
			throw new ValorInvalidoException("o cep do empregado não pode ser nulo");
		if(empregado.getAtivo() == false)
			throw new ValorInvalidoException("o valor de ativo não pode ser false");
	}
}
