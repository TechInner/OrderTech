package com.techinner.TechInner.repository;

import com.techinner.TechInner.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
