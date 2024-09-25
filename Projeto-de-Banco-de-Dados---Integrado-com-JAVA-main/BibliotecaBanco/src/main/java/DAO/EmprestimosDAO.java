/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kesia.viana
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Sistema.Emprestimo;

public class EmprestimosDAO {
    
    private final Connection conexao;
    
    public EmprestimosDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    //Inserção de dados (Create)
    public void inserir(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, emprestimo.getLivroId());
        ps.setInt(2, emprestimo.getusuarioId());
        ps.setDate(3, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
        ps.setDate(4, java.sql.Date.valueOf(emprestimo.getdatadevolucao()));
        ps.executeUpdate();
        ps.close();
    }
    
    //CConsulta de dados (Read)
    public List<Emprestimo> consultar() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(rs.getInt("id"));
            emprestimo.setlivroId(rs.getInt("livro_id"));
            emprestimo.setUsuario(rs.getInt("usuario_id"));
            emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo").toLocalDate());
            emprestimo.setdatadevolucao(rs.getDate("data_devolucao").toLocalDate());
            emprestimos.add(emprestimo);
        }
        ps.close();
        rs.close();
        return emprestimos;
        
    }
    
    //Atualização de dados (Update)
    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimos SET livro_id = ?, usuario-id = ?m  data_emprestimo = ?, data_devololução = ? WHERE id = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, emprestimo.getLivroId());
        ps.setInt(2, emprestimo.getUsuarioId());
        ps.setInt(3, emprestimo.getId());
        ps.setDate(4, java.sql.Date.valueOf(emprestimo.getDataEmprestimo()));
        ps.setDate(5, java.sql.Date.valueOf(emprestimo.getdatadevolucao()));
        ps.executeUpdate();
        ps.close();
    }
    
    //Exclusão de dados (Delete)
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM emprestimos WHERE id = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
    
}
