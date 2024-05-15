package com.srawj.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srawj.app.entity.AppUser;
import com.srawj.app.security.AppUserDetailsService;
import com.srawj.app.security.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;

    @Autowired
    private AppUserDetailsService userDetailsService;

	@PostMapping
	public ResponseEntity<String> authenticate(@RequestBody @Valid AppUser appUser) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					appUser.getEmail(), appUser.getPassword()));
		} catch (Exception e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(appUser.getEmail());

		return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
	}
}
