/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bibliotecabanco;


/**
 *
 * @author kesia.viana
 */
import DAO.AutoresDAO;
import DAO.LivrosDAO;
import DAO.UsuariosDAO;
import DAO.EditoraDAO;
import DAO.CategoriasDAO;
import DAO.EmprestimosDAO;
import Sistema.Autores;
import Sistema.Livros;
import Sistema.Usuarios;
import Sistema.Editora;
import Sistema.Categorias;
import Sistema.Emprestimos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BibliotecaBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca"; // URL correta do banco de dados
    private static final String USUARIO = "root"; // Nome de usuário do MySQL
    private static final String SENHA = "c@tolic@"; // Senha do MySQL

    public static void main(String[] args) {
        Connection conexao = null;

        // Estabelecendo a conexão
        conexao = getConnection();
        System.out.println("Conexão estabelecida com sucesso!");
        // Operando usuários
        UsuariosDAO usuarioDAO = new UsuariosDAO(conexao);
        Usuarios usuario = new Usuarios();
        usuario.setCpf("123.456.789-1");
        usuario.setNome("Ana");
        usuario.setEmail("anasilva@gmail.com");
        java.sql.Date dataNascimento = Date.valueOf("2000-08-02");
        usuario.setData_nascimento(dataNascimento);
        usuario.setEndereco("rua flor");
        usuarioDAO.inserir(usuario);
        // Listando usuários
        List<Usuarios> usuarios = usuarioDAO.consultar();
        System.out.println("Listando usuários:");
        for (Usuarios u : usuarios) {
            System.out.println("CPF: " + u.getCpf() + ", Nome: " + u.getNome() + ", Email: " + u.getEmail() +
                    ", Data de Nascimento: " + u.getData_nascimento() + ", Endereço: " + u.getEndereco());
        }
        // Operações com autores
        AutoresDAO autorDAO = new AutoresDAO(conexao);
        Autores autor = new Autores();
        autor.setNome("J.R.R. Tolkien");
        autor.setNacionalidade("Britânico");
        autor.setSexo("M");
        autorDAO.inserir(autor);
        // Listar autores
        List<Autores> autores = autorDAO.consultar();
        System.out.println("Listando autores:");
        for (Autores a : autores) {
            System.out.println("Autor: " + a.getNome() + ", Nacionalidade: " + a.getNacionalidade() + ", Sexo: " + a.getSexo());
        }
        // Operações com editoras
        EditoraDAO editoraDAO = new EditoraDAO(conexao);
        Editora editora = new Editora();
        editora.setNome_editora("Editora XYZ");
        editora.setEndereco("Rua das Letras, 100");
        editora.setContato("123456789");
        editoraDAO.inserir(editora);
        // Listar editoras
        List<Editora> editoras = editoraDAO.consultar();
        System.out.println("Listando editoras:");
        for (Editora e : editoras) {
            System.out.println("Editora: " + e.getNome_editora() + ", Endereço: " + e.getEndereco() + ", Contato: " + e.getContato());
        }
        // Operações com categorias
        CategoriasDAO categoriaDAO = new CategoriasDAO(conexao);
        Categorias categoria = new Categorias();
        categoria.setCod_categoria(1);
        categoria.setNome_categoria("Ficção");
        categoria.setDescricao("Categoria de ficção literária.");
        categoriaDAO.inserir(categoria);
        // Listar categorias
        List<Categorias> categorias = categoriaDAO.consultar();
        System.out.println("Listando categorias:");
        for (Categorias c : categorias) {
            System.out.println("Código: " + c.getCod_categoria() + ", Nome: " + c.getNome_categoria() + ", Descrição: " + c.getDescricao());
        }
        // Operações com livros
        LivrosDAO livroDAO = new LivrosDAO(conexao);
        Livros livro = new Livros();
        livro.setISBN(123456789);
        livro.setTitulo("O Senhor dos Anéis");
        livro.setAno("1954");
        livro.setCod_categoria(1);
        livro.setId_editora(1); // Assumindo que a editora com ID 1 foi inserida
        livroDAO.inserir(livro);
        // Listar livros
        List<Livros> livros = livroDAO.consultar();
        System.out.println("Listando livros:");
        for (Livros l : livros) {
            System.out.println("ISBN: " + l.getISBN() + ", Título: " + l.getTitulo() + ", Ano: " + l.getAno());
        }
        // Operações com empréstimos
        EmprestimosDAO emprestimoDAO = new EmprestimosDAO(conexao);
        Emprestimos emprestimo = new Emprestimos();
        emprestimo.setCod_emprestimo(1);
        emprestimo.setData_emprestimo(Date.valueOf("2023-09-01"));
        emprestimo.setData_devolucao(Date.valueOf("2023-09-15"));
        emprestimo.setISBN(123456789); // Assumindo que o livro com ISBN 123456789 foi inserido
        emprestimo.setCPF("123.456.789-1"); // CPF do usuário
        emprestimoDAO.inserir(emprestimo);
        // Listar empréstimos
        List<Emprestimos> emprestimos = emprestimoDAO.consultar();
        System.out.println("Listando empréstimos:");
        for (Emprestimos e : emprestimos) {
            System.out.println("Código: " + e.getCod_emprestimo() + ", ISBN: " + e.getISBN() + ", CPF: " + e.getCPF() +
                    ", Data de Empréstimo: " + e.getData_emprestimo() + ", Data de Devolução: " + e.getData_devolucao());
        }
        // Fechando a conexão
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao estabelecer conexão com o banco de dados", e);
        }
    }
}

