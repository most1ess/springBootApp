package com.example.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации SwaggerUI.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Client Management API")
                        .version("1.0.0")
                        .description("API для управления клиентами и их контактами.")
                        .contact(new Contact()
                                .name("Максим Беляков")
                                .email("maxcoderrus@gmail.com")
                                .url("https://github.com/most1ess")));
    }
}