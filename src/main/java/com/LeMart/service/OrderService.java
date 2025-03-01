package com.LeMart.service;

import com.LeMart.exception.OrderNotFoundException;
import com.LeMart.model.Order;
import com.LeMart.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public Order findOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " does not exist"));
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}