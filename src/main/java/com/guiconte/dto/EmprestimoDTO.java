package com.guiconte.dto;

import com.guiconte.domain.entity.ItemEmprestimo;
import com.guiconte.domain.enums.StatusEmprestimo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {

    private Integer cod_cliente;

    private LocalDate data_emprestimo;

    private List<ItemEmprestimoDTO> livros;
}
