package com.LeMart.service;

import com.LeMart.exception.OrderItemNotFoundException;
import com.LeMart.model.OrderItem;
import com.LeMart.repo.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    public List<OrderItem> findOrderItemsByOrderId(Long orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepo.deleteById(id);
    }
}