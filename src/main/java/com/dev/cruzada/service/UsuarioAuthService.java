package com.dev.cruzada.service;

import java.util.List;
import java.util.Optional;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dev.cruzada.Repository.PessoaFisicaRepository;
import com.dev.cruzada.domain.PessoaFisica;


@Component
public class UsuarioAuthService implements UserDetailsService
{
    //private final
    private final PessoaFisicaRepository pessoaFisicaRep;

    @Autowired
    public UsuarioAuthService(PessoaFisicaRepository pessoaFisicaRep) {
        this.pessoaFisicaRep = pessoaFisicaRep;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PessoaFisica pessoa = Optional.ofNullable(pessoaFisicaRep.findByPessoaEmail(email)).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado."));
        System.out.println(pessoa.getPessoaSenha());

        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        User user = new User(pessoa.getPessoaEmail(), pessoa.getPessoaSenha(), pessoa.getPessoaIsAdmin() ? authorityListAdmin : authorityListUser);
        return user;
    }

    public boolean pessoaLiberada(String email) throws NotFoundException {
        try {
            PessoaFisica pessoa = pessoaFisicaRep.findByPessoaEmail(email);
            if (pessoa.getPessoaStatusCadastro())
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            throw new NotFoundException("Usuario nao encontrado.");
        }
    }

}
