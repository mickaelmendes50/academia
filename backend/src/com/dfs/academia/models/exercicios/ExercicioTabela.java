package com.dfs.academia.models.exercicios;

public class ExercicioTabela {
    
    private Exercicio exercicio;
    private int series;
    private int repeticoes;

    public ExercicioTabela(Exercicio exercicio, int series, int repeticoes) {
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
    }
    public Exercicio getExercicio() {
        return exercicio;
    }
    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }
    public int getSeries() {
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }
    public int getRepeticoes() {
        return repeticoes;
    }
    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }
}
