package com.academia.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o nome do exercício")
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o grupo muscular")
    @Getter
    @Setter
    private String muscleGroup;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o equipamento")
    @Getter
    @Setter
    private String equipment;
}
