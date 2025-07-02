package com.example.app.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс, содержащий поля с информацией о контактах клиента, отправленной сервером.
 */
@Data
@Accessors(chain = true)
public class ContactResponse {
    @Schema(description = "ID контактов", example = "0")
    private Long id;

    @Schema(description = "номер телефона", example = "+77777777777")
    private String phone;

    @Email
    @Schema(description = "e-mail", example = "something@site.com")
    private String email;
}
