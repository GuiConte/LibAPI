package com.guiconte.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_emprestimo")
public class ItemEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_item_emprestimo;

    @ManyToOne
    @JoinColumn(name = "cod_emprestimo")
    private Emprestimo emprestimo;

    @ManyToOne
    @JoinColumn(name = "cod_livro")
    private Livro livro;

}
