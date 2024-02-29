package com.example.sanrio.Pojo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventPojo {

    private Long id;

    @NotNull
    private String eventName;

    @NotNull
    private String eventImage;

    @NotNull
    private String eventDescription;

    @NotNull
    private Integer eventPrice;

}