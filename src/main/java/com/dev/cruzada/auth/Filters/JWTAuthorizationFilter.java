package com.dev.cruzada.auth.Filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.dev.cruzada.service.UsuarioAuthService;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
	private final UsuarioAuthService usuarioAuthService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UsuarioAuthService usuarioAuthService) {
		super(authenticationManager);
		this.usuarioAuthService = usuarioAuthService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		String header = request.getHeader(ConstantsSecurity.HEADER_STRING);
		if(header == null || !header.startsWith(ConstantsSecurity.TOKEN_PREFIX))
		{
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest req)
	{
		String token = req.getHeader(ConstantsSecurity.HEADER_STRING);
		if(token == null) return null;
		String email = Jwts.parser().setSigningKey(ConstantsSecurity.SECRET).parseClaimsJws(token.replace(ConstantsSecurity.TOKEN_PREFIX, "")).getBody().getSubject();
		
		UserDetails userDetails = usuarioAuthService.loadUserByUsername(email);
		return email != null ? new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities()) : null;
	}
}

