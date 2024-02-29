package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
    @Id
    @SequenceGenerator(name = "orders_seq_gen", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "orders_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column (name="order_items")
    private String orderItems;

    @Column(name="pay_via")
    private String payVia;

    @Column(name = "pick_up_otion")
    private String pickUpOption;


    @Column(name="total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date_time")
    private Date orderDateTime;
}