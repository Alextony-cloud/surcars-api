package io.github.alextony_cloud.surcars.api.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import io.github.alextony_cloud.surcars.security.UserSS;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> user = repository.findByLogin(login);
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getLogin(), user.get().getPassword());
		}
		throw new UsernameNotFoundException(login);
	}

}
