package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_OrderStatus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
