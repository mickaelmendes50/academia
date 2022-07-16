package com.dfs.academia;

import com.dfs.academia.cliente.Aluno;

public class MainActivity {

    public static void main(String[] args) { 

        Aluno aluno = new Aluno("Marcelo M",
                                 "123.456.789-00", 
                                 "62 98765-4321", 
                                 80.0, 
                                 1.85);

        System.out.println(aluno.toString());
    }    
}