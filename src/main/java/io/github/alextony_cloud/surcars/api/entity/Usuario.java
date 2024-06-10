package io.github.alextony_cloud.surcars.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String firstName;
	
	@Column(length = 100, nullable = false)
	private String lastName;
	
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private Date birthday;
	
	@Column(length = 40, nullable = false, unique = true)
	private String login;
	
	@Column(length = 100, nullable = false, unique = true)
	private String password;
	
	@Column(length = 11, nullable = false, unique = true)
	private String phone;
	
	@OneToMany
	@JoinColumn(name = "cars_id", referencedColumnName = "id")
	List<Carro> cars = new ArrayList<>();
}
