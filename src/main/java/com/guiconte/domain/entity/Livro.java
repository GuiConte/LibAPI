package com.guiconte.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String titulo;

    @Column(name = "isbn", length = 20)
    private String isbn;

}
