package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private OrderStatus orderStatus;

    @Column(name = "dtOrder")
    private LocalDate dtOrder = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "idMesa")
    private Table table;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;



}
