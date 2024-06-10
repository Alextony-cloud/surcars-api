package io.github.alextony_cloud.surcars.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;

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
		if(obj.isPresent()) {
			return obj.get();
		} else {
			return null;
		}
	}

}
