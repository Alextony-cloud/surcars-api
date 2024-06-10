package io.github.alextony_cloud.surcars.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(length = 4, nullable = false)
	private Integer year;
	
	@Column(length = 8, nullable = false)
	private String licensePlate;
	
	@Column(length = 20, nullable = false)
	private String model;
	
	@Column(nullable = false)
	private String color;
	
}
