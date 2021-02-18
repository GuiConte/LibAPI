package com.guiconte.exception;

public class EmprestimoNotFoundException extends RuntimeException{

    public EmprestimoNotFoundException() {
        super("Emprestimo não encontrado !");
    }
}
