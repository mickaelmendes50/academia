package com.dfs.academia.plano;

public class Plano {
    private String titulo;
    private String descricao;

    Plano(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        String dados = "Plano: " + getTitulo() + "\n" +
                       "Descrição: " + getDescricao() + "\n";
        return dados;
    }
}
