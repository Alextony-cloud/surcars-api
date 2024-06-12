package io.github.alextony_cloud.surcars.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.github.alextony_cloud.surcars.api.service.exceptions.HandleAuthenticationException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
	        try {
	            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
	            if (authToken != null) {
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            } else {
	                throw new HandleAuthenticationException("Unauthorized - invalid session");
	            }
	        } catch (HandleAuthenticationException ex) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.setContentType("application/json");
	            String jsonResponse = String.format("{\"errorCode\": \"%d\", \"message\": \"%s\"}", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
	            response.getWriter().write(jsonResponse);
	            return;
	        }
	    }
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.validToken(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails details = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());
		}
		return null;
	}
}
