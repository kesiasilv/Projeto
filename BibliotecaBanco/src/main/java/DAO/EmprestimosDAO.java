/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kesia.viana
 */

import Sistema.Emprestimos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmprestimosDAO {
    private final Connection conexao;

    public EmprestimosDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean inserir(Emprestimos emprestimo) {
        String sql = "INSERT INTO emprestimos (cod_emprestimo, data_emprestimo, data_devolucao, ISBN, CPF) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, emprestimo.getCod_emprestimo());
            ps.setDate(2, emprestimo.getData_emprestimo());
            ps.setDate(3, emprestimo.getData_devolucao());
            ps.setInt(4, emprestimo.getISBN());
            ps.setString(5, emprestimo.getCPF());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Emprestimos> consultar() {
        List<Emprestimos> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimos emprestimo = new Emprestimos();
                emprestimo.setCod_emprestimo(rs.getInt("cod_emprestimo"));
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getDate("data_devolucao"));
                emprestimo.setISBN(rs.getInt("ISBN"));
                emprestimo.setCPF(rs.getString("CPF"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
}
