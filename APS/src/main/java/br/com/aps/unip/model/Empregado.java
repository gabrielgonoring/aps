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
@Table(name="tb_empregado")
public class Empregado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_empregado")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_funcao")
	private FuncaoEmpregado funcao;
	//@Column(name="id_gerente")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_supervisor")
	private Empregado supervisor;
	@Column(name="nm_empregado")
	private String nome;
	private String usuario;
	private String senha;
	@Column(name="cpf")
	private String cpf;
	@Column(name="telefone")
	private String telefone;
	@Column(name="data_entrada")
	private Date dataEntrada;
	private String cidade;
	private String estado;
	private String rua;
	private String bairro;
	@Column(name="num_endereco")
	private String numeroEndereco;
	private String cep;
	@Column(name="fg_ativo")
	private Boolean ativo;
	
	public Empregado(Integer id) {
		setId(id);
	}
	public Empregado() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		if (usuario==null||usuario.length()>30 || usuario.length()<=0)
			throw new ValorInvalidoException("o usuário do empregado não pode ter mais do que 30 caracteres e nem ser vazia");
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		if (senha==null||senha.length()>20 || senha.length()<=0)
			throw new ValorInvalidoException("a senha do empregado não pode ter mais do que 20 caracteres e nem ser vazia");
		this.senha = senha;
	}
	public FuncaoEmpregado getFuncao() {
		return funcao;
	}
	public void setFuncao(FuncaoEmpregado funcao) {
		if (funcao==null)
			throw new ValorInvalidoException("a função do empregado não pode ser vazia");
		this.funcao = funcao;
	}
	
	public Empregado getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Empregado supervisor) {
		this.supervisor = supervisor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome){
		if (nome==null||nome.length()>120 || nome.length()<=0)
			throw new ValorInvalidoException("o nome do empregado não pode ter mais do que 120 caracteres e nem ser vazio");
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		if (cpf==null||cpf.length()!=11||cpf.length()<=0)
			throw new ValorInvalidoException("o cpf do empregado precisa ter 11 caracteres e não pode ser vazia");
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if (telefone.length()!=11 && telefone.length()!=0)
			throw new ValorInvalidoException("o telefone do empregado precisa ter 11 caracteres");
		this.telefone = telefone;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		if (dataEntrada==null)
			throw new ValorInvalidoException("o data de entrada do empregado não pode ser vazia");
		this.dataEntrada = dataEntrada;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		if (cidade==null||cidade.length()>30 || cidade.length()<=0)
			throw new ValorInvalidoException("a cidade do empregado não pode ter mais do que 30 caracteres e nem ser vazia");
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		if (estado==null||estado.length()>15 || estado.length()<=0)
			throw new ValorInvalidoException("o estado do empregado não pode ter mais do que 15 caracteres e nem ser vazia");
		this.estado = estado;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		if (rua==null||rua.length()>120 || rua.length()<=0)
			throw new ValorInvalidoException("a rua do empregado não pode ter mais do que 120 caracteres e nem ser vazia");
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		if (bairro==null||bairro.length()>60 || bairro.length()<=0)
			throw new ValorInvalidoException("o bairro do empregado não pode ter mais do que 60 caracteres e nem ser vazia");
		this.bairro = bairro;
	}
	public String getNumeroEndereco() {
		return numeroEndereco;
	}
	public void setNumeroEndereco(String numeroEndereco) {
		if (nome==null||numeroEndereco.length()>10 || numeroEndereco.length()<=0)
			throw new ValorInvalidoException("o numero do endereço do empregado não pode ter mais do que 10 caracteres e nem ser vazia");
		this.numeroEndereco = numeroEndereco;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		if (cpf==null||cpf.length()<=0)
			throw new ValorInvalidoException("o cep do empregadoe não pode ser vazia");
		this.cep = cep;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		if (ativo==null)
			throw new ValorInvalidoException("ativo não pode ser null");
		this.ativo = ativo;
	}
	
	
}
