package com.github.GuiConte.service.impl;

import com.github.GuiConte.domain.entity.Cliente;
import com.github.GuiConte.domain.repository.Clientes;
import com.github.GuiConte.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        clientesRepository.findById(cod_cliente)
                .map(clienteExistente -> {
                    cliente.setCod_cliente(clienteExistente.getCod_cliente());
                    clientesRepository.save(cliente);
                    return cliente;
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado !")
                );
    }

    @Override
    public void delete(Integer cod_cliente) {
        clientesRepository.findById(cod_cliente)
                .map(clienteExistente -> {
                    clientesRepository.delete(clienteExistente);
                    return clienteExistente;
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado !")
                );
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
