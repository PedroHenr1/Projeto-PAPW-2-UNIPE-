package com.dev.cruzada.rest;

import com.dev.cruzada.Repository.PessoaFisicaRepository;
import com.dev.cruzada.auth.Filters.ConstantsSecurity;
import com.dev.cruzada.domain.Carrinho.ItemCarrinho;
import com.dev.cruzada.domain.PessoaFisica;
import com.dev.cruzada.service.ItemCarrinhoService;
import com.dev.cruzada.service.PessoaFisicaService;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cruzada")
public class ItemCarrinhoRest
{
    @Autowired
    private ItemCarrinhoService itemCarrinhoServ;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRep;

    @PostMapping("/app/itemcart")
    public ResponseEntity<ItemCarrinho> itemSave(@RequestBody @Valid ItemCarrinho item, @RequestHeader("Authorization") String token)
    {
        String email = Jwts.parser().setSigningKey(ConstantsSecurity.SECRET).parseClaimsJws(token.replace(ConstantsSecurity.TOKEN_PREFIX, "")).getBody().getSubject();
        Long id = pessoaFisicaRep.getPessoaFisicaIdByPessoaEmail(email);
        PessoaFisica pessoa = pessoaFisicaRep.findPessoaFisicaByPessoaId(id);
        item.setItemCarrinhoPessoaId(pessoa);
        return ResponseEntity.ok(itemCarrinhoServ.itemSave(item));
    }

    @PutMapping("/app/itemcart")
    public ResponseEntity<ItemCarrinho> itemupdate(@RequestBody @Valid ItemCarrinho item) { return ResponseEntity.ok(itemCarrinhoServ.itemUpdate(item)); }

    @DeleteMapping("/app/itemcart-{id}")
    public ResponseEntity itemDelete(@PathVariable Long id, @RequestHeader("Authorization") String token)
    {
        try
        {
            String email = Jwts.parser().setSigningKey(ConstantsSecurity.SECRET).parseClaimsJws(token.replace(ConstantsSecurity.TOKEN_PREFIX, "")).getBody().getSubject();
            Long pessoaId = pessoaFisicaRep.getPessoaFisicaIdByPessoaEmail(email);
            ItemCarrinho item = itemCarrinhoServ.itemGetById(id);
            if(item == null) return ResponseEntity.badRequest().build();
            if(item.getItemCarrinhoPessoaId().getPessoaId() == pessoaId) itemCarrinhoServ.itemDelete(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/app/itemcartAll")
    public ResponseEntity<List<ItemCarrinho>> itemList() { return ResponseEntity.ok(itemCarrinhoServ.itemList()); }

    @GetMapping("/app/itemcart-{id}")
    public ResponseEntity<ItemCarrinho> itemFindById(@PathVariable Long id) { return ResponseEntity.ok(itemCarrinhoServ.itemGetById(id)); }

    @GetMapping("/app/itemcart")
    public ResponseEntity<List<ItemCarrinho>> getCarrinhoPessoa(@RequestHeader("Authorization") String token)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();

        String email = Jwts.parser().setSigningKey(ConstantsSecurity.SECRET).parseClaimsJws(token.replace(ConstantsSecurity.TOKEN_PREFIX, "")).getBody().getSubject();
        Long id = pessoaFisicaRep.getPessoaFisicaIdByPessoaEmail(email);
        System.out.println("testt  "+id);
        return ResponseEntity.ok(itemCarrinhoServ.getPessoaCarrinhobyPessoaId(id));
    }
}
