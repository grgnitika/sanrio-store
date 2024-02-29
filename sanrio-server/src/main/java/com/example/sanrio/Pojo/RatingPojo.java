// RatingPojo.java
package com.example.sanrio.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingPojo {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Rating value is required")
    private int value; // Rating value (1-5)
}
