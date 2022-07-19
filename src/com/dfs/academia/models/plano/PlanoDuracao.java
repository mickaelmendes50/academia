package com.dfs.academia.models.plano;

public enum PlanoDuracao {
    MENSAL(1, 0), 
    TRIMESTRAL(3, 3), 
    SEMESTRAL(6, 5), 
    ANUAL(12, 10);

    private int meses;
    private double porcentagemDesconto;

    private PlanoDuracao(int meses, double porcentagemDesconto) {
        this.meses = meses;
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public double getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(double porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }
}
