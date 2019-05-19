package br.com.aps.unip.service;

import java.text.Normalizer.Form;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.aps.unip.data.FormaPagamentoDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.FormaPagamento;

public class FormaPagamentoService {
	private FormaPagamentoDAO dao;
	private EntityManager em;
	public FormaPagamentoService(EntityManager em) {
		dao = new FormaPagamentoDAO(em);
		this.em=em;
	}
	
	public boolean save(FormaPagamento formaPagamento) throws Exception {
		
		try {
			checkIllegalArguments(formaPagamento);
			
			em.getTransaction().begin();
			
			dao.save(formaPagamento);
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<FormaPagamento>getList() {
		return dao.getList();
	}
	
	public boolean update(FormaPagamento formaPagamento) throws Exception {
		try {
			checkIllegalArguments(formaPagamento);
			em.getTransaction().begin();
			dao.update(formaPagamento);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean delete(FormaPagamento formaPagamento)throws Exception{
		try {
			em.getTransaction().begin();
			dao.delete(formaPagamento);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	private void checkIllegalArguments(FormaPagamento formaPagamento) {
		if(formaPagamento.getAtivo() == null)
			throw new ValorInvalidoException("o valor de ativo não pode ser nulo");
		if(formaPagamento.getDescricao() == null)
			throw new ValorInvalidoException("o valor da descrição não pode ser nula");
		if(formaPagamento.getAtivo() == false)
			throw new ValorInvalidoException("o valor de ativo não pode ser false");
	}
}
