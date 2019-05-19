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
@Table(name="tb_produto")
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_produto")
	private TipoProduto tipoProduto;
	@Column(name="valor_produto")
	private BigDecimal valor;
	@Column(name="quantidade_estoque")
	private Integer quantidade;
	@Column(name="desc_produto")
	private String descricao;
	@Column(name="fg_ativo")
	private Boolean ativo;
	
	public Produto() {
	}
	public Produto(Integer id) {
		setId(id);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		if (tipoProduto==null)
			throw new ValorInvalidoException("o tipo do produto não pode nem ser nulo");
		this.tipoProduto = tipoProduto;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		String svalor = valor.toString();
		if((svalor.indexOf(".")>5)||(svalor.length()-1 - svalor.indexOf(".")>2)||valor.doubleValue()<=0)
			throw new ValorInvalidoException("O valor não pode ter 7 casa antes da vírgunla e nem mais de 2 casas decimais depois da vírgula e nem ser menor ou igual a zero");
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		if(quantidade==null||quantidade<0)
			throw new ValorInvalidoException("a quantidade do produto não pode ser menor que 0 e nem ser vazia");
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		if (descricao==null||descricao.length()>60 || descricao.length()<=0)
			throw new ValorInvalidoException("a descrição do produto não pode ter mais do que 60 caracteres e nem ser vazia");
		this.descricao = descricao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		if (ativo==null)
			throw new ValorInvalidoException("o atruibuto ativo não pode nem ser nulo");
		this.ativo = ativo;
	}
	
	
}
