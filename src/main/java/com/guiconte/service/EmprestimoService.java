package com.guiconte.service;

import com.guiconte.domain.entity.Emprestimo;
import com.guiconte.domain.enums.StatusEmprestimo;
import com.guiconte.dto.EmprestimoDTO;

import java.util.Optional;

public interface EmprestimoService {

    Emprestimo save(EmprestimoDTO emprestimoDTO);

    Optional<Emprestimo> find(Integer cod_emprestimo);

    void updateStatus(Integer cod_eprestimo, StatusEmprestimo statusEmprestimo);

}
