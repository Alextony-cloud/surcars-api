package io.github.alextony_cloud.surcars.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.alextony_cloud.surcars.api.entity.Carro;
import io.github.alextony_cloud.surcars.api.entity.dto.CarroDTO;
import io.github.alextony_cloud.surcars.api.service.CarroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Carros")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cars")
@CrossOrigin("*")
public class CarroController {

	private final CarroService service;
	

	@ApiOperation("Busca todos os carros")
	@GetMapping
	public ResponseEntity<List<CarroDTO>> findAll(){
		List<Carro> list = this.service.findAll();
		List<CarroDTO> listDTO = list.stream().map(obj -> new CarroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation("Busca um carro por ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CarroDTO>findById(@ApiParam(value = "ID de uma carro", example = "1") @PathVariable Long id){
		Carro obj = service.findById(id);
		return ResponseEntity.ok().body(new CarroDTO(obj));
	}

	@ApiOperation("Cria um carro")
	@PostMapping
	public ResponseEntity<CarroDTO> create(@ApiParam(name = "corpo", value = "Representação de um novo carro")  @RequestBody @Valid CarroDTO carroDTO){
		Carro newObj = service.create(carroDTO);
		CarroDTO dto = new CarroDTO(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Atualiza as informações de um carro através do ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CarroDTO> update(@PathVariable @ApiParam(value = "ID de uma carro", example = "1") Long id,
			@ApiParam(name = "corpo", value = "Representação de um carro com os novos dados") @RequestBody @Valid CarroDTO carroDTO){
		Carro newObj = service.update(id, carroDTO);
		return ResponseEntity.ok().body(new CarroDTO(newObj));
	}
	
	@ApiOperation("Deleta um carro através do ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@ApiParam(value = "ID de uma carro", example = "1") @PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
