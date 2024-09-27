/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kesia.viana
 */


import Sistema.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {
    private final Connection conexao;

    public CategoriasDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean inserir(Categorias categoria) {
        String sql = "INSERT INTO categorias (cod_categoria, nome_categoria, descricao) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, categoria.getCod_categoria());
            ps.setString(2, categoria.getNome_categoria());
            ps.setString(3, categoria.getDescricao());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Categorias> consultar() {
        List<Categorias> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setCod_categoria(rs.getInt("cod_categoria"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}


