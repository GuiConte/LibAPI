package com.guiconte.controller;

import com.guiconte.dto.AtualizacaoStatusEmprestimoDTO;
import com.guiconte.dto.EmprestimoDTO;
import com.guiconte.dto.InformacaoEmprestimoDTO;
import com.guiconte.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoEmprestimoDTO save(@RequestBody @Valid EmprestimoDTO emprestimoDTO){
        return emprestimoService.save(emprestimoDTO);
    }

    @GetMapping("{cod_emprestimo}")
    public InformacaoEmprestimoDTO find(@PathVariable Integer cod_emprestimo){
        return emprestimoService.find(cod_emprestimo);
    }

    @GetMapping("/all")
    public List<InformacaoEmprestimoDTO> findAll(){
        return emprestimoService.findAll();
    }

    @PatchMapping("{cod_emprestimo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer cod_emprestimo,
                             @RequestBody @Valid AtualizacaoStatusEmprestimoDTO dto){
        emprestimoService.updateStatus(cod_emprestimo, dto.getNovo_status());
    }

}
