package com.guiconte.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_livro;

    @Column(name = "titulo",length = 100)
    @NotEmpty(message = "{field.titulo.required}")
    private String titulo;

    @Column(name = "isbn", length = 20)
    @NotEmpty(message = "{field.isbn.required}")
    private String isbn;

}
