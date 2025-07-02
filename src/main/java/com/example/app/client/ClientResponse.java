package com.example.app.client;

import com.example.app.contact.ContactResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс, содержащий поля с информацией о клиенте, отправленной сервером.
 */
@Data
@Accessors(chain = true)
public class ClientResponse {
    @Schema(description = "ID", example = "0")
    private Long id;

    @Schema(description = "Имя", example = "Иван")
    private String name;

    @Schema(description = "фамилия", example = "Иванов")
    private String lastName;

    private ContactResponse contact;
}
