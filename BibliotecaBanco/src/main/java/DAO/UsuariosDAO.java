/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;



/**
 *
 * @author kesia.viana
 */



import Sistema.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    private final Connection conexao;

    public UsuariosDAO(Connection conexao) {
        this.conexao = conexao;
    }

   
    public boolean inserir(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (CPF, nome, email, data_nascimento, endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, usuario.getData_nascimento()); 
            ps.setString(5, usuario.getEndereco());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Usuarios> consultar() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setCpf(rs.getString("CPF"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setData_nascimento(rs.getDate("data_nascimento")); 
                usuario.setEndereco(rs.getString("endereco"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
    

