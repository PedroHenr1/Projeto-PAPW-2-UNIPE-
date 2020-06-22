package com.dev.cruzada.service;

import com.dev.cruzada.Repository.PedidoRepository;
import com.dev.cruzada.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService
{
    @Autowired
    private PedidoRepository pedidoRep;


    public Pedido pedidoSave(Pedido pedido) { return pedidoRep.save(pedido); }

    public Pedido pedidoUpdate(Pedido pedido) throws RuntimeException
    {
        if(pedido != null)
            return pedidoRep.save(pedido);
        throw new RuntimeException("Esse pedido não existe.");
    }


    public void pedidoDelete(Long id)
    {
        pedidoRep.deleteById(id);
    }

    public List<Pedido> pedidoList()
    {
        return pedidoRep.findAll();
    }

    public Pedido pedidoGetById(Long id)
    {
        return pedidoRep.findById(id).get();
    }

}
