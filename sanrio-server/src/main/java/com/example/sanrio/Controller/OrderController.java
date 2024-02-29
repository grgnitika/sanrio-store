package com.example.sanrio.Controller;


import com.example.sanrio.Entity.Order;
import com.example.sanrio.Pojo.OrderPojo;
import com.example.sanrio.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping(value = "/save")
    public void saveOrder(@Valid @RequestBody OrderPojo orderpojo) {
        orderService.save(orderpojo);
    }

    @PostMapping(value = "/saveAll")
    public String saveOrder(@Valid @RequestBody List<OrderPojo> orderPojos) {
        for (OrderPojo orderpojo:orderPojos){
            orderService.save(orderpojo);
        }
        return "Saved Successfully!";
    }

    @GetMapping("/getAll")
    public List<Order> getALl() {
        return orderService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Order> getById(@PathVariable("id") Long id) {
        return this.orderService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.orderService.deleteById(id);
    }

}
