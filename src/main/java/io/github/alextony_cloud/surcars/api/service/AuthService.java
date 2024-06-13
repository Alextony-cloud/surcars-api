package io.github.alextony_cloud.surcars.api.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import io.github.alextony_cloud.surcars.api.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UsuarioRepository usuarioRepository;

	public Long getLoggedUserId() {
        // Supondo que você tenha configurado o Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // Aqui, você obtém o ID do usuário logado de alguma forma, por exemplo, pelo username
        return usuarioRepository.findByLogin(userDetails.getUsername()).get().getId();
    }
	
	public Usuario getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String login = authentication.getName();
		return usuarioRepository.findByLogin(login).map(user -> {
		return user;
		}).orElseThrow(() -> new ObjectNotFoundException("Object not found: " + login));
		
	}
	
}
