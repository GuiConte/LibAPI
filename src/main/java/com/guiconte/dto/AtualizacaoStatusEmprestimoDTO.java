package com.guiconte.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AtualizacaoStatusEmprestimoDTO {

    @NotEmpty(message = "{field.novo-status-emprestimo.required}")
    private String novo_status;
}
