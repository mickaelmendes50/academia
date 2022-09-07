package com.academia.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar o nome do equipamento")
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar a descrição do equipamento")
    @Getter
    @Setter
    private String description;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar a categoria do equipamento")
    @Getter
    @Setter
    private String category;

    @Column(nullable = false)
    @NotNull(message = "É necessário informar o peso do equipamento")
    @Getter
    @Setter
    private Double weight;

    @Column(nullable = false)
    @NotEmpty(message = "É necessário informar as dimensões do equipamento")
    @Getter
    @Setter
    private String dimensions;

    @Column(nullable = false)
    @NotNull(message = "É necessário informar o preço do equipamento")
    @Getter
    @Setter
    private Double price;
}
