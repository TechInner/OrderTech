package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tb_Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private OrderStatus orderStatus;

    @Column(name = "dtOrder")
    private LocalDate dtOrder = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "idTable")
    private Table table;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;



}
