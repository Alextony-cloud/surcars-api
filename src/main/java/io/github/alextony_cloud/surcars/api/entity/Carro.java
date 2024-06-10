package io.github.alextony_cloud.surcars.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
	
}
