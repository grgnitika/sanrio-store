package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reserve")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name" , nullable=false)
    private String name;

    @Column(name="phone" , nullable=false)
    private String phone;

    @Column(name="persons" , nullable=false)
    private Integer persons;

    @Column(name="day" , nullable=false)
    private String day;


    @ManyToOne
    @JoinColumn(name="table_Id")//, referencedColumnName = "id"
    private ManageTable table;

}
