package com.dev.cruzada.auth.Filters;

public class ConstantsSecurity
{
	public static final String SECRET        = "HashKey";
	public static final String TOKEN_PREFIX  = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL   = "/loginApp";
	public static final long EXPIRATION_TIME = 86400000L;
}
