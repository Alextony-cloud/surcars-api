package io.github.alextony_cloud.surcars.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.alextony_cloud.surcars.api.entity.dto.CredenciaisDTO;
import io.github.alextony_cloud.surcars.api.service.UsuarioService;
import io.github.alextony_cloud.surcars.api.service.exceptions.BadCredentialsException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		CredenciaisDTO creds = null;
		try {
			creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				creds.getLogin(), creds.getPassword(), new ArrayList<>());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		if (authentication.isAuthenticated()) {
			return authentication;
		}
		throw new BadCredentialsException("Incorrect login or password");
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);

		ApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getServletContext());
		UsuarioService usuarioService = applicationContext.getBean(UsuarioService.class);
		usuarioService.updateLastLogin(username);

		response.setHeader("access-control-expose-headers", "Authorization");
		response.setHeader("Authorization", "Bearer " + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	private CharSequence json() {
		long date = new Date().getTime();
		return "{" + "\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Not authorized\", "
				+ "\"message\": \"Invalid login or password\", " + "\"path\": \"/login\"}";
	}
}
