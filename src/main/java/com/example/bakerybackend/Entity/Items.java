package com.example.bakerybackend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name="items")
@Data
public class Items {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    public int id;

    @Column(name="name")
    public String name;

    @Column(name="price")
    public double price;

    @Column(name="category")
    public String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
