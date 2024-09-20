create database Biblioteca;

GRANT ALL PRIVILEGES ON biblioteca.* TO 'root'@'localhost';
FLUSH PRIVILEGES; /*permissoes*/

USE biblioteca;

CREATE TABLE autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor_id INT,
    categoria_id INT,
    FOREIGN KEY (autor_id) REFERENCES autores(id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT,
    usuario_id INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    FOREIGN KEY (livro_id) REFERENCES livros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
select * from autores;

