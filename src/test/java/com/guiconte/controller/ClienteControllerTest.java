package com.guiconte.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guiconte.domain.entity.Cliente;
import com.guiconte.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldSaveCliente() throws Exception {
        Cliente cliente = Cliente.builder().cod_cliente(1).nome("Cliente Teste").cpf("46323334097").build();

        Mockito.when(clienteService.save(Mockito.any(Cliente.class))).thenReturn(cliente);

        mvc.perform( MockMvcRequestBuilders
                .post("/clientes")
                .content(asJsonString(cliente))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cod_cliente").exists());
    }

    @Test
    public void shouldFindAllClientes() throws Exception
    {
        List<Cliente> clienteList =
                Arrays.asList(
                        Cliente.builder().cod_cliente(1).nome("Cliente 1").cpf("11111111111").build(),
                        Cliente.builder().cod_cliente(2).nome("Cliente 2").cpf("22222222222").build(),
                        Cliente.builder().cod_cliente(3).nome("Cliente 3").cpf("33333333333").build()
                );

        Mockito.when(clienteService.findAll()).thenReturn(clienteList);

        mvc.perform( MockMvcRequestBuilders
                .get("/clientes/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].cod_cliente").isNotEmpty());
    }

    @Test
    public void shouldUpdateClientes() throws Exception{
        Cliente cliente = Cliente.builder().cod_cliente(1).nome("Cliente 1").cpf("46323334097").build();

        Mockito.when(clienteService.update(1,cliente)).thenReturn(cliente);

        mvc.perform( MockMvcRequestBuilders
                .put("/clientes/1")
                .content(asJsonString(cliente))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cod_cliente").isNotEmpty());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
