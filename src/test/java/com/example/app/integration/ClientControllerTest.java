package com.example.app.integration;

import com.example.app.client.*;
import com.example.app.contact.ContactRequest;
import com.example.app.contact.ContactResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Класс тестирования контроллера.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientControllerTest {
    @Mock
    private ClientServiceImpl service;

    @InjectMocks
    private ClientController controller;

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
        when(service.findAll()).thenReturn(testClientResponses);

        List<ClientResponse> result = controller.findAll();

        assertThat(result).isEqualTo(testClientResponses);
    }

    @Test
    void findByIdTest() {
        when(service.findById(1L)).thenReturn(testClientResponses.get(0));

        ClientResponse result = controller.findById(1L);

        assertThat(result).isEqualTo(testClientResponses.get(0));
    }

    @Test
    void createTest() {
        when(service.create(any(ClientRequest.class))).thenReturn(testClientResponses.get(0));

        ClientResponse result = controller.create(testClientRequest);

        assertThat(result).isEqualTo(testClientResponses.get(0));
    }

    @Test
    void updateTest() {
        ClientResponse updatedClientResponse = new ClientResponse()
                .setId(2L)
                .setName("Петр")
                .setLastName("Петров")
                .setContact(new ContactResponse()
                        .setId(2L)
                        .setPhone("+33333333333")
                        .setEmail("c@c.c"));

        when(service.update(eq(2L), any(ClientRequest.class))).thenReturn(updatedClientResponse);

        ClientResponse result = controller.update(2L, testClientRequest);

        assertThat(result).isEqualTo(updatedClientResponse);
    }

    @Test
    void deleteTest() {
        controller.delete(1L);

        verify(service).delete(1L);
    }
}