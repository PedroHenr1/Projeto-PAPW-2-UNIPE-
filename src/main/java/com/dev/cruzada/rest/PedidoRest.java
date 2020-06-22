package com.dev.cruzada.rest;

import com.dev.cruzada.domain.Pedido;
import com.dev.cruzada.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cruzada")

public class PedidoRest
{
    @Autowired
    private PedidoService pedidoServ;

    @PostMapping("/app/pedido")
    public ResponseEntity<Pedido> pedidoSave(@RequestBody @Valid Pedido pedido)
    {
        return ResponseEntity.ok(pedidoServ.pedidoSave(pedido));
    }

    @PutMapping("/admin/pedido")
    public ResponseEntity<Pedido> pedidoUpdate(@RequestBody @Valid Pedido pedido)
    {
        return ResponseEntity.ok(pedidoServ.pedidoUpdate(pedido));
    }

    @DeleteMapping("/admin/pedido-{id}")
    public ResponseEntity pedidoDelete(@PathVariable Long id)
    {
        try
        {
            pedidoServ.pedidoDelete(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/app/pedido")
    public ResponseEntity<List<Pedido>> pedidoList()
    {
        return ResponseEntity.ok(pedidoServ.pedidoList());
    }

    @GetMapping("/app/pedido-{id}")
    public ResponseEntity<Pedido> pedidoGetbyId(@PathVariable Long id)
    {
        return ResponseEntity.ok(pedidoServ.pedidoGetById(id));
    }
}
