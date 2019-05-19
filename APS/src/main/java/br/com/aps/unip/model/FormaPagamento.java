package br.com.aps.unip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.aps.unip.exception.ValorInvalidoException;

@Entity
@Table(name="tb_forma_pagamento")
public class FormaPagamento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_forma_pagamento")
	private Integer id;
	@Column(name="desc_forma_pagamento")
	private String descricao;
	@Column(name="fg_ativo")
	private Boolean ativo;
	
	public FormaPagamento() {
	}
	
	public FormaPagamento(String descricao, Boolean ativo) {
		setDescricao(descricao);
		setAtivo(ativo);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao(){
		return descricao;
	}
	public void setDescricao(String descricao) throws ValorInvalidoException {
		if (descricao==null||descricao.length()>60 || descricao.length()<=0)
			throw new ValorInvalidoException("a descrição da forma de pagamento não pode ter mais do que 60 caracteres e nem vazia");
		this.descricao = descricao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		if (ativo==null)
			throw new ValorInvalidoException("ativo não pode ser nulo");
		this.ativo = ativo;
	}
	
	
}
