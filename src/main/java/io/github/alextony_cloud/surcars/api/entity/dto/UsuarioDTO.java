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

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Usuario", description = "Representa um usuario")
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "ID do usuário", example = "1")
	private Long id;

	@ApiModelProperty(value = "Nome do usuário", example = "João")
	@NotBlank(message = "Missing fields")
	@Length(min = 3, max = 100, message = "The FIRSTNAME field must be between 3 and 100 characters long")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$", message = "the FIRSTNAME field does not accept numbers or special characters")
	private String firstName;

	@ApiModelProperty(value = "Sobrenome do usuário", example = "Teixeira")
	@NotBlank(message = "Missing fields")
	@Length(min = 3, max = 100, message = "The LASTNAME field must be between 3 and 100 characters long")
	@Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$", message = "the LASTNAME field does not accept numbers or special characters")
	private String lastName;

	@ApiModelProperty(value = "E-mail do usuário", example = "Joao@mail.com")
	@NotBlank(message = "Missing fields")
	@Email(message = "Invalid fields")
	private String email;

	@ApiModelProperty(value = "data de nascimento do usuário", example = "05/09/1994")
	@NotNull(message = "Missing fields")
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;

	@ApiModelProperty(value = "Login do usuário", example = "JoaoxX1")
	@NotBlank(message = "Missing fields")
	private String login;

	@ApiModelProperty(value = "Senha do usuário", example = "124$11@a")
	@NotBlank(message = "Missing fields")
	private String password;

	@ApiModelProperty(value = "Telefone do usuário", example = "81999999999")
	@NotBlank(message = "Missing fields")
	private String phone;

	@ApiModelProperty(value = "Lista de carros do usuário", example = "Ford,Fiat,Honda")
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
