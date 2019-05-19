package br.com.aps.unip.service;

import java.math.BigDecimal;
import java.security.Provider.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.aps.unip.data.CompraDAO;
import br.com.aps.unip.data.FormaPagamentoDAO;
import br.com.aps.unip.data.ItemCompraDAO;
import br.com.aps.unip.exception.ValorInvalidoException;
import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.FormaPagamento;
import br.com.aps.unip.model.ItemCompra;
import br.com.aps.unip.model.Produto;

public class CompraService {
	private CompraDAO dao;
	private ItemCompraDAO daoItem;
	private ProdutoService produtoService;
	private EntityManager em;
	
	public CompraService(EntityManager em) {
		dao = new CompraDAO(em);
		daoItem = new ItemCompraDAO(em);
		produtoService = new ProdutoService(em);
		this.em=em;
	}
	
	public List<FormaPagamento> getListFormaPagamento(){
		return new FormaPagamentoDAO(em).getList();
	}
	
	public List<Produto> getListProduto(){
		return produtoService.getListWithQuantidade();
	}
	
	public List<Caixa> getListCaixa(){
		return new CaixaService(em).getList();
	}
	
	public Produto findProdutoByid(Integer id) throws Exception{
		try {
			return produtoService.findById(id);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean save(Compra compra, List<ItemCompra> listaItens) throws Exception {
		
		try {
			checkIllegalArguments(compra, listaItens);
			
			em.getTransaction().begin();
			
			dao.save(compra);
			
			 
			
			for (ItemCompra itemCompra : listaItens) {
				if(!produtoService.subtractQuantidade(itemCompra.getQuantidade(), itemCompra.getProduto().getId()))
					throw new Exception("Não foi possível salvar a compra, erro ao discontar a quantidade do produto no estoque");
				daoItem.save(itemCompra);
			}
				
			em.getTransaction().commit();
			
			return true;
		
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e.getMessage());
		}
		
	}
	
	public BigDecimal getValorTotal(Compra compra) throws Exception {
		try {
			return dao.getValorTotal(compra);
		}
		catch (NoResultException e) {
			throw new Exception("a compra informada não existe");
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	public List<Compra> getListCompraByCaixa(Caixa caixa) throws Exception {
		// TODO Auto-generated method stub
		try {
			return dao.getListCompraByCaixa(caixa);
		}
		catch (NoResultException e) {
			throw new Exception("o caixa informado não existe");
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public List<ItemCompra> getListItemByCompra(Compra compra) throws Exception {
		// TODO Auto-generated method stub
		try {
			return daoItem.getListItemByCompra(compra);
		}
		catch (NoResultException e) {
			throw new Exception("a compra informada não existe");
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public List<Compra>getList() {
		return dao.getList();
	}
	
	private void checkIllegalArguments(Compra compra, List<ItemCompra> listaItens) {
		if (listaItens.size()<=0) 
			throw new ValorInvalidoException("Você não pode salvar uma compra sem ter itens");
		if(compra.getCaixa()==null)
			throw new ValorInvalidoException("o caixa não pode ser nulo");
		if(compra.getEmpregado()==null)
			throw new ValorInvalidoException("o empregado não pode ser nulo");
		if(compra.getData()==null)
			throw new ValorInvalidoException("a data da compra não pode ser nula");
		if(compra.getFormaPagamento()==null)
			throw new ValorInvalidoException("a forma de pagamento não pode ser nula");
		
	}
}
