package com.guiconte.dto;

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
