package com.dev.cruzada.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long produtoId;
	private Float produtoValor;
	private String produtoNome;
	private String produtoDescricao;
	private Integer produtoEstoque;

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Float getProdutoValor() {
		return produtoValor;
	}

	public void setProdutoValor(Float produtoValor) {
		this.produtoValor = produtoValor;
	}

	public String getProdutoNome() {
		return produtoNome;
	}

	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}

	public String getProdutoDescricao() {
		return produtoDescricao;
	}

	public void setProdutoDescricao(String produtoDescricao) {
		this.produtoDescricao = produtoDescricao;
	}

	public Integer getProdutoEstoque() {
		return produtoEstoque;
	}

	public void setProdutoEstoque(Integer produtoEstoque) {
		this.produtoEstoque = produtoEstoque;
	}
}
