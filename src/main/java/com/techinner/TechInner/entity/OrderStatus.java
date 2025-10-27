package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_OrderStatus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;

}
