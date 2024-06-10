package io.github.alextony_cloud.surcars.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import io.github.alextony_cloud.surcars.api.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/users")
public class UsuarioController {

	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<Usuario> list = this.service.findAll();
		List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO>findById(@PathVariable Long id){
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
		Usuario newObj = service.create(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	
	
}
