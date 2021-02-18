package com.guiconte.controller;

import com.guiconte.domain.entity.Emprestimo;
import com.guiconte.domain.entity.ItemEmprestimo;
import com.guiconte.domain.enums.StatusEmprestimo;
import com.guiconte.dto.AtualizacaoStatusEmprestimoDTO;
import com.guiconte.dto.EmprestimoDTO;
import com.guiconte.dto.InformacaoEmprestimoDTO;
import com.guiconte.dto.InformacaoItemEmprestimoDTO;
import com.guiconte.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = emprestimoService.save(emprestimoDTO);
        return emprestimo.getCod_emprestimo();
    }

    @GetMapping("{cod_emprestimo}")
    public InformacaoEmprestimoDTO find(@PathVariable Integer cod_emprestimo){
        return emprestimoService.find(cod_emprestimo)
                .map( emprestimo -> convertDTO(emprestimo))
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado !"));
    }

    @PatchMapping("{cod_emprestimo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer cod_emprestimo, @RequestBody AtualizacaoStatusEmprestimoDTO dto){
        String novoStatus = dto.getNovo_status();
        emprestimoService.updateStatus(cod_emprestimo, StatusEmprestimo.valueOf(novoStatus));
    }

    public InformacaoEmprestimoDTO convertDTO(Emprestimo emprestimo){
        return InformacaoEmprestimoDTO
                .builder()
                .codigo(emprestimo.getCod_emprestimo())
                .cpf(emprestimo.getCliente().getCpf())
                .cliente(emprestimo.getCliente().getNome())
                .data_emprestimo(emprestimo.getDataEmprestimo())
                .data_devolucao(emprestimo.getDataDevolucao())
                .status(emprestimo.getStatusEmprestimo().name())
                .livros(convertDTO(emprestimo.getLivros()))
                .build();
    }

    public List<InformacaoItemEmprestimoDTO> convertDTO(List<ItemEmprestimo> itemEmprestimo){
        if (CollectionUtils.isEmpty(itemEmprestimo)){
            return Collections.emptyList();
        }
        return itemEmprestimo
                .stream()
                .map(item -> InformacaoItemEmprestimoDTO
                                .builder()
                                .titulo(item.getLivro().getTitulo())
                                .isbn(item.getLivro().getIsbn())
                                .build()
                ).collect(Collectors.toList());
    }


}
