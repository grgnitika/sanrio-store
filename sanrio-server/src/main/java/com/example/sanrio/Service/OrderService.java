package com.example.sanrio.Service;

import com.example.sanrio.Entity.Order;
import com.example.sanrio.Pojo.OrderPojo;


import java.util.List;
import java.util.Optional;

public interface OrderService {

    String save(OrderPojo orderpojo);


    List<Order> getAll();


    Optional<Order> findById(Long id);

    void deleteById(Long id);

    String update(Long id , OrderPojo orderpojo);

}
