package com.example.sanrio.Pojo;

public class BookTablePojo {

    private int number;
    private int capacity;
    private boolean reserved;

    // Constructors, getters, setters, and other methods

    // Default constructor
    public BookTablePojo() {
    }

    // Parameterized constructor
    public BookTablePojo(int number, int capacity, boolean reserved) {
        this.number = number;
        this.capacity = capacity;
        this.reserved = reserved;
    }

    // Getters and setters

    // toString method (for debugging/logging)
    @Override
    public String toString() {
        return "BookTablePojo{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", reserved=" + reserved +
                '}';
    }

    // Other methods as needed
}
