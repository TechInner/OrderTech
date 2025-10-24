package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "OrderItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Order pedido;

    //private Cardapio cardapio

    @Column(name = "quantidade")
    private int quantity;

    @Column(name = "Preco")
    private Double price;

    @Column(name = "Observation")
    private String observation;




}
