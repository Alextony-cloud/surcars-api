package io.github.alextony_cloud.surcars.api.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import io.github.alextony_cloud.surcars.api.security.JWTAuthenticationFilter;
import io.github.alextony_cloud.surcars.api.security.JWTAuthorizationFilter;
import io.github.alextony_cloud.surcars.api.security.JWTUtil;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final JWTUtil jwtUtil;
	
	private final UserDetailsService userDetailsService;
	
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;

	private static final String[] PUBLIC_MATCHERS = {"/h2-console/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**"
			, "/configuration/security", "/swagger-ui.html", "/webjars/**"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors().and().csrf().disable()
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil, "/signin"))
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))
		.authorizeRequests(authorize -> authorize
				.antMatchers(HttpMethod.GET, "/api/cars").authenticated()
				.antMatchers(HttpMethod.GET, "/api/cars/{id}").authenticated()
				.antMatchers(HttpMethod.GET, "/api/user/me").authenticated()
                .antMatchers(HttpMethod.POST, "/api/cars").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/cars/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/cars/{id}").authenticated()
                .antMatchers(HttpMethod.POST, "/signin").permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().permitAll())
		.exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
