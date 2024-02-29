package com.example.sanrio.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class CartPojo {

    private Long id;

    private Long userId;

    private Integer itemId;

    @NotNull
    private Integer total_price;

    @NotNull
    private Integer quantity;
}
