package com.academia.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o nome do aluno")
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o email do aluno")
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o cpf do aluno")
    @Getter
    @Setter
    private String cpf;

    @Column(nullable = false)
    @NotNull(message = "É necessário informar o tipo do plano do aluno")
    @Getter
    @Setter
    private String tipo;

}
