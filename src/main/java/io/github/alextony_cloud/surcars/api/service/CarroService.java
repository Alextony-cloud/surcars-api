package io.github.alextony_cloud.surcars.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Carro;
import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.CarroDTO;
import io.github.alextony_cloud.surcars.api.repository.CarroRepository;
import io.github.alextony_cloud.surcars.api.service.exceptions.DataIntegrityViolationException;
import io.github.alextony_cloud.surcars.api.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarroService {

	private final CarroRepository repository;
	private final UsuarioService usuarioService;
	
	

	public List<Carro> findAll() {
		return repository.findAll();
	}

	public Carro findById(Long id) {
		Optional<Carro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
	}

	public Carro create(CarroDTO carroDTO) {
		carroDTO.setId(null);
		return repository.save(newCar(carroDTO));
	}

	private Carro newCar(CarroDTO carroDTO) {
		Usuario user = usuarioService.findById(carroDTO.getUser());
		
		Carro carro = new Carro();
		if(carroDTO.getId() != null) {
			carro.setId(carroDTO.getId());
		}
		validByLicencePlate(carroDTO);
		carro.setUser(user);
		carro.setYear(carroDTO.getYear());
		carro.setColor(carroDTO.getColor());
		carro.setLicensePlate(carroDTO.getLicensePlate());
		carro.setModel(carroDTO.getModel());
		return carro;
	}

	public Carro update(Long id, CarroDTO carroDTO) {
		carroDTO.setId(id);
		Carro oldCar = findById(id);
		oldCar = newCar(carroDTO); 
		return repository.save(oldCar);
	}


	public void delete(Long id) {
		repository.findById(id).map(Carro -> {
			repository.delete(Carro);
			return Carro;
		}).orElseThrow(() -> new ObjectNotFoundException("Object not found: " + id));
	}

	private void validByLicencePlate(CarroDTO carroDTO) {
		Optional<Carro> obj = repository.findByLicensePlate(carroDTO.getLicensePlate());
		if (obj.isPresent() && obj.get().getId() != carroDTO.getId()) {
			throw new DataIntegrityViolationException("License plate already exists");
		}
	}
}
