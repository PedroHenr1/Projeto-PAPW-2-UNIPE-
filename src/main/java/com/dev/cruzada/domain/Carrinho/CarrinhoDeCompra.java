package com.dev.cruzada.domain.Carrinho;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarrinhoDeCompra
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrinhoId;
    @NotEmpty
    private Long pessoaId;


    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

}
