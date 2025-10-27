package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tb_Table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "table")
    private List<Order> orders;

}
