package com.dfs.academia.models.usuario;

import com.dfs.academia.models.exercicios.TabelaTreino;
import com.dfs.academia.models.plano.Plano;

public class Aluno extends Usuario {

    private double peso;
    private double altura;
    private String matrícula;
    private TabelaTreino tabelaTreino;

    public Aluno(String nome, String cpf, String telefone, double peso, double altura, String matrícula) {
        super(nome, cpf, telefone);
        this.peso = peso;
        this.altura = altura;
        this.matrícula = matrícula;
    }

    public String getMatrícula() {
        return matrícula;
    }

    public void setMatrícula(String matrícula) {
        this.matrícula = matrícula;
    }

    public TabelaTreino getTabelaTreino() {
        return tabelaTreino;
    }

    public void setTabelaTreino(TabelaTreino tabelaTreino) {
        this.tabelaTreino = tabelaTreino;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public Plano getPlano() {
        return this.plano;
    }
}
