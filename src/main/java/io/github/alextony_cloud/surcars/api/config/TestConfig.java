package io.github.alextony_cloud.surcars.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.alextony_cloud.surcars.api.service.DBService;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class TestConfig {
	
	
	private final DBService dbService;
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}

}
