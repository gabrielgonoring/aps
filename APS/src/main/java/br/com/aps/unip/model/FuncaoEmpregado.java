package br.com.aps.unip.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.aps.unip.exception.ValorInvalidoException;

@Entity
@Table(name="tb_funcao")
public class FuncaoEmpregado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_funcao")
	private Integer id;
	@Column(name="nm_funcao")
	private String nome;
	@Column(name="desc_funcao")
	private String descricao;
	@Column(name="salario_funcao")
	private BigDecimal salario;
	@Column(name="fg_ativo")
	private Boolean ativo;
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
		if (nome == null||nome.length()>60 || nome.length()<=0)
			throw new ValorInvalidoException("o nome da função não pode ter mais do que 60 caracteres e nem ser vazia");
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao){
		if (descricao==null||descricao.length()>60)
			throw new ValorInvalidoException("a descrição da função não pode ter mais do que 60 caracteres");
		this.descricao = descricao;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		String valor = salario.toString();
		if((valor.indexOf(".")>5)||(valor.length()-1 - valor.indexOf(".")>2)||salario.doubleValue()<=0)
			throw new ValorInvalidoException("O salário não pode ter 7 casa antes da vírgunla, mais de 2 casas decimais depois da vírgula e nem ser menor ou igual a zero");
		
		this.salario = salario;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		if (ativo==null)
			throw new ValorInvalidoException("ativo não pode ser nulo");
		this.ativo = ativo;
	}
	public FuncaoEmpregado(String nome, String descricao, BigDecimal salario, Boolean ativo) {
		this.nome = nome;
		this.descricao = descricao;
		this.salario = salario;
		this.ativo = ativo;
	}
	public FuncaoEmpregado() {
	}
	
	
	
}
