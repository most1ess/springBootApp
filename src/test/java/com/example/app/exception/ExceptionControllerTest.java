package com.example.app.exception;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирования глобального обработчика ошибок.
 */
@SpringBootTest(classes = ExceptionController.class)
public class ExceptionControllerTest {

    private final ExceptionController controller = new ExceptionController();

    @Test
    void notFoundTest() {
        // Подготовка
        EntityNotFoundException ex = new EntityNotFoundException();

        // Вызов
        ExceptionResponse response = controller.notFound(ex);

        // Проверки
        assertEquals("Клиент не найден!", response.getMessage());
    }

    @Test
    void errorTest() {
        // Подготовка
        RuntimeException ex = new RuntimeException();

        // Вызов
        ExceptionResponse response = controller.error(ex);

        // Проверки
        assertEquals("Проверьте правильность введенных данных!", response.getMessage());
    }
}