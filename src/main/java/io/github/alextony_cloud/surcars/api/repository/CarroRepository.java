package io.github.alextony_cloud.surcars.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.alextony_cloud.surcars.api.entity.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
	Optional<Carro> findByLicensePlate(String licensePlate);
}
