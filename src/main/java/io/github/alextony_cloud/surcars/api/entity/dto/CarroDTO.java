package io.github.alextony_cloud.surcars.api.entity.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.github.alextony_cloud.surcars.api.entity.Carro;
import lombok.Data;

@Data
public class CarroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Missing fields")
	private Integer year;
	
	@NotBlank(message = "Missing fields")
	private String licensePlate;
	
	@NotBlank(message = "Missing fields")
	private String model;
	
	@NotBlank(message = "Missing fields")
	private String color;
	
	@NotNull(message = "the field is required")
	private Long user;

	public CarroDTO() {
		super();
	}

	public CarroDTO(Carro obj) {
		super();
		this.id = obj.getId();
		this.year = obj.getYear();
		this.licensePlate = obj.getLicensePlate();
		this.model = obj.getModel();
		this.color = obj.getColor();
		this.user = obj.getUser().getId();
	}

	
}
