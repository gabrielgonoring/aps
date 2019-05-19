package br.com.aps.unip.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.aps.unip.data.CaixaDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Empregado;

public class CaixaService {
	private CaixaDAO dao;
	private EntityManager em;
	
	public CaixaService(EntityManager em) {
		dao = new CaixaDAO(em);
		this.em=em;
	}
	
	public List<Empregado> getListEmpregado(){
		return new EmpregadoService(em).getList();
	}
	
	public boolean save(Caixa caixa) throws Exception {
		
		try {
			checkIllegalArguments(caixa);
			
			em.getTransaction().begin();
			
			dao.save(caixa);
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<Caixa>getList() {
		return dao.getList();
	}
	
	public boolean update(Caixa caixa) throws Exception {
		try {
			checkIllegalArguments(caixa);
			em.getTransaction().begin();
			dao.update(caixa);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean delete(Caixa caixa)throws Exception{
		try {
			em.getTransaction().begin();
			dao.delete(caixa);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	private void checkIllegalArguments(Caixa caixa) {
		if(caixa.getNome() == null)
			throw new ValorInvalidoException("o nome do caixa não pode ser nulo");
		if(caixa.getAtivo() == null)
			throw new ValorInvalidoException("o valor de ativo não pode ser nulo");
		if(caixa.getAtivo() == false)
			throw new ValorInvalidoException("o valor de ativo não pode ser false");
	}
}
