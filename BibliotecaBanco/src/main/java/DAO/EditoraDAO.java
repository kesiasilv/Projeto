/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kesia.viana
 */

import Sistema.Editora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    private final Connection conexao;

    public EditoraDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean inserir(Editora editora) {
        String sql = "INSERT INTO editora (nome_editora, endereco, contato) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, editora.getNome_editora());
            ps.setString(2, editora.getEndereco());
            ps.setString(3, editora.getContato());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Editora> consultar() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Editora editora = new Editora();
                editora.setId_editora(rs.getInt("id_editora"));
                editora.setNome_editora(rs.getString("nome_editora"));
                editora.setEndereco(rs.getString("endereco"));
                editora.setContato(rs.getString("contato"));
                editoras.add(editora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editoras;
    }
}

    

