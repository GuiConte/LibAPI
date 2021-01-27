package com.guiconte.service;

import com.guiconte.domain.entity.Livro;

import java.util.List;

public interface LivroService {
    Livro save(Livro livro);

    List<Livro> findAll();

    List<Livro> findWithFilter(Livro filter);

    void update(Integer cod_livro, Livro livro);

    void delete(Integer cod_livro);
}
