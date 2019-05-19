package br.com.aps.unip.model;

import java.util.Date;

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
@Table(name="tb_compra")
public class Compra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_compra")
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_forma_pagamento")
	private FormaPagamento formaPagamento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_caixa")
	private Caixa caixa;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_empregado")
	private Empregado empregado;
	@Column(name="data_compra")
	private Date data;

	
	public Compra() {
	}
	
	public Compra(Integer id) {
		setId(id);
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		if (formaPagamento==null)
			throw new ValorInvalidoException("a forma de pagamento não pode nem ser nulo");
		this.formaPagamento = formaPagamento;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		if (empregado==null)
			throw new ValorInvalidoException("o empregado não pode nem ser nulo");
		this.empregado = empregado;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		if (caixa==null)
			throw new ValorInvalidoException("o caixa do produto não pode nem ser nulo");
		this.caixa = caixa;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		if (data==null)
			throw new ValorInvalidoException("a data da compra não pode nem ser nula");
		this.data = data;
	}
	
	
}
