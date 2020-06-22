package com.dev.cruzada.domain.Carrinho;

import com.dev.cruzada.domain.PessoaFisica;
import com.dev.cruzada.domain.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class ItemCarrinho
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCarrinhoId;
    @ManyToOne

    @JsonBackReference
    @JsonIgnore
    private PessoaFisica itemCarrinhoPessoaId;
    @OneToOne
    private Produto itemCarrinhoProdutoId;
    private Integer itemCarrinhoQuantidade;
    private Float itemCarrinhoPreco;

    public Long getItemCarrinhoId() {
        return itemCarrinhoId;
    }

    public void setItemCarrinhoId(Long itemCarrinhoId) {
        this.itemCarrinhoId = itemCarrinhoId;
    }

    public PessoaFisica getItemCarrinhoPessoaId() {
        return itemCarrinhoPessoaId;
    }

    public void setItemCarrinhoPessoaId(PessoaFisica itemCarrinhoPessoaId) {
        this.itemCarrinhoPessoaId = itemCarrinhoPessoaId;
    }

    public Produto getItemCarrinhoProdutoId() {
        return itemCarrinhoProdutoId;
    }

    public void setItemCarrinhoProdutoId(Produto itemCarrinhoProdutoId) {
        this.itemCarrinhoProdutoId = itemCarrinhoProdutoId;
    }

    public Integer getItemCarrinhoQuantidade() {
        return itemCarrinhoQuantidade;
    }

    public void setItemCarrinhoQuantidade(Integer itemCarrinhoQuantidade) {
        this.itemCarrinhoQuantidade = itemCarrinhoQuantidade;
    }

    public Float getItemCarrinhoPreco() {
        return itemCarrinhoPreco;
    }

    public void setItemCarrinhoPreco(Float itemCarrinhoPreco) {
        this.itemCarrinhoPreco = itemCarrinhoPreco;
    }
}
