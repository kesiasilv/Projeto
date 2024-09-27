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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoresDAO {
    
    private Connection conexao;
    
    // Construtor que recebe a conexão
    public AutoresDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    // Inserção de dados (CREATE)
    public boolean inserir(Autores autor) {
        String sql = "INSERT INTO autores (nome, nacionalidade, sexo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setString(3, autor.getSexo());
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Consulta de dados (READ)
    public List<Autores> consultar() {
        List<Autores> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Autores autor = new Autores();
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNome(rs.getString("nome"));
                autor.setNacionalidade(rs.getString("nacionalidade"));
                autor.setSexo(rs.getString("sexo"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    // Atualização de dados (UPDATE)
    public boolean atualizar(Autores autor) {
        String sql = "UPDATE autores SET nome = ?, nacionalidade = ?, sexo = ? WHERE id_autor = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setString(3, autor.getSexo());
            ps.setInt(4, autor.getId_autor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Exclusão de dados (DELETE)
    public void excluir(int id) {
        String sql = "DELETE FROM autores WHERE id_autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
