package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.FormaPagamento;

public class FormaPagamentoDAO extends DAO<FormaPagamento>{

	
	public FormaPagamentoDAO(EntityManager em) {
		super(em, FormaPagamento.class);
	}

	@Override
	public void delete(FormaPagamento entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setAtivo(false);
		em.unwrap(Session.class).update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPagamento> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM FormaPagamento e where e.ativo = true").getResultList();
	}
	
}
