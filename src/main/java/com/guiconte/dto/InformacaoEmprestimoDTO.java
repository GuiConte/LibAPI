package com.guiconte.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacaoEmprestimoDTO {

    private Integer codigo;
    private String cpf;
    private String cliente;
    private String data_emprestimo;
    private String data_devolucao;
    private String status;
    private List<InformacaoItemEmprestimoDTO> livros;


}
