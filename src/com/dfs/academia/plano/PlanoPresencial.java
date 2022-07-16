package com.dfs.academia.plano;

import java.util.ArrayList;

public class PlanoPresencial extends Plano implements IPlano {
    private ArrayList<Beneficios> beneficios = new ArrayList<Beneficios>();

    public PlanoPresencial(String titulo, String descricao) {
        super(titulo, descricao);
    }

    public void setBeneficios(Beneficios beneficios) {
        this.beneficios.add(beneficios);
    }

    public ArrayList<Beneficios> getBeneficios() {
        return beneficios;
    }

    @Override
    public double valor() {
        double valor = 0;
        for (int i = 0; i < getBeneficios().size(); i++) {
            valor += getBeneficios().get(i).getValor();
        }
        return valor;
    }

    @Override
    public String toString() {
        String dados = super.toString() + "\nBeneficios: \n";
        for (int i = 0; i < getBeneficios().size(); i++) {
            dados += " - " + getBeneficios().get(i).toString();
        }
        dados += "Valor = " + valor() + "\n";
        return dados;
    }
}
