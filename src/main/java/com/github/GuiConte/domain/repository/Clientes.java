package com.github.GuiConte.domain.repository;

import com.github.GuiConte.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clientes extends JpaRepository<Cliente, Integer> {
}
