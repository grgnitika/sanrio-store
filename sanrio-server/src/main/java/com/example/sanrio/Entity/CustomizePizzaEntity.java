package com.example.sanrio.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class CustomizePizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double basePrice = 800; // Updated base price to 800 Rs
    private boolean addExtraCheese;
    private boolean addExtraMeat;
    private boolean addExtraMozzarella;
    private boolean addExtraBasil;
    private boolean addExtraVeggies;

    // Getters, Setters, Constructors

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isAddExtraCheese() {
        return addExtraCheese;
    }

    public void setAddExtraCheese(boolean addExtraCheese) {
        this.addExtraCheese = addExtraCheese;
    }

    public boolean isAddExtraMeat() {
        return addExtraMeat;
    }

    public void setAddExtraMeat(boolean addExtraMeat) {
        this.addExtraMeat = addExtraMeat;
    }

    public boolean isAddExtraMozzarella() {
        return addExtraMozzarella;
    }

    public void setAddExtraMozzarella(boolean addExtraMozzarella) {
        this.addExtraMozzarella = addExtraMozzarella;
    }

    public boolean isAddExtraBasil() {
        return addExtraBasil;
    }

    public void setAddExtraBasil(boolean addExtraBasil) {
        this.addExtraBasil = addExtraBasil;
    }

    public boolean isAddExtraVeggies() {
        return addExtraVeggies;
    }

    public void setAddExtraVeggies(boolean addExtraVeggies) {
        this.addExtraVeggies = addExtraVeggies;
    }
}
