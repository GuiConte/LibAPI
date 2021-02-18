package com.guiconte.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacaoEmprestimoDTO {

    private Integer codigo;
    private String cpf;
    private String cliente;
    private LocalDate data_emprestimo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate data_devolucao;
    private String status;
    private List<InformacaoItemEmprestimoDTO> livros;


}
