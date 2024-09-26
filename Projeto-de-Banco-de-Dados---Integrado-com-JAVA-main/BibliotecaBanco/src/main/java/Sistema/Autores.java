/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sistema;

/**
 *
 * @author kesia.viana
 */
public class Autores {
   
    private int id_autor;
    private String nome;
    private String nacionalidade;
    private String sexo;
    

    public Autores() {}

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   public String getNacionalidade() {
        return nacionalidade;
    }
    
    public void setNacionalidade(String nacionalidade){
        this.nacionalidade = nacionalidade;
    }

    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
}

