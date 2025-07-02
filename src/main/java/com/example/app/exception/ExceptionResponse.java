package com.example.app.exception;

import lombok.Data;

/**
 * Класс, содержащий сообщение об ошибке.
 */
@Data
public class ExceptionResponse {
    private final String message;
}
