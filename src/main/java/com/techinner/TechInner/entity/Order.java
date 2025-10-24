package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@jakarta.persistence.Table(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Table mesa;
    private OrderStatus statusPedido;

    @Column(name = "dtOrder")
    private LocalDate dtOrder = LocalDate.now();

    
}
