package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Administrator")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Nome")
    private String name;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "Password")
    private String password;
}
