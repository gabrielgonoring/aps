package br.com.aps.unip.data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.ItemCompra;

public class ItemCompraDAO extends DAO<ItemCompra> {

	public ItemCompraDAO(EntityManager em) {
		super(em, ItemCompra.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete(ItemCompra entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemCompra> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM ItemCompra e").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemCompra> getListItemByCompra(Compra compra)  {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM ItemCompra e WHERE e.compra = :compra").setParameter("compra", compra).getResultList();
	}
}
