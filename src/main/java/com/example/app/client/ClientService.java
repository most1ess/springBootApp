package com.example.app.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный интерфейс с методами для операций с клиентами.
 */
@Service
public interface ClientService {

    @NotNull
    List<ClientResponse> findAll();

    @NotNull
    ClientResponse findById(@NotNull Long clientId);

    @NotNull
    ClientResponse create(@NotNull ClientRequest request);

    @NotNull
    ClientResponse update(@NotNull Long clientId, @NotNull ClientRequest request);

    void delete(@NotNull Long clientId);
}
