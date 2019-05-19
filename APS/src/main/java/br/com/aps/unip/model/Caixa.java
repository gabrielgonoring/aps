package br.com.aps.unip.model;

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
@Table(name="tb_caixa")
public class Caixa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_caixa")
	private Integer id;
	@Column(name="nm_caixa")
	private String nome;
	@Column(name="fg_ativo")
	private Boolean ativo;
	
	public Caixa() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome==null||nome.length()>30 || nome.length()<=0)
			throw new ValorInvalidoException("o nome do caixa não pode ter mais do que 30 caracteres e nem ser vazio");
		this.nome = nome;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		if (ativo==null)
			throw new ValorInvalidoException("o atributo ativo não pode ser nulo");
		this.ativo = ativo;
	}
	
}
