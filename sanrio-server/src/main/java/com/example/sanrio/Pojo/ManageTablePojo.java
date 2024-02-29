package com.example.sanrio.Pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManageTablePojo {

    private Integer id;

    @NotNull
    private String tableName;

    @NotNull
    private String status;
}
