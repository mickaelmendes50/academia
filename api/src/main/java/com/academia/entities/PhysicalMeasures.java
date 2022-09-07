package com.academia.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class PhysicalMeasures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    //todo mudar tipo para Usuario quando a classe for criada
    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o nome do aluno")
    @Getter
    @Setter
    private String bodybuilderName;

    @Column(nullable = false)
    @Min(value = 0)
    @Getter
    @Setter
    private int weight;

    @Column(nullable = false)
    @Min(value = 0)
    @Getter
    @Setter
    private int height;
}