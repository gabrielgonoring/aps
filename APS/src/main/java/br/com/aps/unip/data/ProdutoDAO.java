package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.Produto;

public class ProdutoDAO extends DAO<Produto>{

	public ProdutoDAO(EntityManager em) {
		super(em, Produto.class);
		// TODO Auto-generated constructor stub
	}

	public Produto findByIdWithQuantidade(Integer id)throws Exception {
		// TODO Auto-generated method stub
		return (Produto) em.createQuery("SELECT e FROM Produto e WHERE e.id = :id AND e.ativo = true AND e.quantidade>0").setParameter("id", id).getSingleResult();
	}
	@Override
	public void delete(Produto entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setAtivo(false);
		em.unwrap(Session.class).update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM Produto e where e.ativo = true").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Produto> getListWithQuantidade() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM Produto e where e.ativo = true AND e.quantidade>0").getResultList();
	}
	
	public boolean subtractQuantidade(Integer quantidade, Integer id)throws Exception {
		// TODO Auto-generated method stub
		return  em.createQuery("UPDATE Produto e SET e.quantidade = e.quantidade - :qtd WHERE e.id = :id")
				.setParameter("qtd", quantidade)
				.setParameter("id", id)
				.executeUpdate() >0;
	}
}
