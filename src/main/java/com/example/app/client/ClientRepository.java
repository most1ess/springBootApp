package com.example.app.client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для обращения к базе данных и вызова CRUD-методов.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
