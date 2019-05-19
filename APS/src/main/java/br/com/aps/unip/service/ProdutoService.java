package br.com.aps.unip.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.aps.unip.data.ProdutoDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.Produto;
import br.com.aps.unip.model.TipoProduto;

public class ProdutoService {
	private ProdutoDAO dao;
	private EntityManager em;
	
	public ProdutoService(EntityManager em) {
		dao = new ProdutoDAO(em);
		this.em=em;
	}
	public List<TipoProduto> getListTipoProduto(){
		return new TipoProdutoService(em).getList();
	}
	
	public Produto findById(Integer id) throws Exception{
		try {
			return dao.findByIdWithQuantidade(id);
		}catch (NoResultException e) {
			throw new ValorInvalidoException("O id informado não existe ou o produto não tem estoque suficiente");
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean save(Produto produto) throws Exception {
		
		try {
			checkIllegalArguments(produto);
			
			em.getTransaction().begin();
			
			dao.save(produto);
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<Produto>getList() {
		return dao.getList();
	}
	
	public List<Produto>getListWithQuantidade() {
		return dao.getListWithQuantidade();
	}
	
	
	public boolean update(Produto produto) throws Exception {
		try {
			checkIllegalArguments(produto);
			em.getTransaction().begin();
			dao.update(produto);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean delete(Produto produto)throws Exception{
		try {
			em.getTransaction().begin();
			dao.delete(produto);
			em.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean subtractQuantidade(Integer quantidade, Integer id)throws Exception {
		// TODO Auto-generated method stub
		return dao.subtractQuantidade(quantidade, id);
	}
	
	private void checkIllegalArguments(Produto produto) {
		if(produto.getAtivo() == null)
			throw new ValorInvalidoException("ativo não pode ser nulo");
		if(produto.getDescricao() == null)
			throw new ValorInvalidoException("a descrição não pode ser nulo");
		if(produto.getTipoProduto()== null)
			throw new ValorInvalidoException("o tipo do produto não pode ser nulo");
		if(produto.getQuantidade()== null)
			throw new ValorInvalidoException("a quantidade do produto não pode ser nulo");
		if(produto.getValor()== null)
			throw new ValorInvalidoException("o valor do produto não pode ser nulo");
		if(produto.getAtivo() == false)
			throw new ValorInvalidoException("o valor de ativo não pode ser false");
	}
}
