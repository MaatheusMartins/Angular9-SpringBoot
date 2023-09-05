package io.github.maatheusmartins.clientes.model.repository;

import io.github.maatheusmartins.clientes.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
