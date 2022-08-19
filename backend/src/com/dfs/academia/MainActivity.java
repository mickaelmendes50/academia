package com.dfs.academia;

import java.util.ArrayList;

import com.dfs.academia.models.plano.Beneficio;
import com.dfs.academia.models.plano.Plano;
import com.dfs.academia.models.plano.TipoPlano;
import com.dfs.academia.models.usuario.Aluno;

public class MainActivity {

    public static void main(String[] args) { 

        Aluno aluno = new Aluno("Marcelo M", "123.456.789-00",
                                 "62 98765-4321", 80.0, 1.85, "123");

        System.out.println(aluno.toString());

        String descricao = "Acesso a todas as modalidades";
        Plano plano = new Plano("Completo", descricao, TipoPlano.SEMESTRAL, 100.0);

        Beneficio beneficio = new Beneficio("Cardio", 35.0);
        ArrayList<Beneficio> beneficios = new ArrayList<>();
        beneficios.add(beneficio);
        plano.setBeneficios(beneficios);

        System.out.println(plano.toString());
    }    
}