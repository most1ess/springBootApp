package com.example.app.client;

import com.example.app.contact.Contact;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Сущность Клиента, данные которого хранятся в базе данных.
 */
@Entity
@Table(name = "clients")
@Data
@Accessors(chain = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", foreignKey = @ForeignKey(name = "client_contact_id"))
    private Contact contact;
}