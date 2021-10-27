package com.web2.web2.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Filial")
public class Filial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name = "Nome")
	private String nome;

	@NotBlank
	@Email
	@Column(name = "Email")
	private String email;

	@NotBlank
	@Column(name = "Telefone")
	private String telefone;

	@NotBlank
	@Column(name = "Cidade")
	private String cidade;

	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionarios")
	private Set<Funcionarios> funcionariosSet;*/

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
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
/*
	public Set<Funcionarios> getFuncionariosSet() {
		return funcionariosSet;
	}

	public void setFuncionariosSet(Set<Funcionarios> funcionariosSet) {
		this.funcionariosSet = funcionariosSet;
	}*/
}
