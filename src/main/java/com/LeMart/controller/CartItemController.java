package com.LeMart.controller;

import com.LeMart.dto.CartItemDTO;
import com.LeMart.exception.ResourceNotFoundException;
import com.LeMart.model.Cart;
import com.LeMart.model.CartItem;
import com.LeMart.model.Product;
import com.LeMart.service.CartItemService;
import com.LeMart.service.CartService;
import com.LeMart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByCartId(@PathVariable Long cartId) {
        List<CartItem> cartItems = cartItemService.findCartItemsByCartId(cartId);
        List<CartItemDTO> cartItemDTOs = cartItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cartItemDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addCartItem(@RequestBody CartItemDTO cartItemDTO) {
        try {
            if (cartItemDTO.getPriceAtAddition() <= 0 || cartItemDTO.getCartId() == null || cartItemDTO.getProductId() == null) {
                return ResponseEntity.badRequest().build();
            }
            
            Cart cart = cartService.findCartById(cartItemDTO.getCartId());
            Product product = productService.findProductById(cartItemDTO.getProductId());
            
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setPriceAtAddition(cartItemDTO.getPriceAtAddition());
            
            CartItem createdCartItem = cartItemService.addCartItem(cartItem);
            return new ResponseEntity<>(convertToDTO(createdCartItem), HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long id, @RequestBody CartItemDTO cartItemDTO) {
        try {
            if (cartItemDTO.getPriceAtAddition() <= 0 || !id.equals(cartItemDTO.getId())) {
                return ResponseEntity.badRequest().build();
            }
            
            CartItem existingItem = cartItemService.findCartItemById(id);
            existingItem.setQuantity(cartItemDTO.getQuantity());
            
            // Don't update price on quantity changes
            // existingItem.setPriceAtAddition(cartItemDTO.getPriceAtAddition());
            
            CartItem updatedCartItem = cartItemService.updateCartItem(existingItem);
            return new ResponseEntity<>(convertToDTO(updatedCartItem), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        try {
            cartItemService.deleteCartItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private CartItemDTO convertToDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setProductId(cartItem.getProduct().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPriceAtAddition(cartItem.getPriceAtAddition()); 
        return cartItemDTO;
    }
}