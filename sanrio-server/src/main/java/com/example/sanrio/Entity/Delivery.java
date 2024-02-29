package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class Delivery {
    @Id
    @SequenceGenerator(name = "delivery_seq_gen", sequenceName = "delivery_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "delivery_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "address")
    private String address;
}
