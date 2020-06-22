package com.dev.cruzada.Repository;

import com.dev.cruzada.domain.Carrinho.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long>
{
    @Query("SELECT i FROM ItemCarrinho i WHERE i.itemCarrinhoPessoaId.pessoaId = :id")
    List<ItemCarrinho> findItemCarrinhoByItemCarrinhoPessoaId(@Param("id") Long id);
}
