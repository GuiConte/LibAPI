package com.guiconte.repository;

import com.guiconte.domain.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Emprestimos extends JpaRepository<Emprestimo,Integer> {
}
