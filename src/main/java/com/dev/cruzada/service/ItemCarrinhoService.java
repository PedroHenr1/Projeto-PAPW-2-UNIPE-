package com.dev.cruzada.service;

import com.dev.cruzada.Repository.ItemCarrinhoRepository;
import com.dev.cruzada.domain.Carrinho.ItemCarrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarrinhoService
{
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRep;

    public ItemCarrinho itemSave(ItemCarrinho item)
    {
        return itemCarrinhoRep.save(item);
    }

    public ItemCarrinho itemUpdate(ItemCarrinho item) throws RuntimeException
    {
        if(item != null)
            return itemCarrinhoRep.save(item);
        throw new RuntimeException("Esse item não existe.");
    }


    public void itemDelete(Long id)
    {
        itemCarrinhoRep.deleteById(id);
    }

    public List<ItemCarrinho> itemList()
    {
        return itemCarrinhoRep.findAll();
    }

    public ItemCarrinho itemGetById(Long id)
    {
        return itemCarrinhoRep.findById(id).get();
    }

    public List<ItemCarrinho> getPessoaCarrinhobyPessoaId(Long id)
    {
        return itemCarrinhoRep.findItemCarrinhoByItemCarrinhoPessoaId(id);
    };
}
