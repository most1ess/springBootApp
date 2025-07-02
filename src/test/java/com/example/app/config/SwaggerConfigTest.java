package com.example.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Класс тестирования правильной загрузки SwaggerConfig.
 */
@SpringBootTest(classes = SwaggerConfig.class)
public class SwaggerConfigTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private OpenAPI customOpenAPI;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void openApiExistenceTest() {
        assertThat(customOpenAPI).isNotNull();
    }

    @Test
    void apiInfoTest() {
        Info info = customOpenAPI.getInfo();

        assertThat(info.getTitle()).isEqualTo("Client Management API");
        assertThat(info.getVersion()).isEqualTo("1.0.0");
        assertThat(info.getDescription()).isEqualTo("API для управления клиентами и их контактами.");

        Contact contact = info.getContact();
        assertThat(contact.getName()).isEqualTo("Максим Беляков");
        assertThat(contact.getEmail()).isEqualTo("maxcoderrus@gmail.com");
        assertThat(contact.getUrl()).isEqualTo("https://github.com/most1ess");
    }
}
