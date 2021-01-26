package com.github.GuiConte.service.impl;

import com.github.GuiConte.domain.entity.Cliente;
import com.github.GuiConte.domain.repository.Clientes;
import com.github.GuiConte.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final Clientes clientesRepository;

    @Override
    public Cliente save(Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public void update(Integer cod_cliente, Cliente cliente) {

    }

    @Override
    public void delete(Integer cod_cliente) {

    }

    @Override
    public List<Cliente> findAll() {
        return clientesRepository.findAll();
    }
}
