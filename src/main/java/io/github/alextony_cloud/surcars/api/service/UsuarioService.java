package io.github.alextony_cloud.surcars.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import io.github.alextony_cloud.surcars.api.service.exceptions.DataIntegrityViolationException;
import io.github.alextony_cloud.surcars.api.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

	public Usuario create(UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);
		validByEmailAndLogin(usuarioDTO);
		Usuario newUsuario = new Usuario(usuarioDTO);
		return repository.save(newUsuario);
	}

	public Usuario update(Long id, UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(id);
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
		if(obj.isPresent() && obj.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException("Login already exists");
		}
	}
}
