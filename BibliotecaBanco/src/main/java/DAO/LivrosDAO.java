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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {
    private final Connection conexao;

    public LivrosDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean inserir(Livros livro) {
        String sql = "INSERT INTO livros (ISBN, titulo, ano, cod_categoria, id_editora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, livro.getISBN());
            ps.setString(2, livro.getTitulo());
            ps.setString(3, livro.getAno());
            ps.setInt(4, livro.getCod_categoria());
            ps.setInt(5, livro.getId_editora());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Livros> consultar() {
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livros livro = new Livros();
                livro.setISBN(rs.getInt("ISBN"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getString("ano"));
                livro.setCod_categoria(rs.getInt("cod_categoria"));
                livro.setId_editora(rs.getInt("id_editora"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
}
