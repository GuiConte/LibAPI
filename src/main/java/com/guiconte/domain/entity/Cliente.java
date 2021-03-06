package com.guiconte.domain.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente")
    private Integer cod_cliente;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "{field.nome.required}")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{field.cpf.required}")
    @CPF(message = "{field.cpf.invalid}")
    private String cpf;

}
