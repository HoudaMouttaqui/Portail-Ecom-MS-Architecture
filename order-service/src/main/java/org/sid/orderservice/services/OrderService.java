package org.sid.orderservice.services;

import org.sid.orderservice.OrderNotFoundException;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FeignClient(name = "CUSTOMER-SERVICE")
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public Order placeOrder(Order order){
        return orderRepository.save(order);
    }


    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }
    public boolean cancelOrder(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        if (order.getOrderStatus() == OrderStatus.PENDING || order.getOrderStatus() == OrderStatus.PROCESSED) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public boolean processPayment(long id, double amount) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        if (order.getOrderStatus() == OrderStatus.PROCESSED) {
            //call external or internal payment gateway here
            boolean isPaymentSuccessful = true;
            if (isPaymentSuccessful) {
                order.setOrderStatus(OrderStatus.PAID);
                order.setTotalAmount(amount);
                orderRepository.save(order);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }



    public Order updateOrder(long id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(()-> new
                OrderNotFoundException("Order not found with id"+ id));
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setTotalAmount(order.getTotalAmount());
        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setOrderItems(order.getOrderItems());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        orderRepository.deleteById(id);
    }

    public boolean processOrder(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        if (order.getOrderStatus().equals(OrderStatus.PENDING)) {
            order.setOrderStatus(OrderStatus.PROCESSED);
            orderRepository.save(order);
            return true;
        }
        return false;
    }
}
