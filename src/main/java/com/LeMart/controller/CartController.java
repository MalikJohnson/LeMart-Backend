package com.LeMart.controller;

import com.LeMart.dto.CartDTO;
import com.LeMart.dto.CartItemDTO;
import com.LeMart.exception.ResourceNotFoundException;
import com.LeMart.model.*;
import com.LeMart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        try {
            Cart cart = cartService.findCartByUserId(userId);
            return ResponseEntity.ok(convertToDTO(cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        try {
            // Validate request
            if (cartDTO.getUserId() == null) {
                return ResponseEntity.badRequest().build();
            }

            User user = userService.findUserById(cartDTO.getUserId());
            Cart cart = new Cart();
            cart.setUser(user);

            if (cartDTO.getItems() != null) {
                for (CartItemDTO itemDTO : cartDTO.getItems()) {
                    Product product = productService.findProductById(itemDTO.getProductId());
                    CartItem item = new CartItem();
                    item.setCart(cart);
                    item.setProduct(product);
                    item.setQuantity(itemDTO.getQuantity());
                    item.setPriceAtAddition(itemDTO.getPriceAtAddition());
                    cart.addItem(item);
                }
            }

            Cart createdCart = cartService.createCart(cart);
            return new ResponseEntity<>(convertToDTO(createdCart), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        try {
            // Validate request
            if (!id.equals(cartDTO.getId()) || cartDTO.getUserId() == null) {
                return ResponseEntity.badRequest().build();
            }

            Cart cart = cartService.findCartById(id);
            User user = userService.findUserById(cartDTO.getUserId());
            
            // Verify ownership
            if (!cart.getUser().getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            
            // Update items
            updateCartItems(cart, cartDTO.getItems()); // Extract to helper method
            Cart updatedCart = cartService.updateCart(cart);
            return ResponseEntity.ok(convertToDTO(updatedCart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    private void updateCartItems(Cart cart, List<CartItemDTO> itemDTOs) {
        if (itemDTOs == null) return;
        
        // Clear existing items (orphanRemoval=true in Cart ensures proper deletion)
        cart.getCartItems().clear();
        
        // Add new items
        for (CartItemDTO itemDTO : itemDTOs) {
            Product product = productService.findProductById(itemDTO.getProductId());
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPriceAtAddition(product.getPrice());
            cart.addItem(item);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        try {
            cartService.deleteCart(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        
        List<CartItemDTO> itemDTOs = cart.getCartItems().stream()
            .map(item -> new CartItemDTO(
                item.getId(),
                cart.getId(),
                item.getProduct().getId(),
                item.getQuantity(),
                item.getPriceAtAddition()
            ))
            .collect(Collectors.toList());
        
        cartDTO.setItems(itemDTOs);
        return cartDTO;
    }
}