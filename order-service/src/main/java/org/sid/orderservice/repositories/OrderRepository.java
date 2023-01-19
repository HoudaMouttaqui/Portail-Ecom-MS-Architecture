package org.sid.orderservice.repositories;

import org.sid.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByOrderStatus(String status);
}
