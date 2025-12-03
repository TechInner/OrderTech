package com.techinner.TechInner.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tb_Table")
@Builder
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

    public Table(int id, String username, String password, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.orders = orders;
    }

    public Table() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
