package com.dev.cruzada.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoded {
	public static void main(String[] args) {
		BCryptPasswordEncoder passEncoded = new BCryptPasswordEncoder();
		System.out.println(passEncoded.encode("123"));
	}
}
