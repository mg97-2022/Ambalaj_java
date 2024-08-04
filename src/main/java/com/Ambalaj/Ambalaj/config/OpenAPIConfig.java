package com.Ambalaj.Ambalaj.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title("Ambalaj REST APIs").description("Ambalaj REST APIs Documentation").version("v1.0")
                        .contact(new Contact().name("Mohamed Gamal").email("mohamedgamalsaied1997@gmail.com")));
    }
}