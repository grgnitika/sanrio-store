package com.example.sanrio.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPojo {


    private Integer id;

    @NotNull
    private String itemName;

    @NotNull
    private Integer categoryId;

    private MultipartFile itemImage;

    @NotNull
    private double itemPrice;
}
