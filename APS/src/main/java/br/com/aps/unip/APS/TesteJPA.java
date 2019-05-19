package br.com.aps.unip.APS;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.aps.unip.model.Caixa;
import br.com.aps.unip.model.Compra;
import br.com.aps.unip.model.Empregado;
import br.com.aps.unip.model.FormaPagamento;
import br.com.aps.unip.model.FuncaoEmpregado;
import br.com.aps.unip.model.ItemCompra;
import br.com.aps.unip.model.Produto;
import br.com.aps.unip.model.TipoProduto;
import br.com.aps.unip.service.CaixaService;
import br.com.aps.unip.util.JPAUtil;
import com.google.gson.Gson;

public class TesteJPA {
	@PersistenceContext
	private EntityManager em1;
	
	public static void main(String[] args) {
		EntityManager em = JPAUtil.createEntityManager();
		List<ItemCompra> lista = em.createQuery("Select p from ItemCompra p where p.compra = :compra").setParameter("compra", new Compra(22)).getResultList();// where p.id>12
		for (ItemCompra formaPagamento : lista) {
			System.out.println(formaPagamento.getProduto().getDescricao());
			System.out.println("\n");
		}
         
		
		/*em.getTransaction().begin();
		
		Compra f = new Compra();
		
		f.setData(new Date());
		f.setIdCaixa(4);
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(12);
		f.setFormaPagamento(formaPagamento);
		
		em.persist(f);
		
		em.getTransaction().commit();
		em.close();*/
	}
}
