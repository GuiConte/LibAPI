package com.guiconte.repository;

import com.guiconte.domain.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Emprestimos extends JpaRepository<Emprestimo,Integer> {

    @Query("select e from Emprestimo e left join fetch e.livros where e.cod_emprestimo = :cod_emprestimo")
    Optional<Emprestimo> findByIdFetchLivros(@Param("cod_emprestimo") Integer cod_emprestimo);

}
