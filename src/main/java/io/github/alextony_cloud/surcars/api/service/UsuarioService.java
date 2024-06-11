package io.github.alextony_cloud.surcars.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import io.github.alextony_cloud.surcars.api.service.exceptions.DataIntegrityViolationException;
import io.github.alextony_cloud.surcars.api.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final BCryptPasswordEncoder encoder;

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
	}

	public Usuario create(UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);
		usuarioDTO.setPassword(encoder.encode(usuarioDTO.getPassword()));
		validByEmailAndLogin(usuarioDTO);
		Usuario newUsuario = new Usuario(usuarioDTO);
		return repository.save(newUsuario);
	}

	public Usuario update(Long id, UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(id);
		usuarioDTO.setPassword(encoder.encode(usuarioDTO.getPassword()));
		Usuario oldUsuario = findById(id);
		validByEmailAndLogin(usuarioDTO);
		oldUsuario = new Usuario(usuarioDTO);
		return repository.save(oldUsuario);
	}

	public void delete(Long id) {
		repository.findById(id).map(usuario -> {
			repository.delete(usuario);
			return usuario;
		}).orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
	}

	private void validByEmailAndLogin(UsuarioDTO usuarioDTO) {
		Optional<Usuario> obj = repository.findByEmail(usuarioDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException("Email already exists");
		}
		obj = repository.findByLogin(usuarioDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException("Login already exists");
		}
	}

	public Usuario findByLogin(String login) {
		Optional<Usuario> usuario = repository.findByLogin(login);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("User not found: " + login));
	}

	@Transactional
	public void updateLastLogin(String login) {
		Usuario usuario = findByLogin(login);
		usuario.setLastLogin(LocalDateTime.now());
		repository.save(usuario);
	}
}
