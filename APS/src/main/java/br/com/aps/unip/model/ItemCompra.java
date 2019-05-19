package br.com.aps.unip.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.aps.unip.exception.ValorInvalidoException;

@Entity
@Table(name="tb_item_compra")
public class ItemCompra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_item_compra")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Produto produto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_compra")
	private Compra compra;
	@Column(name="valor_item")
	private BigDecimal valor;
	@Column(name="quantidade_item")
	private Integer quantidade;
	
	

	public ItemCompra() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		if (produto==null)
			throw new ValorInvalidoException("o produto não pode nem ser nulo");
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		if (compra==null)
			throw new ValorInvalidoException("a compra não pode nem ser nula");
		this.compra = compra;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		if(valor==null||valor.doubleValue()<0)
			throw new ValorInvalidoException("o valor do item não pode ser menor que 0 e nem ser vazia");
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		if(quantidade==null||quantidade<0)
			throw new ValorInvalidoException("a quantidade do item não pode ser menor que 0 e nem ser vazia");
		this.quantidade = quantidade;
	}
	
	
	
	
}
