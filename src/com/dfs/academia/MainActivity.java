package com.dfs.academia;

import com.dfs.academia.cliente.Aluno;
import com.dfs.academia.plano.Beneficios;
import com.dfs.academia.plano.Plano;
import com.dfs.academia.plano.PlanoPresencial;

public class MainActivity {

    public static void main(String[] args) { 

        Aluno aluno = new Aluno("Marcelo M", "123.456.789-00",
                                 "62 98765-4321", 80.0, 1.85);

        System.out.println(aluno.toString());

        String descricao = "Acesso a todas as modalidades";
        PlanoPresencial plano = new PlanoPresencial("Completo", descricao);

        Beneficios beneficios = new Beneficios("Cardio", 35.0);
        plano.setBeneficios(beneficios);

        System.out.println(plano.toString());
    }    
}