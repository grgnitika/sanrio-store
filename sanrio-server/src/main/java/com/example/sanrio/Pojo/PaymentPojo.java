package com.example.sanrio.Pojo;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PaymentPojo {
    private Long id;

//    @NotNull
//    private Long orderId;

    private Long userId;

    private List<Integer> orderItems;

    @NotNull
    private Integer subTotal;

    private Integer deliveryFee;

    @NotNull
    private Integer total;

    private Date paymentDate;

    private String status;


}
