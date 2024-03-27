package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int area;
    private long population;
    private int gdp;
    private String description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
