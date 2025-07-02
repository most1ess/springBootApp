package com.example.app.client;

import com.example.app.contact.Contact;
import com.example.app.contact.ContactRequest;
import com.example.app.contact.ContactResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Имплементация сервисного интерфейса, реализовывающая бизнес-логику операций с клиентами.
 * Также обеспечивает связь между репозиторием и контроллером.
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;

    /**
     * Метод, реализующий логику операции нахождения всех клиентов.
     *
     * @return список из объектов типа ClientResponse, хранящих данные о клиентах.
     */
    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::buildClientResponse)
                .collect(Collectors.toList());
    }

    /**
     * Метод, реализующий логику нахождения клиента по указанному ID.
     *
     * @param clientId ID, отправленный пользователем.
     * @return объект типа ClientResponse, хранящий данные о найденном клиенте.
     */
    @NotNull
    @Override
    @Transactional(readOnly = true)
    public ClientResponse findById(@NotNull Long clientId) {
        return repository.findById(clientId)
                .map(this::buildClientResponse)
                .orElseThrow(() -> new EntityNotFoundException("Клиент с ID  " + clientId + " не найден."));

    }

    /**
     * Метод, реализующий логику создания нового клиента в базе данных на основе полученных от пользователя данных.
     *
     * @param request запрос пользователя, хранящий данные о клиенте.
     * @return объект типа ClientResponse, хранящий данные о созданном клиенте.
     */
    @NotNull
    @Override
    @Transactional
    public ClientResponse create(@NotNull ClientRequest request) {
        Client client = buildClientEntity(request);
        return buildClientResponse(repository.save(client));
    }

    /**
     * Метод, реализующий логику обновления данных клиента с указанным ID.
     *
     * @param clientId ID клиента, данные которого нужно обновить.
     * @param request  запрос пользователя с новыми данными о клиенте.
     * @return объект типа ClientResponse, хранящий обновленные данные о клиенте.
     */
    @NotNull
    @Override
    @Transactional
    public ClientResponse update(@NotNull Long clientId, @NotNull ClientRequest request) {
        Client client = repository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Клиент с ID " + clientId + " не найден."));
        updateClient(client, request);
        return buildClientResponse(repository.save(client));
    }

    /**
     * Метод, реализующий логику удаления клиента из базы данных.
     *
     * @param clientId ID клиента, которого нужно удалить.
     */
    @Override
    @Transactional
    public void delete(@NotNull Long clientId) {
        Client client = repository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден."));
        repository.delete(client);
    }

    /**
     * Метод, отвечающий за парсинг объекта сущности клиента в объект типа ClientResponse.
     *
     * @param client объект сущности клиента.
     * @return объект типа ClientResponse, используемый сервером для отправки ответа.
     */
    @NotNull
    private ClientResponse buildClientResponse(@NotNull Client client) {
        return new ClientResponse()
                .setId(client.getClientId())
                .setName(client.getName())
                .setLastName(client.getLastName())
                .setContact(new ContactResponse()
                        .setId(client.getContact().getId())
                        .setPhone(client.getContact().getPhone())
                        .setEmail(client.getContact().getEmail()));
    }

    /**
     * Метод, отвечающий за парсинг объекта запроса клиента в объект сущности клиента.
     *
     * @param request запрос пользователя, хранящий данные о клиенте.
     * @return объект сущности клиента.
     */
    @NotNull
    private Client buildClientEntity(@NotNull ClientRequest request) {
        return new Client()
                .setClientId(request.getId())
                .setName(request.getName())
                .setLastName(request.getLastName())
                .setContact(new Contact()
                        .setId(request.getContact().getId())
                        .setPhone(request.getContact().getPhone())
                        .setEmail(request.getContact().getEmail()));
    }

    /**
     * Метод, заменяющий старые данные о клиенте на новые.
     *
     * @param client  объект клиента, данные которого нужно заменить.
     * @param request запрос с новыми данными о клиенте.
     */
    private void updateClient(@NotNull Client client, @NotNull ClientRequest request) {
        ofNullable(request.getId()).map(client::setClientId);
        ofNullable(request.getName()).map(client::setName);
        ofNullable(request.getLastName()).map(client::setLastName);

        ContactRequest contactRequest = request.getContact();
        if (contactRequest != null) {
            ofNullable(contactRequest.getId()).map(client.getContact()::setId);
            ofNullable(contactRequest.getPhone()).map(client.getContact()::setPhone);
            ofNullable(contactRequest.getEmail()).map(client.getContact()::setEmail);
        }
    }

}
