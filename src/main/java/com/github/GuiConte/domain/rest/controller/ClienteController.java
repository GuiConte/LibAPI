package com.github.GuiConte.domain.rest.controller;

import com.github.GuiConte.domain.entity.Cliente;
import com.github.GuiConte.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @PutMapping("{cod_cliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "cod_cliente") Integer cod_cliente,
                                @RequestBody Cliente cliente){

    }


}
