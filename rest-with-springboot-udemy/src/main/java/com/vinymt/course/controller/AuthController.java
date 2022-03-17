package com.vinymt.course.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinymt.course.repository.UserRepository;
import com.vinymt.course.security.AccountCredentialsVO;
import com.vinymt.course.security.jwt.JwtTokenProvider;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository repo;

	@ApiOperation(value="Authenticate a user by credentials")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Map<Object, Object>> create(@RequestBody AccountCredentialsVO data) throws Exception {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			var user = repo.findByUsername(username);
			var token = "";
			
			if(user != null) {
				token = jwtTokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username: " + username + " not found!");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			
			return ok(model);
		} catch(AuthenticationException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid username or password");
		}
	}
}
