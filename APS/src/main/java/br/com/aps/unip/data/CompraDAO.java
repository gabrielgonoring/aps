package br.com.aps.unip.data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Compra;

public class CompraDAO extends DAO<Compra>{

	public CompraDAO(EntityManager em) {
		super(em, Compra.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void delete(Compra entity) throws Exception {
		//em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compra> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM Compra e").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Compra> getListCompraByCaixa(Caixa caixa) {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM Compra e WHERE e.caixa = :caixa ").setParameter("caixa", caixa).getResultList();
	}
	
	public BigDecimal getValorTotal(Compra compra) {
		return (BigDecimal) em.createQuery("SELECT SUM(e.valor * e.quantidade) FROM ItemCompra e WHERE e.compra = :compra").setParameter("compra", compra).getSingleResult();
	}
	
}
