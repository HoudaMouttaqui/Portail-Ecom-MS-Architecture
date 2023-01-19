package org.sid.orderservice.web;

import org.sid.orderservice.entities.Order;
import org.sid.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }
    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> findOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/status/{status}")
    public List<Order> findOrdersByStatus(@PathVariable String status) {
        return orderService.findOrdersByStatus(status);
    }
    @PutMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
    }
    @PutMapping("/{id}/process")
    public boolean processOrder(@PathVariable long id) {
        return orderService.processOrder(id);
    }

    @PutMapping("/{id}/payment")
    public boolean processPayment(@PathVariable Long id, @RequestParam double amount) {
        return orderService.processPayment(id, amount);
    }

}
