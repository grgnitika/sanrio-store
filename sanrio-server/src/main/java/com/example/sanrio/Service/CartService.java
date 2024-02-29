package com.example.sanrio.Service;

import com.example.sanrio.Entity.Cart;
import com.example.sanrio.Pojo.CartPojo;

import java.util.List;

public interface CartService {

    void saveCart(CartPojo cartPojo);

    List<Cart> getAll();

    void deleteById(Long id);

    void updateQuantity(Long id, Integer quantity);
}
