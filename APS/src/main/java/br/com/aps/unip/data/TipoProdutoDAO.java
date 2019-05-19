package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.TipoProduto;

public class TipoProdutoDAO extends DAO<TipoProduto>{

	public TipoProdutoDAO(EntityManager em) {
		super(em, TipoProduto.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete(TipoProduto entity) throws Exception {
		entity.setAtivo(false);
		em.unwrap(Session.class).update(entity);
	}

	@Override
	public List<TipoProduto> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM TipoProduto e where e.ativo = true").getResultList();
	}

}
