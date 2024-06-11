package io.github.alextony_cloud.surcars.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import io.github.alextony_cloud.surcars.api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Usuarios")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UsuarioController {

	private final UsuarioService service;
	
	@ApiOperation("Busca todos usuários")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<Usuario> list = this.service.findAll();
		List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation("Busca um usuario por ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO>findById(@ApiParam(value = "ID de um usuario", example = "1") @PathVariable Long id){
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}

	@ApiOperation("Cadastra um usuário")
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@ApiParam(name = "corpo", value = "Representação de um novo usuario") @RequestBody @Valid UsuarioDTO usuarioDTO){
		Usuario newObj = service.create(usuarioDTO);
		UsuarioDTO dto = new UsuarioDTO(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Atualiza um usuario por ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@ApiParam(value = "ID de um usuario", example = "1") @PathVariable Long id, 
			@ApiParam(name = "corpo", value = "Representação de um usuario com os novos dados") @RequestBody @Valid UsuarioDTO usuarioDTO){
		Usuario newObj = service.update(id, usuarioDTO);
		return ResponseEntity.ok().body(new UsuarioDTO(newObj));
	}
	
	@ApiOperation("Deleta um usuario por ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de um usuario", example = "1") @PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
