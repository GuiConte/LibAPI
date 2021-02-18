package com.guiconte.controller;

import com.guiconte.domain.entity.Cliente;
import com.guiconte.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping("/all")
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @GetMapping
    public List<Cliente> findWithFilter(Cliente filter){
        return clienteService.findWithFilter(filter);
    }

    @PutMapping("{cod_cliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "cod_cliente") Integer cod_cliente,
                                @RequestBody @Valid Cliente cliente){
        clienteService.update(cod_cliente,cliente);
    }

    @DeleteMapping("{cod_cliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "cod_cliente") Integer cod_cliente){
        clienteService.delete(cod_cliente);
    }


}
