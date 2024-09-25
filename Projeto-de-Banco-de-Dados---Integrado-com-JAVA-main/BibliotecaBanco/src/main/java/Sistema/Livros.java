/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sistema;

/**
 *
 * @author kesia.viana
 */
public class Livros {
    private int ISBN;
    private String titulo;
    private int ano;
    private int id_editora;
    private int cd_categoria;
    private boolean disponibilidade;

    public Livros() {}

    public int getisbn() {
        return ISBN;
    }

    public void setisbn(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getANO() {
        return ano;
    }

    public void setANO(int ano) {
        this.ano = ano;
    }
    
    public int getIdEditora(){
        return id_editora;
    }
    
    public void setIdEditora(int id_editora){
        this.id_editora = id_editora;
    }
    
    public int getcdcategoria() {
        return cd_categoria;
    }

    public void setcdcategoria(int cd_categoria) {
        this.cd_categoria = cd_categoria;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
  

