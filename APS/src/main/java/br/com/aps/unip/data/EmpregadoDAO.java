package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.Empregado;

public class EmpregadoDAO extends DAO<Empregado> {

	public EmpregadoDAO(EntityManager em) {
		super(em, Empregado.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void delete(Empregado entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setAtivo(false);
		em.unwrap(Session.class).update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empregado> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM Empregado e where e.ativo = true").getResultList();
	}
	
	public Empregado login(String usuario, String senha) {
		return (Empregado) em.createQuery("SELECT e FROM Empregado e WHERE e.usuario = :usuario AND e.senha=:senha AND e.ativo = true")
			.setParameter("usuario", usuario).setParameter("senha", senha).getSingleResult();
	}
}
