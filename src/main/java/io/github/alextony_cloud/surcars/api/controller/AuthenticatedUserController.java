package io.github.alextony_cloud.surcars.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.entity.dto.UserResponseDTO;
import io.github.alextony_cloud.surcars.api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Usuario Autenticado")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/me")
@CrossOrigin("*")
public class AuthenticatedUserController {

	private final UsuarioService usuarioService;

	@ApiOperation("Detalha as informações do usuário logado")
	@GetMapping
	public UserResponseDTO getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String login = authentication.getName();
		Usuario usuario = usuarioService.findByLogin(login);
		
		return new UserResponseDTO(usuario);
		
	}
}
