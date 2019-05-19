package br.com.aps.unip.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.aps.unip.model.FuncaoEmpregado;

public class FuncaoEmpregadoDAO extends DAO<FuncaoEmpregado>{

	public FuncaoEmpregadoDAO(EntityManager em) {
		super(em, FuncaoEmpregado.class);
	}

	@Override
	public void delete(FuncaoEmpregado entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setAtivo(false);
		em.unwrap(Session.class).update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuncaoEmpregado> getList() {
		// TODO Auto-generated method stub
		return  em.createQuery("SELECT e FROM FuncaoEmpregado e where e.ativo = true").getResultList();
	}

}
