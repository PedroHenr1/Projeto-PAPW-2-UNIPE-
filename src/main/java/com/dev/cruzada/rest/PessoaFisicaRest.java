package com.dev.cruzada.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.Response;
import javax.xml.ws.handler.MessageContext;

import com.dev.cruzada.auth.Filters.ConstantsSecurity;

import com.dev.cruzada.service.UsuarioAuthService;
import io.jsonwebtoken.Jwts;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.dev.cruzada.domain.PessoaFisica;
import com.dev.cruzada.service.PessoaFisicaService;

@RestController
@RequestMapping("/cruzada")
public class PessoaFisicaRest
{
	@Autowired
	private UsuarioAuthService userAuthServ;

	@Autowired
	private PessoaFisicaService pessoaFisicaServ;
	
	@PostMapping("/app/user")
	public ResponseEntity<PessoaFisica> pessoaFisicaSave(@RequestBody @Valid PessoaFisica pessoa)
	{
		System.out.println(pessoa.getPessoaNome());
		return ResponseEntity.ok(pessoaFisicaServ.pessoaFisicaSave(pessoa));
	}

	@GetMapping("/online")
	public ResponseEntity isOnline(@RequestHeader("Authorization") String token, HttpServletRequest request)
	{
		if(token == null) return ResponseEntity.badRequest().build();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = Jwts.parser().setSigningKey(ConstantsSecurity.SECRET).parseClaimsJws(token.replace(ConstantsSecurity.TOKEN_PREFIX, "")).getBody().getSubject();
		UserDetails userDetails = userAuthServ.loadUserByUsername(email);
		System.out.println(userDetails.getUsername());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/admin/user")
	public ResponseEntity<PessoaFisica> pessoaFisicaUpdate(@RequestBody @Valid PessoaFisica pessoa)
	{
		return ResponseEntity.ok(pessoaFisicaServ.pessoaFisicaUpdate(pessoa));
	}
	
	@DeleteMapping("/admin/user-{id}")
	public ResponseEntity pessoaFisicaDelete(@PathVariable Long id)
	{
		try
		{
			pessoaFisicaServ.pessoaFisicaDelete(id);
			return ResponseEntity.ok().build();
		}
		catch (Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/admin/user")
	public ResponseEntity<List<PessoaFisica>> pessoaFisicaList()
	{
		return ResponseEntity.ok(pessoaFisicaServ.pessoaFisicaList());
	}
	
	@GetMapping("/app/user-{id}")
	public ResponseEntity<PessoaFisica> pessoaFisicaById(@PathVariable Long id)
	{
		return ResponseEntity.ok(pessoaFisicaServ.pessoaFisicagetById(id));
	}

	@PutMapping("/admin/user-{id}")
	public ResponseEntity liberarCadastro(@PathVariable Long id) throws NotFoundException {
		Optional<PessoaFisica> pessoa = Optional.ofNullable(pessoaFisicaServ.pessoaFisicagetById(id));
		if(pessoa == null) throw new NotFoundException("test");
		pessoa.get().setPessoaStatusCadastro(true);
		pessoaFisicaServ.pessoaFisicaSave(pessoa.get());
		return ResponseEntity.ok().build();
	}

	/*@get
	public boolean isAdmin(@PathVariable Long id)
	{

	}*/
}

