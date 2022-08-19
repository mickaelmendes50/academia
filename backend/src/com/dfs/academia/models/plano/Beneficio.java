package com.dfs.academia.models.plano;

// descontos: recomendação, estudante, atleta, patrocínio, etc...
public class Beneficio {
    private String nome;
    private double valor;

    public Beneficio(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return getNome() + "\n";
    }
}
