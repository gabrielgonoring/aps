package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.FormaPagamento;

public class CaixaDAO extends DAO<Caixa>{

	public CaixaDAO(EntityManager em) {
		super(em, Caixa.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete(Caixa entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setAtivo(false);
		em.unwrap(Session.class).update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caixa> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM Caixa e where e.ativo = true").getResultList();
	}
}
