/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sistema;

/**
 *
 * @author Amanda
 */

import java.time.LocalDate; // serao utilizadas 2 datas 

public class Emprestimo {
    
    private int cd;
    private int ISBN;
    private String cpf;
    private LocalDate dataemprestimo;
    private LocalDate datadevolucao;

    //construtor necessario
    public Emprestimo(){
        
    }
    
    public int getId(){
        return cd;
    }
    
    public void setId(int id) {
        this.cd = cd;
    }
    
    public int getisbn(){
        return ISBN;
    }
    
    public void setisbn(String ISBN){
        int isbn = 0;
        this.ISBN = isbn;
    }
    
    public String getucpf(){
        return cpf;
    }
    
    public void setcpf(String cpf){
        this.cpf = cpf;
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


