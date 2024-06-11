package io.github.alextony_cloud.surcars.api.entity.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.github.alextony_cloud.surcars.api.entity.Carro;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Carro", description = "Representa um carro")
public class CarroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty(value = "ID do carro", example = "1")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(value = "Ano de fabricação carro", example = "2024")
	@NotNull(message = "Missing fields")
	private Integer year;
	
	@ApiModelProperty(value = "Placa do carro", example = "PDV-0625")
	@NotBlank(message = "Missing fields")
	private String licensePlate;
	
	@ApiModelProperty(value = "Modelo do carro", example = "FIAT")
	@NotBlank(message = "Missing fields")
	private String model;
	
	@ApiModelProperty(value = "Cor predominante do carro", example = "Azul")
	@NotBlank(message = "Missing fields")
	private String color;
	
	@ApiModelProperty(value = "Usuario que cadastrou o carro", example = "Paulo")
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
