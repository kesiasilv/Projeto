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
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Sistema.Livros;

public class LivrosDAO {

    private final Connection conexao;

    public LivrosDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    //Inserção de dados (Create)
    public void inserir(Livros livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor_id, categoria_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getAutorId());
            ps.setInt(3, livro.getCategoriaId());
            ps.executeUpdate();
            ps.close();
    }
    
    //Consulta de dados (READ)
    public List<Livros> listar() throws SQLException {
        
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                Livros livro = new Livros();
                livro.setId(rs.getInt("ISBN"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutorId(rs.getInt("autor_id"));
                livro.setCategoriaId(rs.getInt("cod_categoria"));
                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
                livros.add(livro);
            }
            rs.close();
            ps.close();
            return livros;
    }  
    
    //Atualização de dados (UPDATE) 
    public void atualizar(Livros livro) throws SQLException{
        String sql = "UPDATE livros SET titulo"
                + " = ? WHERE autor_id = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        
            ps.setString(1, livro.getTitulo());
            ps.setInt(2, livro.getAutorId());
            ps.setInt(3, livro.getCategoriaId());
            ps.executeUpdate();
            
            ps.close();
    }
    
    //Exclusão de dados (Delete)
    public void excluir(int id) throws SQLException{
        String sql = "DELETE FROM livros WHERE id = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        
        //definir o id que sera excluido
        ps.setInt(1,id);
        
        //excluir
        ps.executeUpdate();
        
        //fechar
        ps.close();
    }
}
