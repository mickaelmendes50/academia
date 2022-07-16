package com.dfs.academia.cliente;

public abstract class Pessoa {
    
    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    private String nome;
    private String cpf;
    private String telefone;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String toString() {
        String dados = "Nome: " + getNome() + "\n" +
                       "CPF: " + getCpf() + "\n" +
                       "Telefone: " + getTelefone() + "\n";
        return dados;
    }
}
