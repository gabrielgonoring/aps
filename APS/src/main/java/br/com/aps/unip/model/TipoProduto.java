package br.com.aps.unip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.aps.unip.exception.ValorInvalidoException;

@Entity
@Table(name="tb_tipo_produto")
public class TipoProduto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_produto")
	private Integer id;
	@Column(name="nm_tipo_produto")
	private String nome;
	@Column(name="desc_tipo_produto")
	private String descricao;
	@Column(name="fg_ativo")
	private Boolean ativo;

	public TipoProduto() {
	}
	public Integer getId() {
		return id;
	}
	private void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome==null||nome.length()>25 || nome.length()<=0)
			throw new ValorInvalidoException("o nome do tipo de produto não pode ter mais do que 25 caracteres e nem ser vazio");
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		if (descricao.length()>60)
			throw new ValorInvalidoException("a descrição do tipo de produto não pode ter mais do que 60 caracteres");
		this.descricao = descricao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		if (ativo==null)
			throw new ValorInvalidoException("o valor de ativo não pode ser nulo");
		this.ativo = ativo;
	}
	
	
}
