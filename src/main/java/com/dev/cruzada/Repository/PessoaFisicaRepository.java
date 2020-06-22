package com.dev.cruzada.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.cruzada.domain.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>
{

	PessoaFisica findByPessoaEmail(String email);
	PessoaFisica findPessoaFisicaByPessoaId(Long id);
	@Query("SELECT pessoaId FROM PessoaFisica WHERE pessoaEmail = ?1")
	Long getPessoaFisicaIdByPessoaEmail(String email);

}
