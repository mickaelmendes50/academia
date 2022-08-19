package com.dfs.academia.models.exercicios;

import java.util.ArrayList;
import java.util.Date;

import com.dfs.academia.models.usuario.Professor;

public class TabelaTreino {
    
    private Date dataDeCriacao;
    private Date dataDeTroca;
    private Professor professorCriador;
    private ArrayList<ExercicioTabela> exerciciosTabela;

    public TabelaTreino(Date dataDeCriacao, Date dataDeTroca, Professor professorCriador,
            ArrayList<ExercicioTabela> exerciciosTabela) {
        this.dataDeCriacao = dataDeCriacao;
        this.dataDeTroca = dataDeTroca;
        this.professorCriador = professorCriador;
        this.exerciciosTabela = exerciciosTabela;
    }
    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }
    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
    public Date getDataDeTroca() {
        return dataDeTroca;
    }
    public void setDataDeTroca(Date dataDeTroca) {
        this.dataDeTroca = dataDeTroca;
    }
    public Professor getProfessorCriador() {
        return professorCriador;
    }
    public void setProfessorCriador(Professor professorCriador) {
        this.professorCriador = professorCriador;
    }
    public ArrayList<ExercicioTabela> getExerciciosTabela() {
        return exerciciosTabela;
    }
    public void setExerciciosTabela(ArrayList<ExercicioTabela> exerciciosTabela) {
        this.exerciciosTabela = exerciciosTabela;
    }
}
