package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Table_Management")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManageTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="tableName" , nullable=false)
    private String tableName;

    @Column(name="Status", nullable=false)
    private String status;


}
