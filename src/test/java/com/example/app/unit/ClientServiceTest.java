package com.example.app.unit;

import com.example.app.client.*;
import com.example.app.contact.Contact;
import com.example.app.contact.ContactRequest;
import com.example.app.contact.ContactResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Класс тестирования сервиса.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientServiceTest {
    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImpl service;

    private final List<Client> testClients = List.of(
            new Client()
                    .setClientId(1L)
                    .setName("Иван")
                    .setLastName("Иванов")
                    .setContact(new Contact()
                            .setId(1L)
                            .setPhone("+11111111111")
                            .setEmail("a@a.a")),
            new Client()
                    .setClientId(2L)
                    .setName("Александр")
                    .setLastName("Александров")
                    .setContact(new Contact()
                            .setId(2L)
                            .setPhone("+22222222222")
                            .setEmail("b@b.b"))
    );

    private final List<ClientResponse> testClientResponses = List.of(
            new ClientResponse()
                    .setId(1L)
                    .setName("Иван")
                    .setLastName("Иванов")
                    .setContact(new ContactResponse()
                            .setId(1L)
                            .setPhone("+11111111111")
                            .setEmail("a@a.a")),
            new ClientResponse()
                    .setId(2L)
                    .setName("Александр")
                    .setLastName("Александров")
                    .setContact(new ContactResponse()
                            .setId(2L)
                            .setPhone("+22222222222")
                            .setEmail("b@b.b"))
    );

    private final ClientRequest testClientRequest = new ClientRequest()
            .setId(3L)
            .setName("Петр")
            .setLastName("Петров")
            .setContact(new ContactRequest()
                    .setId(3L)
                    .setPhone("+33333333333")
                    .setEmail("c@c.c"));

    @Test
    void findAllTest() {
        when(repository.findAll()).thenReturn(testClients);

        List<ClientResponse> result = service.findAll();

        assertThat(result).isEqualTo(testClientResponses);
    }

    @Test
    void findByIdTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(testClients.get(0)));

        ClientResponse result = service.findById(1L);

        assertThat(result).isEqualTo(testClientResponses.get(0));
    }

    @Test
    void findByIdNotFoundTest() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> service.findById(99L))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void createTest() {
        when(repository.save(any(Client.class))).thenReturn(testClients.get(0));

        ClientResponse result = service.create(testClientRequest);

        assertThat(result).isEqualTo(testClientResponses.get(0));
    }

    @Test
    void updateTest() {
        Client updatedClient = new Client()
                .setClientId(2L)
                .setName("Петр")
                .setLastName("Петров")
                .setContact(new Contact()
                        .setId(2L)
                        .setPhone("+33333333333")
                        .setEmail("c@c.c"));

        when(repository.findById(2L)).thenReturn(Optional.of(testClients.get(0)));
        when(repository.save(any(Client.class))).thenReturn(updatedClient);

        ClientResponse result = service.update(2L, testClientRequest);

        assertThat(result).isEqualTo(new ClientResponse()
                .setId(2L)
                .setName("Петр")
                .setLastName("Петров")
                .setContact(new ContactResponse()
                        .setId(2L)
                        .setPhone("+33333333333")
                        .setEmail("c@c.c")));
    }

    @Test
    void updateNotFoundTest() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> service.update(99L, testClientRequest))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void deleteTest() {
        when(repository.findById(2L)).thenReturn(Optional.of(testClients.get(1)));

        service.delete(2L);

        verify(repository).findById(2L);
        verify(repository).delete(testClients.get(1));
    }

    @Test
    void deleteNotFoundTest() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.delete(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Клиент не найден.");

        verify(repository, never()).deleteById(any());
    }
}