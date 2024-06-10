package io.github.alextony_cloud.surcars.api.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
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
	private LocalDate birthday;
	
	@Column(length = 40, nullable = false, unique = true)
	private String login;
	
	@Column(length = 100, nullable = false, unique = true)
	private String password;
	
	@Column(length = 11, nullable = false, unique = true)
	private String phone;
	
	
	@OneToMany(cascade = CascadeType.ALL) // CascadeType.ALL pode ser ajustado conforme necessário
    @JoinColumn(name = "usuario_id") // Especifica a coluna na tabela de Carro que referencia a tabela de Usuario
	List<Carro> cars = new ArrayList<>();
	
	public void addCars(Carro car) {
		if(cars == null) {
			cars = new ArrayList<>();
		}
		cars.add(car);
	}
}
