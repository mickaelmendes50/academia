package com.dfs.academia.cliente;

public class Aluno extends Pessoa {

    public Aluno(String nome, String cpf, String telefone, double peso, double altura) {
        super(nome, cpf, telefone);
        this.peso = peso;
        this.altura = altura;
    }

    private double peso;
    private double altura;

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

    public String toString() {
        String dados = "Peso: " + getPeso() + "\n" +
                       "Altura: " + getAltura() + "\n";
        return super.toString() + dados;
    }
}
