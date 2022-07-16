package com.dfs.academia.plano;

public class Beneficios {
    private String nome;
    private double valor;

    public Beneficios(String nome, double valor) {
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
