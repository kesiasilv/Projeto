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
   
    private int id;
    private String nome;

    public Autores() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   public String getNascionalidade() {
        return nascionalidade;
    }
    
    public void setNacionalidade(String nacionalidade){
        this.nascionalidade = nascionalidade;
    }

    public String getsexo() {
        return sexo;
    }
    
    public void setsexo(String string){
        this.sexo = sexo;
    }
}

