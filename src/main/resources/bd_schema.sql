CREATE TABLE CLIENTE(
    cod_cliente INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100),
    CPF VARCHAR (11)
);

CREATE TABLE LIVRO(
    cod_livro INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100),
    isbn VARCHAR(20)
)

CREATE TABLE EMPRESTIMO(
    cod_emprestimo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cod_cliente INT REFERENCES CLIENTE(cod_cliente),
    data_emprestimo timestamp,
    data_devolucao timestamp,
    status varchar(20)
)

CREATE TABLE ITEM_EMPRESTIMO(
    cod_item_emprestimo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cod_emprestimo INT REFERENCES EMPRESTIMO(cod_emprestimo),
    cod_livro INT REFERENCES LIVRO(cod_livro)
)