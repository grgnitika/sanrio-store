package com.example.sanrio.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class EventBookingPojo {
    private Long id;

//    @NotNull
    private Long userId;

//    @NotNull
    private Long eventId;

    @NotNull
    private Date eventDate;

    @NotNull
    private LocalTime eventTime;

    private Integer noOfGuest;

    private String specialRequest;

    private boolean eventStatus;
}
