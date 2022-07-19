package com.dfs.academia.models.usuario;

import com.dfs.academia.models.plano.Plano;
import com.dfs.academia.models.plano.TipoPlano;

public class Professor extends Usuario {

    private Turno turno;
    private String registroCref;

    public Professor(String nome, String cpf, String telefone, Turno turno, String registroCref) {
        super(nome, cpf, telefone);
        this.turno = turno;
        this.registroCref = registroCref;
    }
    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    public String getRegistroCref() {
        return registroCref;
    }
    public void setRegistroCref(String registroCref) {
        this.registroCref = registroCref;
    }
    @Override
    public Plano getPlano() {
        Plano plano = new Plano("Plano", "Professor", TipoPlano.PROFESSOR, 0);
        return plano;
    }

}
