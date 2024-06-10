package io.github.alextony_cloud.surcars.api.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

private Long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String login;
	private String password;
	private String phone;

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
		this.phone = obj.getPhone();
	}
	
	
}
