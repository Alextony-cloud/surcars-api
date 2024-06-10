package io.github.alextony_cloud.surcars.api.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.alextony_cloud.surcars.api.entity.dto.UsuarioDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Missing fields")
	@Length(min = 3, max = 100, message = "O campo FIRSTNAME deve ter entre 3 e 100 caracteres")
	@Column(length = 100, nullable = false)
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$")
	private String firstName;
	
	@NotBlank(message = "Missing fields")
	@Length(min = 3, max = 100, message = "O campo LASTNAME deve ter entre 3 e 100 caracteres")
	@Column(length = 100, nullable = false)
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$")
	private String lastName;
	
	@NotBlank(message = "Missing fields")
	@Email(message = "Invalid fields")
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "Missing fields")
	@Past
	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;
	
	@NotBlank(message = "Missing fields")
	@Column(length = 40, nullable = false, unique = true)
	private String login;
	
	@NotBlank(message = "Missing fields")
	@Column(length = 100, nullable = false)
	private String password;
	
	@NotBlank(message = "Missing fields")
	@Column(length = 11, nullable = false, unique = true)
	private String phone;
	
	
	@OneToMany(cascade = CascadeType.ALL) // CascadeType.ALL pode ser ajustado conforme necessário
    @JoinColumn(name = "usuario_id") // Especifica a coluna na tabela de Carro que referencia a tabela de Usuario
	private List<Carro> cars = new ArrayList<>();


	public Usuario(Long id, String firstName, String lastName, String email, LocalDate birthday, String login, String password, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
		this.password = password;
		this.phone = phone;
	}
	
	public Usuario(UsuarioDTO obj) {
		super();
		this.id = obj.getId();
		this.firstName = obj.getFirstName();
		this.lastName = obj.getLastName();
		this.email = obj.getEmail();
		this.birthday = obj.getBirthday();
		this.login = obj.getLogin();
		this.password = obj.getPassword();
		this.phone = obj.getPhone();
	}
}
