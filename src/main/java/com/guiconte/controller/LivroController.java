package com.guiconte.controller;

import com.guiconte.domain.entity.Livro;
import com.guiconte.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro save(@RequestBody Livro livro){
        return livroService.save(livro);
    }

    @GetMapping("/all")
    public List<Livro> findAll(){
        return livroService.findAll();
    }

    @GetMapping
    public List<Livro> findWithFilter(Livro livro){
        return livroService.findWithFilter(livro);
    }

    @PutMapping("{cod_livro}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable(name = "cod_livro") Integer cod_livro,
                                @RequestBody Livro livro){
        livroService.update(cod_livro,livro);
    }

    @DeleteMapping("{cod_livro}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "cod_livro") Integer cod_livro){
        livroService.delete(cod_livro);
    }

}
