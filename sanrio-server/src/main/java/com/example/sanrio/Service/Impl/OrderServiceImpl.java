package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.Cart;
import com.example.sanrio.Entity.Order;
import com.example.sanrio.Entity.User;
import com.example.sanrio.Pojo.OrderPojo;
import com.example.sanrio.Repo.CartRepo;
import com.example.sanrio.Repo.OrderRepo;
import com.example.sanrio.Repo.UserRepo;
import com.example.sanrio.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    private final CartRepo cartRepo;

    @Override
    public String save(OrderPojo orderPojo) {
        Order order = new Order();

        if (orderPojo.getId() != null){
            order = orderRepo.findById(order.getId()).get();
        }
        User user=userRepo.findById(orderPojo.getUserId()).get();
        order.setUser(user);

        // Fetch all carts for the user from the database
        List<Cart> carts = cartRepo.findByUserId(orderPojo.getUserId());
        //        order.setCarts(carts);

        // Convert the List<Integer> to a single String
        String orderItemsAsString = orderPojo.getOrderItems().stream()
                .map(String::valueOf) // Convert each Integer to String
                .collect(Collectors.joining(", "));

        // Set the order items as a single String
        order.setOrderItems(orderItemsAsString);

        order.setPayVia(orderPojo.getPayVia());
        order.setPickUpOption(orderPojo.getPickUpOption());
        order.setTotalPrice(orderPojo.getTotalPrice());
        order.setAddress(orderPojo.getAddress());
        order.setPhoneNumber(orderPojo.getPhoneNumber());
        order.setOrderDateTime(new Date());

        orderRepo.save(order);
        return (" This Order Saved Successfully");
//        cartRepo.deleteByUserId(orderPojo.getUserId());
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderRepo.deleteById(Long.valueOf(id));
    }

    @Override
    public String update(Long id, OrderPojo orderpojo) {
        return null;
    }
}