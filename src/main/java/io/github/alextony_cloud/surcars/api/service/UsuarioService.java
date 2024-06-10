package io.github.alextony_cloud.surcars.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
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

	public Usuario create(Usuario usuario) {
		usuario.setId(null);
		return repository.save(usuario);
	}

	public Usuario update(Long id, UsuarioDTO usuarioDTO) {
		Usuario obj = findById(id);
		obj.setFirstName(usuarioDTO.getFirstName());
		obj.setLastName(usuarioDTO.getLastName());
		obj.setEmail(usuarioDTO.getEmail());
		obj.setBirthday(usuarioDTO.getBirthday());
		obj.setLogin(usuarioDTO.getLogin());
		obj.setPassword(usuarioDTO.getPassword());
		obj.setPhone(usuarioDTO.getPhone());
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.findById(id).map(usuario -> {
			if(usuario.getCars().size() > 0) throw new DataIntegrityViolationException("Cliente possui carros associados e não pode ser deletado!");
			repository.delete(usuario);
			return usuario;
		}).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " + id));
	}

}
