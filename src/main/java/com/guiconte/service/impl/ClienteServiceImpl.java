package com.guiconte.service.impl;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.exception.ClienteNotFoundException;
import com.guiconte.repository.Clientes;
import com.guiconte.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public Cliente update(Integer cod_cliente, Cliente cliente) {
        return clientesRepository.findById(cod_cliente)
                .map(clienteExistente -> {
                    cliente.setCod_cliente(clienteExistente.getCod_cliente());
                    clientesRepository.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ClienteNotFoundException());
    }

    @Override
    public void delete(Integer cod_cliente) {
        clientesRepository.findById(cod_cliente)
                .map(clienteExistente -> {
                    clientesRepository.delete(clienteExistente);
                    return clienteExistente;
                })
                .orElseThrow(() -> new ClienteNotFoundException());
    }

    @Override
    public List<Cliente> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public List<Cliente> findWithFilter(Cliente filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter,matcher);
        return clientesRepository.findAll(example);
    }
}
