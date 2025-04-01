package com.LeMart.controller;

import com.LeMart.dto.OrderDTO;
import com.LeMart.dto.OrderItemDTO;
import com.LeMart.exception.ResourceNotFoundException;
import com.LeMart.model.*;
import com.LeMart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable Long userId) {
        try {
            List<Order> orders = orderService.findOrdersByUserId(userId);
            List<OrderDTO> orderDTOs = orders.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.findOrderById(id);
            return ResponseEntity.ok(convertToDTO(order));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            // Validate required fields
            if (orderDTO.getUserId() == null || orderDTO.getOrderItems() == null || orderDTO.getOrderItems().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Verify user exists
            User user = userService.findUserById(orderDTO.getUserId());
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            // Create order with all financial components
            Order order = new Order();
            order.setUser(user);
            order.setStatus(orderDTO.getStatus() != null ? orderDTO.getStatus() : "PENDING");
            order.setSubtotal(orderDTO.getSubtotal());
            order.setTax(orderDTO.getTax());
            order.setShipping(orderDTO.getShipping());
            order.setTotalAmount(orderDTO.getTotalAmount());
            order.setOrderItems(new ArrayList<>());

            // Add order items
            for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
                Product product = productService.findProductById(itemDTO.getProductId());
                if (product == null) {
                    throw new ResourceNotFoundException("Product not found with id: " + itemDTO.getProductId());
                }
                
                OrderItem item = new OrderItem();
                item.setOrder(order); 
                item.setProduct(product);
                item.setQuantity(itemDTO.getQuantity());
                item.setPriceAtPurchase(itemDTO.getPriceAtPurchase());
                order.getOrderItems().add(item);
            }

            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(convertToDTO(createdOrder), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        try {
            String newStatus = statusUpdate.get("status");
            if (newStatus == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Order updatedOrder = orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok(convertToDTO(updatedOrder));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setSubtotal(order.getSubtotal());  
        orderDTO.setTax(order.getTax());            
        orderDTO.setShipping(order.getShipping());  
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus());
        
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        order.getId(),
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getPriceAtPurchase()
                ))
                .collect(Collectors.toList());
        
        orderDTO.setOrderItems(itemDTOs);
        return orderDTO;
    }
}