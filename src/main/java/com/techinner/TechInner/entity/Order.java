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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getDtOrder() {
        return dtOrder;
    }

    public void setDtOrder(LocalDate dtOrder) {
        this.dtOrder = dtOrder;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
