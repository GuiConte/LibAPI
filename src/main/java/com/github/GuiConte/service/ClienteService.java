package com.github.GuiConte.service;

import com.github.GuiConte.domain.entity.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente save(Cliente cliente);

    void update(Integer cod_cliente,Cliente cliente);

    void delete(Integer cod_cliente);

    List<Cliente> findAll ();
}
