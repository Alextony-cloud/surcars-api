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

import io.github.alextony_cloud.surcars.api.entity.Carro;
import io.github.alextony_cloud.surcars.api.entity.dto.CarroDTO;
import io.github.alextony_cloud.surcars.api.service.CarroService;

@RestController
@RequestMapping(value = "/api/cars")
public class CarroController {

	private CarroService service;
	
	public CarroController(CarroService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<CarroDTO>> findAll(){
		List<Carro> list = this.service.findAll();
		List<CarroDTO> listDTO = list.stream().map(obj -> new CarroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CarroDTO>findById(@PathVariable Long id){
		Carro obj = service.findById(id);
		return ResponseEntity.ok().body(new CarroDTO(obj));
	}

	@PostMapping
	public ResponseEntity<CarroDTO> create(@RequestBody @Valid CarroDTO carroDTO){
		Carro newObj = service.create(carroDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CarroDTO> update(@PathVariable Long id, @RequestBody @Valid CarroDTO carroDTO){
		Carro newObj = service.update(id, carroDTO);
		return ResponseEntity.ok().body(new CarroDTO(newObj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
