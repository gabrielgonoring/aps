package br.com.aps.unip.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.aps.unip.data.FuncaoEmpregadoDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.FuncaoEmpregado;
import br.com.aps.unip.model.ItemCompra;

public class FuncaoEmpregadoService {
	private FuncaoEmpregadoDAO dao;
	private EntityManager em;
	public FuncaoEmpregadoService(EntityManager em) {
		dao = new FuncaoEmpregadoDAO(em);
		this.em=em;
	}
	
	public boolean save(FuncaoEmpregado funcaoEmpregado) throws Exception {
		
		try {
			checkIllegalArguments(funcaoEmpregado);
			
			em.getTransaction().begin();
			
			dao.save(funcaoEmpregado);
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<FuncaoEmpregado>getList() {
		return dao.getList();
	}
	
	public boolean update(FuncaoEmpregado funcaoEmpregado) throws Exception {
		try {
			checkIllegalArguments(funcaoEmpregado);
			em.getTransaction().begin();
			dao.update(funcaoEmpregado);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean delete(FuncaoEmpregado funcaoEmpregado)throws Exception{
		try {
			em.getTransaction().begin();
			dao.delete(funcaoEmpregado);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	private void checkIllegalArguments(FuncaoEmpregado funcaoEmpregado) {
		if(funcaoEmpregado.getAtivo() == null)
			throw new ValorInvalidoException("ativo não pode ser nulo");
		if(funcaoEmpregado.getAtivo() == false)
			throw new ValorInvalidoException("ativo não pode ser false");
		if(funcaoEmpregado.getNome() == null)
			throw new ValorInvalidoException("o nome não pode ser nulo");
		if(funcaoEmpregado.getSalario() == null)
			throw new ValorInvalidoException("o salário não pode ser nulo");
	}
}
