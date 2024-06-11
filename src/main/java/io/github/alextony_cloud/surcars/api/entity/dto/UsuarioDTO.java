package io.github.alextony_cloud.surcars.api.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotBlank(message = "Missing fields")
	@Length(min = 3, max = 100, message = "The FIRSTNAME field must be between 3 and 100 characters long")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$", message = "the FIRSTNAME field does not accept numbers or special characters")
	private String firstName;

	@NotBlank(message = "Missing fields")
	@Length(min = 3, max = 100, message = "The LASTNAME field must be between 3 and 100 characters long")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$", message = "the LASTNAME field does not accept numbers or special characters")
	private String lastName;

	@NotBlank(message = "Missing fields")
	@Email(message = "Invalid fields")
	private String email;

	@NotNull(message = "Missing fields")
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;

	@NotBlank(message = "Missing fields")
	private String login;

	@JsonIgnore
	@NotBlank(message = "Missing fields")
	private String password;

	@NotBlank(message = "Missing fields")
	private String phone;

	private List<Long> cars;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Usuario obj) {
		super();
		this.id = obj.getId();
		this.firstName = obj.getFirstName();
		this.lastName = obj.getLastName();
		this.email = obj.getEmail();
		this.birthday = obj.getBirthday();
		this.login = obj.getLogin();
		this.password = obj.getPassword();
		this.phone = obj.getPhone();
		this.cars = obj.getCars().stream().map(x -> x.getId()).collect(Collectors.toList());
	}
}
