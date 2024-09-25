/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kesia.viana
 */
import Sistema.Livros;
import com.mycompany.bibliotecabanco.BibliotecaBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {

    public LivrosDAO(Connection conexao) {
    }

    public void inserir(Livros livro) {
        String sql = "INSERT INTO livros (titulo, autor_id, categoria_id) VALUES (?, ?, ?)";
        try (Connection conn = BibliotecaBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAutorId());
            stmt.setInt(3, livro.getCategoriaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livros> listar() {
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Connection conn = BibliotecaBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livros livro = new Livros();
                livro.setId(rs.getInt("ISBN"));
                livro.setTitulo(rs.getString("titulo"));
               // livro.setAutorId(rs.getInt("autor_id"));
                livro.setCategoriaId(rs.getInt("cod_categoria"));
                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    
}


    

