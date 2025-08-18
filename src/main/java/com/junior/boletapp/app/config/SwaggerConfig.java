package com.junior.boletapp.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI juniorAppApiConfig() {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("Junior API")
                .version("1.0.0")
                .description("API para gestionar el equipo Junior de Barranquilla: jugadores, partidos, campeonatos y boletería.")
                .contact(new Contact()
                        .name("Equipo de Desarrollo JuniorApp")
                        .url("jhoan-olivo-dev.co")
                        .email("noj2304@gmail.com"))
                .license(new License()
                        .name("Licencia MIT")
                        .url("https://opensource.org/licenses/MIT")));
    }

}