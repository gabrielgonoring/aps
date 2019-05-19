package br.com.aps.unip.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.aps.unip.data.TipoProdutoDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.TipoProduto;

public class TipoProdutoService {
	private TipoProdutoDAO dao;
	private EntityManager em;
	
	public TipoProdutoService(EntityManager em) {
		dao = new TipoProdutoDAO(em);
		this.em=em;
	}
	
	public boolean save(TipoProduto tipoProduto) throws Exception {
		
		try {
			checkIllegalArguments(tipoProduto);
			
			em.getTransaction().begin();
			
			dao.save(tipoProduto);
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<TipoProduto>getList() {
		return dao.getList();
	}
	
	public boolean update(TipoProduto tipoProduto) throws Exception {
		try {
			checkIllegalArguments(tipoProduto);
			em.getTransaction().begin();
			dao.update(tipoProduto);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean delete(TipoProduto tipoProduto)throws Exception{
		try {
			em.getTransaction().begin();
			dao.delete(tipoProduto);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	private void checkIllegalArguments(TipoProduto tipoProduto) {
		if(tipoProduto.getAtivo()==null)
			throw new ValorInvalidoException("o valor de ativo não pode ser nulo");
		if(tipoProduto.getNome()==null)
			throw new ValorInvalidoException("o nome do tipo não pode ser nulo");
		if(tipoProduto.getDescricao()==null)
			throw new ValorInvalidoException("a descrição do tipo não pode ser nulo");
		if(tipoProduto.getAtivo() == false)
			throw new ValorInvalidoException("o valor de ativo não pode ser false");
	}
}
