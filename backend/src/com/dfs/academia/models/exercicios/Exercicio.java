package com.dfs.academia.models.exercicios;

public class Exercicio {
    
    private String nome;
    private String grupoMuscular;
    private String videoExecucao;
    private String equipamento;
    
    public Exercicio(String nome, String grupoMuscular, String videoExecucao, String equipamento) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.videoExecucao = videoExecucao;
        this.equipamento = equipamento;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getGrupoMuscular() {
        return grupoMuscular;
    }
    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }
    public String getVideoExecucao() {
        return videoExecucao;
    }
    public void setVideoExecucao(String videoExecucao) {
        this.videoExecucao = videoExecucao;
    }
    public String getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }
}
