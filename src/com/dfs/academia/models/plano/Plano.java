package com.dfs.academia.models.plano;

import java.util.ArrayList;

public class Plano implements IPlano {
    private String titulo;
    private String descricao;
    private PlanoDuracao duracao;
    private double valorMensalidade;
    private ArrayList<Beneficio> beneficios = new ArrayList<Beneficio>();
    
    public Plano(String titulo, String descricao, PlanoDuracao duracao, double valorMensalidade,
            ArrayList<Beneficio> beneficios) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.valorMensalidade = valorMensalidade;
        this.beneficios = beneficios;
    }

    public Plano(String titulo, String descricao, PlanoDuracao duracao, double valorMensalidade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.valorMensalidade = valorMensalidade;
    }

    @Override
    public double calculaValor() {
        double valorBeneficios = 0.0;
        for (Beneficio beneficio : beneficios) {
            valorBeneficios += beneficio.getValor();
        }

        return (valorMensalidade * duracao.getMeses() * 
                    (1 - duracao.getPorcentagemDesconto())) - valorBeneficios;
    }

    public PlanoDuracao getDuracao() {
        return duracao;
    }

    public void setDuracao(PlanoDuracao duracao) {
        this.duracao = duracao;
    }


    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
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

    public ArrayList<Beneficio> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(ArrayList<Beneficio> beneficios) {
        this.beneficios = beneficios;
    }
}
