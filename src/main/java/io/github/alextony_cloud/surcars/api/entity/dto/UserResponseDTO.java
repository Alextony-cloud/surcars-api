package io.github.alextony_cloud.surcars.api.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "UsuarioLogado", description = "Representa um usuario Logado")
public class UserResponseDTO {
    
	private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String login;
    private String phone;
    private List<Long> cars;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
	
    
    public UserResponseDTO(Usuario obj) {
		super();
		this.firstName = obj.getFirstName();
		this.lastName = obj.getLastName();
		this.email = obj.getEmail();
		this.birthday = obj.getBirthday();
		this.login = obj.getLogin();
		this.phone = obj.getPhone();
		this.cars = obj.getCars().stream().map(x -> x.getId()).collect(Collectors.toList());
		this.createdAt = obj.getCreatedAt();
		this.lastLogin = obj.getLastLogin();
	}
    
    
}