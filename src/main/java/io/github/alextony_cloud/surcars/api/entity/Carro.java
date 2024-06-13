package io.github.alextony_cloud.surcars.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.alextony_cloud.surcars.api.entity.dto.CarroDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carro implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Missing fields")
	@Column(length = 4, nullable = false)
	private Integer year;
	
	@NotBlank(message = "Missing fields")
	@Column(length = 8, nullable = false, unique = true)
	private String licensePlate;
	
	@NotBlank(message = "Missing fields")
	@Column(length = 20, nullable = false)
	private String model;
	
	@NotBlank(message = "Missing fields")
	@Column(nullable = false)
	private String color;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
	
	public Carro(CarroDTO obj) {
		super();
		this.id = obj.getId();
		this.year = obj.getYear();
		this.licensePlate = obj.getLicensePlate();
		this.model = obj.getModel();
		this.color = obj.getColor();
		this.usuario = obj.getUsuario();
	}
	
}
