package com.example.sanrio.Pojo;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter

public class ReservationPojo {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private Integer persons;

    @NotNull
    private String day;

    private  Integer tableId;


}
