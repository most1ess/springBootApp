package com.example.app.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * RestController для операций с клиентами.
 */
@Tag(name = "Клиенты", description = "Управление клиентской базой")
@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    /**
     * Метод, возвращающий список из всех клиентов, хранящихся в базе данных.
     *
     * @return список объектов типа ClientResponse, хранящих данные о клиентах.
     */
    @Operation(summary = "Вывод всех клиентов", description = "Возвращает список всех клиентов")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ClientResponse> findAll() {
        return service.findAll();
    }

    /**
     * Метод, ищущий клиента в базе данных по его ID.
     *
     * @param clientId ID нужного клиента.
     * @return объект типа ClientResponse, хранящий данные о клиенте.
     */
    @Operation(summary = "Поиск по ID", description = "Возвращает клиента с указанным ID")
    @GetMapping(value = "/{client_id}", produces = APPLICATION_JSON_VALUE)
    public ClientResponse findById(
            @Parameter(description = "ID клиента", example = "1", required = true)
            @PathVariable("client_id") Long clientId) {
        return service.findById(clientId);
    }

    /**
     * Метод, создающий нового клиента в базе данных.
     *
     * @param request запрос пользователя, хранящий данные о клиенте.
     * @return объект типа ClientResponse, хранящий данные о клиенте.
     */
    @Operation(summary = "Создать", description = "Создает клиента и записывает его в БД")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ClientResponse create(@RequestBody ClientRequest request) {
        return service.create(request);
    }

    /**
     * Метод, обновляющий данные о клиенте с указанным ID.
     *
     * @param clientId ID клиента, данные которого нужно обновить.
     * @param request  запрос пользователя, хранящий обновленные данные.
     * @return объект типа ClientResponse, хранящий обновленные данные о клиенте.
     */
    @Operation(summary = "Обновить", description = "Обновляет данные о клиенте с указанным ID")
    @PatchMapping(value = "/{client_id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ClientResponse update(
            @Parameter(description = "ID клиента", example = "1", required = true)
            @PathVariable("client_id") Long clientId,
            @RequestBody ClientRequest request) {

        return service.update(clientId, request);
    }

    /**
     * Метод, удаляющий клиента с указанным ID из базы данных.
     *
     * @param clientId ID клиента, которого нужно удалить.
     * @return ошибка удаления, либо информация об успешном удалении.
     */
    @Operation(summary = "Удалить", description = "Удаляет клиента с указанным ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Клиент удалён."),
            @ApiResponse(responseCode = "404", description = "Клиент не найден.")
    })
    @DeleteMapping(value = "/{client_id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(
            @Parameter(description = "ID клиента", example = "1", required = true)
            @PathVariable("client_id") Long clientId) {

        service.delete(clientId);

        return ResponseEntity.ok("Клиент с ID " + clientId + "успешно удалён.");
    }
}
