package com.dfs.academia.models.usuario;

public abstract class Usuario {
    
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    public Usuario(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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
