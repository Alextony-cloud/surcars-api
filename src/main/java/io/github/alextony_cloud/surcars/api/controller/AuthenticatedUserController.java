package io.github.alextony_cloud.surcars.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UserResponseDTO;
import io.github.alextony_cloud.surcars.api.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Usuario Autenticado")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/me")
@CrossOrigin("*")
public class AuthenticatedUserController {

	private final AuthService authService;

	@ApiOperation("Detalha as informações do usuário logado")
	@GetMapping
	public ResponseEntity<UserResponseDTO>  getUserDetails() {
		Usuario usuario = authService.getAuthenticatedUser();
		return ResponseEntity.ok().body(new UserResponseDTO(usuario));
		
	}
	
	
}
