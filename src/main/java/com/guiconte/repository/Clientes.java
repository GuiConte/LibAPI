package com.guiconte.repository;

import com.guiconte.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Integer> {
}
