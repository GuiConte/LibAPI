package com.guiconte.repository;

import com.guiconte.domain.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Livros extends JpaRepository<Livro,Integer> {
}
