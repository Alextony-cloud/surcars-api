package io.github.alextony_cloud.surcars.api.config.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer{

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("io.github.alextony_cloud.surcars.api.controller"))
				.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Carros", "Gerenciamento de carros"), new Tag("Usuarios", "Gerenciamento de usuários"), new Tag("Usuario Autenticado", "Gerenciamento do usuário autenticado na aplicação"));
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Surcars-api")
				.description("API aberta para gerenciamento e cadastro de usuários e carros")
				.version("1")
				.contact(new Contact("Pitang","https://www.pitang.com/",  "alextonyrodrigues@gmail.com"))
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
