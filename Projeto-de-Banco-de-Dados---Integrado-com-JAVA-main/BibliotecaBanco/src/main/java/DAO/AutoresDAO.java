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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Sistema.Autores;

public class AutoresDAO {
    
    private Connection conexao;
    
    //construtor que recebe a conexao
    public AutoresDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    //inserção de dados (CREATE)
    public boolean inserir(Autores autor) {
        try {
             Connection con = DbConnect.getConexao();
             String sql = "INSERT INTO autores (nome) VALUES (?)";
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, autor.getNome());
             ps.setString(2, autor.getNascionalidade());
             ps.setString(3, autor.getsexo());
             
             ps.execute();
             ps.close();
             con.close();
             
             return true;
             
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Consulta de dados (READ)
    public List<Autores> consultar() {
        
        List<Autores> autores = new ArrayList<>();

        try{
            Connection con = DbConnect.getConexao();
            String sql = "SELECT * FROM autores";
            
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Autores autor = new Autores();
                
                autor.setId(rs.getInt("idAutor"));
                autor.setNome(rs.getString("nomeAutor"));
                autor.setNacionalidade(rs.getString("nacionalidade"));
                autor.setDataNascimento(rs.getString("sexo"));
                
                autores.add(autor);
            }
            rs.close();
            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    
    // Atualização de dados (UPDATE)
    public boolean atualizar(Autores autor) {
        
        
        try {
            Connection con = DbConnect.getConexao();
            String sql = "UPDATE autores SET nome = ? WHERE id_autor = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, autor.getNome());
            ps.setInt(2, autor.getId());
            ps.setString(3, autor.getNascionalidade());
            ps.setString(4, autor.getsexo());
            
            //executa o comando de atualização
            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Exclusão de dados (DELETE)
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

    
