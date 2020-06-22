package com.dev.cruzada.service;

import com.dev.cruzada.Repository.ProdutoRepository;
import com.dev.cruzada.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService
{
    @Autowired
    private ProdutoRepository produtoRep;

    public Produto save(Produto produto) { return produtoRep.save(produto); }

    public Produto update(Produto produto) throws RuntimeException
    {
        if(produto != null)
            return produtoRep.save(produto);
        throw new RuntimeException("Esse produto não existe.");
    }


    public void delete(Long id)
    {
        produtoRep.deleteById(id);
    }

    public List<Produto> list()
    {
        return produtoRep.findAll();
    }

    public Produto getById(Long id)
    {
        return produtoRep.findById(id).get();
    }

}
