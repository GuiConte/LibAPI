package com.guiconte.exception;

public class LivroNotFoundException extends RuntimeException{

    public LivroNotFoundException() {
        super("Livro não encontrado !");
    }
}
