package com.academia.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "USER")
@DiscriminatorValue("USER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Getter
    @Setter
    @Column(name="USERNAME", nullable = false, unique = true)
    private String username;

    @Getter
    @Setter
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Getter
    @Setter
    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
