package com.example.app.client;

import com.example.app.contact.ContactRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс, содержащий поля с информацией о клиенте, отправленной пользователем.
 */
@Data
@Accessors(chain = true)
public class ClientRequest {
    @Schema(hidden = true)
    private Long id;

    @Schema(description = "имя", example = "Иван")
    private String name;

    @Schema(description = "фамилия", example = "Иванов")
    private String lastName;

    private ContactRequest contact;
}
