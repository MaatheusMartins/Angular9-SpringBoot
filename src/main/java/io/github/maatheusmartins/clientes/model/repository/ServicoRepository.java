package io.github.maatheusmartins.clientes.model.repository;

import io.github.maatheusmartins.clientes.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
