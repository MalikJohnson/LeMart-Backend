package com.LeMart.service;

import com.LeMart.exception.OrderNotFoundException;
import com.LeMart.model.Order;
import com.LeMart.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    
    // Define valid statuses directly in the service
    private final Set<String> VALID_STATUSES = new HashSet<>(Arrays.asList(
        "PENDING",
        "PROCESSING",
        "SHIPPED",
        "DELIVERED",
        "CANCELLED",
        "COMPLETED"
    ));
    
    // Define allowed status transitions
    private final Map<String, Set<String>> ALLOWED_TRANSITIONS = Map.of(
        "PENDING", Set.of("PROCESSING", "CANCELLED"),
        "PROCESSING", Set.of("SHIPPED", "CANCELLED"),
        "SHIPPED", Set.of("DELIVERED"),
        "DELIVERED", Set.of("COMPLETED"),
        "CANCELLED", Set.of(),
        "COMPLETED", Set.of()
    );

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order createOrder(Order order) {
        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        } else if (!VALID_STATUSES.contains(order.getStatus())) {
            throw new IllegalArgumentException("Invalid initial order status: " + order.getStatus());
        }
        return orderRepo.save(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public Order findOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " does not exist"));
    }

    @Transactional
    public Order updateOrderStatus(Long id, String newStatus) {
        Order order = findOrderById(id);
        
        // Validate the new status
        if (!VALID_STATUSES.contains(newStatus)) {
            throw new IllegalArgumentException("Invalid order status: " + newStatus);
        }
        
        // Check if transition is allowed
        String currentStatus = order.getStatus();
        if (!ALLOWED_TRANSITIONS.get(currentStatus).contains(newStatus)) {
            throw new IllegalStateException(
                String.format("Cannot change status from %s to %s", currentStatus, newStatus)
            );
        }
        
        order.setStatus(newStatus);
        return orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}