package com.example.sanrio.Pojo;

import jakarta.validation.constraints.NotNull;

public class DeliveryPojo {
    private Long id;
    private Long user;
    @NotNull
    private Integer order;
    @NotNull
    private String address;
}
