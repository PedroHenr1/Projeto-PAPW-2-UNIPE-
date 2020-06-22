package com.dev.cruzada.rest;

import com.dev.cruzada.domain.Produto;

import com.dev.cruzada.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cruzada")
public class ProdutoRest
{
    @Autowired
    private ProdutoService produtoServ;

    @PostMapping("/admin/produto")
    public ResponseEntity<Produto> produtoSave(@RequestBody @Valid Produto produto) { return ResponseEntity.ok(produtoServ.save(produto)); }

    @PutMapping("/admin/produto")
    public ResponseEntity<Produto> produtoUpdate(@RequestBody @Valid Produto produto) { return ResponseEntity.ok(produtoServ.update(produto)); }

    @DeleteMapping("/admin/produto-{id}")
    public ResponseEntity produtoDelete(@PathVariable Long id)
    {
        try
        {
            produtoServ.delete(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/app/produto")
    public ResponseEntity<List<Produto>> produtoList() { return ResponseEntity.ok(produtoServ.list()); }

    @GetMapping("/app/produto-{id}")
    public ResponseEntity<Produto> produtoFindById(@PathVariable Long id) { return ResponseEntity.ok(produtoServ.getById(id)); }
}
