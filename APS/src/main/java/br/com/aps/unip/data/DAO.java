package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Session;

public abstract class DAO<E> {
	
	protected EntityManager em;
	private Class<E> classe;
	
	public abstract void delete(E entity) throws Exception;
	
	public DAO(EntityManager em, Class<E> classe) {
		// TODO Auto-generated constructor stub
		this.em=em;
		this.classe = classe;
	}
	
	public abstract List<E> getList();
	
	
	public void update(E e) throws Exception{
		em.unwrap(Session.class).update(e);
	}
	
	public void save(E entity) throws Exception {
		em.persist(entity);
	}
}
