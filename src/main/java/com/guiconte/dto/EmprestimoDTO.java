package com.guiconte.dto;

import com.guiconte.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {

    @NotNull(message = "{field.cod-cliente.required}")
    private Integer cod_cliente;

    @NotNull(message = "{field.data-emprestimo.required}")
    private LocalDate data_emprestimo;

    @NotEmptyList(message = "{field.itens-emprestimo.required}")
    private List<ItemEmprestimoDTO> livros;
}
