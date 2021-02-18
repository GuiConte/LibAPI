package com.guiconte.exception;

public class EmprestimoWithoutItemsException extends RuntimeException{

    public EmprestimoWithoutItemsException() {
        super("O Emprestimo precisa conter pelo menos um livro!");
    }
}
