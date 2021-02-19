package com.guiconte.service;

import com.guiconte.dto.EmprestimoDTO;
import com.guiconte.dto.InformacaoEmprestimoDTO;

import java.util.List;

public interface EmprestimoService {

    InformacaoEmprestimoDTO save(EmprestimoDTO emprestimoDTO);

    InformacaoEmprestimoDTO find(Integer cod_emprestimo);

    List<InformacaoEmprestimoDTO> findAll();

    void updateStatus(Integer cod_eprestimo, String status);

}
