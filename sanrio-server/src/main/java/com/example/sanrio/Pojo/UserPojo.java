package com.example.sanrio.Pojo;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Long id;

    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message="confirm-password is required")
    private String confirmPassword;

    @NotNull(message="fullname is required")
    private String fullName;

    @NotNull(message="security question is required")
    private String securityQuestion;

    private String roles;
}
