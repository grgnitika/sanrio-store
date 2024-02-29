package com.example.sanrio.Entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name="book_table")
public class BookTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private int capacity;
    @Getter
    private boolean reserved;

    // Constructors, getters, setters, and other methods
    // Default constructor (required by JPA)
    public BookTable() {
    }

    // Parameterized constructor
    public BookTable(int number, int capacity, boolean reserved) {
        this.number = number;
        this.capacity = capacity;
        this.reserved = reserved;
    }

    // Getters and setters

    // toString method (for debugging/logging)
    @Override
    public String toString() {
        return "BookTable{" +
                "id=" + id +
                ", number=" + number +
                ", capacity=" + capacity +
                ", reserved=" + reserved +
                '}';
    }

    public void setReserved(boolean reserved) {
        // Implementation
    }

}
