package com.techinner.TechInner.repository;

import com.techinner.TechInner.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
