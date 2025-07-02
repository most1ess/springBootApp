package com.example.app.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик ошибок и исключений.
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * Обработчик ошибки 404: Not Found.
     *
     * @param ex объект ошибки.
     * @return сообщение об ошибке.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionResponse notFound(EntityNotFoundException ex) {
        return new ExceptionResponse("Клиент не найден!");
    }

    /**
     * Обработчик ошибки 500: Internal Server Error.
     *
     * @param ex объект ошибки.
     * @return сообщение об ошибке.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponse error(RuntimeException ex) {
        return new ExceptionResponse("Проверьте правильность введенных данных!");
    }
}
