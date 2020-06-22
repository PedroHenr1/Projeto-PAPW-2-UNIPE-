package com.dev.cruzada.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.dev.cruzada.domain.Carrinho.ItemCarrinho;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;

import java.util.List;

@Entity
public class PessoaFisica
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Dados Pesoais
	private Long pessoaId;
	@NotEmpty
	private String pessoaNome;
	@NotEmpty
	private String pessoaSobrenome;
	@NotEmpty
	private String pessoaCpf;
	
	//Auth
	@NotEmpty
	@Column(unique = true)
	private String pessoaEmail;
	@NotEmpty
	private String pessoaSenha;
	
	//Adicionais
	@Nullable
	private String pessoaEndereco;
	@Nullable
	private String pessoaTelefone;
	
	//Admin
	@NotEmpty
	private Boolean pessoaIsAdmin;
	@NotEmpty
	private Boolean pessoaStatusCadastro;


	public PessoaFisica(){}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

	public void setPessoaNome(String pessoaNome) {
		this.pessoaNome = pessoaNome;
	}

	public String getPessoaSobrenome() {
		return pessoaSobrenome;
	}

	public void setPessoaSobrenome(String pessoaSobrenome) {
		this.pessoaSobrenome = pessoaSobrenome;
	}

	public String getPessoaCpf() {
		return pessoaCpf;
	}

	public void setPessoaCpf(String pessoaCpf) {
		this.pessoaCpf = pessoaCpf;
	}

	public String getPessoaEmail() {
		return pessoaEmail;
	}

	public void setPessoaEmail(String pessoaEmail) {
		this.pessoaEmail = pessoaEmail;
	}

	public String getPessoaSenha() {
		return pessoaSenha;
	}

	public void setPessoaSenha(String pessoaSenha) {
		this.pessoaSenha = pessoaSenha;
	}

	public String getPessoaEndereco() {
		return pessoaEndereco;
	}

	public void setPessoaEndereco(String pessoaEndereco) {
		this.pessoaEndereco = pessoaEndereco;
	}

	public String getPessoaTelefone() {
		return pessoaTelefone;
	}

	public void setPessoaTelefone(String pessoaTelefone) {
		this.pessoaTelefone = pessoaTelefone;
	}

	public Boolean getPessoaIsAdmin() {
		return pessoaIsAdmin;
	}

	public void setPessoaIsAdmin(Boolean pessoaIsAdmin) {
		this.pessoaIsAdmin = pessoaIsAdmin;
	}

	public Boolean getPessoaStatusCadastro() {
		return pessoaStatusCadastro;
	}

	public void setPessoaStatusCadastro(Boolean pessoaStatusCadastro) {
		this.pessoaStatusCadastro = pessoaStatusCadastro;
	}


}
