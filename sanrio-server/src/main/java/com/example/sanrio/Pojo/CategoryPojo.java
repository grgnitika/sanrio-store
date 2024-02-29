package com.example.sanrio.Pojo;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter

public class CategoryPojo {

    private Integer id;

    @NotNull
    private String name;
}
