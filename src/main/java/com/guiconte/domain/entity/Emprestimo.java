package com.guiconte.domain.entity;

import com.guiconte.domain.enums.StatusEmprestimo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_emprestimo;

    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    private Cliente cliente;

    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEmprestimo statusEmprestimo;

    @OneToMany(mappedBy = "emprestimo")
    private List<ItemEmprestimo> livros;
}
