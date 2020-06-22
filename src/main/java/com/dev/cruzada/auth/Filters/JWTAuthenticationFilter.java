	package com.dev.cruzada.auth.Filters;

	import java.io.IOException;
	import java.util.Date;

	import javax.servlet.FilterChain;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import com.dev.cruzada.service.UsuarioAuthService;
	import javassist.NotFoundException;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.AuthenticationException;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

	import com.dev.cruzada.domain.PessoaFisica;
	import com.fasterxml.jackson.databind.ObjectMapper;

	import io.jsonwebtoken.Jwts;
	import io.jsonwebtoken.SignatureAlgorithm;

	public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
	{

		private final AuthenticationManager authManager;

		private final UsuarioAuthService usuarioAuthServ;

		public JWTAuthenticationFilter(AuthenticationManager authManager, UsuarioAuthService usuarioAuthServ) {
			this.authManager = authManager;
			this.usuarioAuthServ = usuarioAuthServ;
		}

		@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, UsernameNotFoundException
		{
			try
			{
				PessoaFisica pessoa = new ObjectMapper().readValue(request.getInputStream(), PessoaFisica.class);
				if(!cadastroValido(pessoa.getPessoaEmail())) throw new RuntimeException("Seu cadastro esta pendente.");
				return this.authManager.authenticate(new UsernamePasswordAuthenticationToken(pessoa.getPessoaEmail(), pessoa.getPessoaSenha()));
			}
			catch (IOException | NotFoundException e)
			{
				throw new RuntimeException("Erro na autenticação");
			}

		}

		@Override
		protected void successfulAuthentication(HttpServletRequest request,
				HttpServletResponse response, FilterChain chain, Authentication authResult)
				throws IOException, ServletException
		{
			String userName = ((User) authResult.getPrincipal()).getUsername();
			String token = Jwts.builder().setSubject(userName)
					.setExpiration(new Date(System.currentTimeMillis() + ConstantsSecurity.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, ConstantsSecurity.SECRET)
					.compact();
			response.addHeader(ConstantsSecurity.HEADER_STRING, ConstantsSecurity.TOKEN_PREFIX + token);
			try
			{
				response.getOutputStream().print(token);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		public boolean cadastroValido(String email) throws NotFoundException {
			boolean valido = this.usuarioAuthServ.pessoaLiberada(email);
			if(valido) return true;
			return false;
		}

	}
