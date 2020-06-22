package com.dev.cruzada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.cruzada.Repository.PessoaFisicaRepository;
import com.dev.cruzada.domain.PessoaFisica;

@Service
public class PessoaFisicaService
{
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRep;
	
	public PessoaFisica pessoaFisicaSave(PessoaFisica pessoa)
	{
		BCryptPasswordEncoder passEncoded = new BCryptPasswordEncoder();
		pessoa.setPessoaSenha(passEncoded.encode(pessoa.getPessoaSenha()));
		return pessoaFisicaRep.save(pessoa);
	}

	public PessoaFisica pessoaFindByEmail(String email)
	{
		return pessoaFisicaRep.findByPessoaEmail(email);
	}
	
	public PessoaFisica pessoaFisicaUpdate(PessoaFisica pessoa) throws RuntimeException
	{
		if(pessoa != null)
			return pessoaFisicaRep.save(pessoa);
		throw new RuntimeException("Essa pessoa não existe.");
	}
	
	
	public void pessoaFisicaDelete(Long id)
	{
		pessoaFisicaRep.deleteById(id);	
	}
	
	public List<PessoaFisica> pessoaFisicaList()
	{
		return pessoaFisicaRep.findAll();
	}
	
	public PessoaFisica pessoaFisicagetById(Long id)
	{
		return pessoaFisicaRep.findById(id).get();
	}
	
	
}

