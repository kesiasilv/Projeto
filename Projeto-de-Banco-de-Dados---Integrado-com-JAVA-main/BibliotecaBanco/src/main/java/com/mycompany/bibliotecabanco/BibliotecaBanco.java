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
import Sistema.Autores;
import Sistema.Livros;

import java.sql.Connection;
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

        try {
            // Estabelecendo a conexão
            conexao = getConnection();
            System.out.println("Conexão estabelecida com sucesso!");

            // Criando o statement para a consulta
            Statement stmt = conexao.createStatement();
            ResultSet rsMembro = stmt.executeQuery("SELECT * FROM usuarios");

            // Iterando sobre os resultados
            System.out.println("Listando usuários:");
            while (rsMembro.next()) {
                System.out.println("Nome: " + rsMembro.getString("nome"));
            }

            // Operações com autores
            AutoresDAO autorDAO = new AutoresDAO(conexao); // Passando a conexão
            Autores autor = new Autores();
            autor.setNome("J.R.R. Tolkien");
            autorDAO.inserir(autor);

            // Listar autores
            List<Autores> autores = autorDAO.consultar();
            System.out.println("Listando autores:");
            for (Autores a : autores) {
                System.out.println("Autor: " + a.getNome());
            }

            // Operações com livros
            LivrosDAO livroDAO = new LivrosDAO(conexao); // Passando a conexão
            Livros livro = new Livros();
            livro.setTitulo("O Senhor dos Anéis");
            livro.setAutorId(autor.getId()); // Ajuste para pegar o ID do autor inserido
            livro.setCategoriaId(1); // Substitua pelo ID real da categoria existente
            livroDAO.inserir(livro);
            

            // Listar livros
            List<Livros> livros = livroDAO.consultar();
            System.out.println("Listando livros:");
            for (Livros l : livros) {
                System.out.println("Livro: " + l.getTitulo());
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
        } finally {
            // Fechando a conexão
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
                }
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

