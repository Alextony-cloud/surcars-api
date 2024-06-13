package io.github.alextony_cloud.surcars.api.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import io.github.alextony_cloud.surcars.api.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UsuarioRepository usuarioRepository;

	public Usuario getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String login = authentication.getName();
		return usuarioRepository.findByLogin(login).map(user -> {
		return user;
		}).orElseThrow(() -> new ObjectNotFoundException("Object not found: " + login));
		
	}
	
}
