package com.example.app.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Сущность контактов клиента.
 */
@Entity
@Table(name = "contacts")
@Data
@Accessors(chain = true)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;
}
