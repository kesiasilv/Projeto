/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sistema;

/**
 *
 * @author kesia.viana
 */

import java.time.LocalDate; // serao utilizadas 2 datas 

public class Emprestimo {
    
    private int id;
    private int livroId;
    private int usuarioId;
    private LocalDate dataemprestimo;
    private LocalDate datadevolucao;

    //construtor necessario
    public Emprestimo(){
        
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getLivroId(){
        return livroId;
    }
    
    public void setlivroId(int livroId){
        this.livroId = livroId;
    }
    
    public int getusuarioId(){
        return usuarioId;
    }
    
    public void setUsuario(int usuarioId){
        this.usuarioId = usuarioId;
    }
    
    public LocalDate getDataEmprestimo(){
        return dataemprestimo;
    }
    
    public void setDataEmprestimo(LocalDate dataemprestimo){
        this.dataemprestimo = dataemprestimo;
    }
    
    public LocalDate getdatadevolucao(){
        return datadevolucao;
    }
    
    public void setdatadevolucao(LocalDate dataDevolucao){
         this.datadevolucao = dataDevolucao;
    }
}
