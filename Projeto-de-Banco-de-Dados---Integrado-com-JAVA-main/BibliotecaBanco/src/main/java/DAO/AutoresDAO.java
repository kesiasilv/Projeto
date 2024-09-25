/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kesia.viana
 */

import Sistema.Autores;
import com.mycompany.bibliotecabanco.BibliotecaBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoresDAO {

    public AutoresDAO(Connection conexao) {
    }
    
    public void inserir(Autores autor) {
        String sql = "INSERT INTO autores (nome) VALUES (?)";
        try (Connection conn = BibliotecaBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autores> listar() {
        List<Autores> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (Connection conn = BibliotecaBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Autores autor = new Autores();
                autor.setId(rs.getInt("id_autor"));
                autor.setNome(rs.getString("nome"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

// Métodos de atualização e exclusão podem ser implementados de maneira similar
    // Atualizar Autor
    public void atualizar(Autores autor) {
        String sql = "UPDATE autores SET nome = ? WHERE id_autor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setInt(2, autor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Excluir Autor
    public void excluir(int id) {
        String sql = "DELETE FROM autores WHERE id_autor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

    
