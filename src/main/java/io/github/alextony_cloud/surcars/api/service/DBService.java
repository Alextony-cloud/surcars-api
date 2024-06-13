package io.github.alextony_cloud.surcars.api.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.alextony_cloud.surcars.api.entity.Carro;
import io.github.alextony_cloud.surcars.api.entity.Usuario;
import io.github.alextony_cloud.surcars.api.repository.CarroRepository;
import io.github.alextony_cloud.surcars.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DBService {

	private final UsuarioRepository usuarioRepository;
	private final CarroRepository carroRepository;
	private final BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Usuario u1 = new Usuario(null,"Paulo","Abrão","paulo@mail.com",LocalDate.parse("05/08/1993",formatter),"pauloX",encoder.encode("123"),"81009666894");
		Carro car1 = new Carro(null,2014,"PDV-1020","Ford","Azul Marinho", u1);
		
		Carro car2 = new Carro(null,2024,"PDV-1031","Fiat","Preto", u1);
		
	//	u1.getCars().addAll(Arrays.asList(car1, car2));
		
		usuarioRepository.saveAll(Arrays.asList(u1));
		carroRepository.saveAll(Arrays.asList(car1,car2));
		
	}
}
