package com.guiconte.service.impl;

import com.guiconte.domain.entity.Livro;
import com.guiconte.repository.Livros;
import com.guiconte.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService {

    private final Livros livroRepository;

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    public List<Livro> findWithFilter(Livro filter) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter,matcher);
        return livroRepository.findAll(example);
    }

    @Override
    public void update(Integer cod_livro, Livro livro) {
        livroRepository.findById(cod_livro)
                .map(livroExistente -> {
                    livro.setCod_livro(livroExistente.getCod_livro());
                    livroRepository.save(livro);
                    return livro;
                })
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro não encontrado !")
                );
    }

    @Override
    public void delete(Integer cod_livro) {
        livroRepository.findById(cod_livro)
                .map(livroExistente -> {
                    livroRepository.delete(livroExistente);
                    return livroExistente;
                })
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro não encontrado !")
                );
    }
}
