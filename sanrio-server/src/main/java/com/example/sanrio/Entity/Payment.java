package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="payment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="order_items")
    private String orderItems;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="Sub_Total" , nullable=false)  // Apply join table from cart
    private Integer subTotal;

    @Column(name="Delivery_Fee" ) // Apply join table from cart
    private Integer deliveryFee;

    @Column(name="Total" , nullable=false)    // Apply join table from cart
    private Integer total;

    @Column(name = "date")
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column (name="Status")
    private String status;


}

