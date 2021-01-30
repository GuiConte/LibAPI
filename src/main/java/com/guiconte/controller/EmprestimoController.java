package com.guiconte.controller;

import com.guiconte.domain.entity.Emprestimo;
import com.guiconte.domain.enums.StatusEmprestimo;
import com.guiconte.dto.AtualizacaoStatusEmprestimoDTO;
import com.guiconte.dto.EmprestimoDTO;
import com.guiconte.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("{cod_emprestimo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer cod_emprestimo, @RequestBody AtualizacaoStatusEmprestimoDTO dto){
        String novoStatus = dto.getNovoStatus();
        emprestimoService.updateStatus(cod_emprestimo, StatusEmprestimo.valueOf(novoStatus));
    }


}
