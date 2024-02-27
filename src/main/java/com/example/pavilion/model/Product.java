package com.example.pavilion.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String price;
    private String city;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private Image image;
    private Long previewImageId;
    private String photo;
    private LocalDateTime dateOfCreated;

    @PrePersist
    public void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImage(Image image2) {
        this.image = image2;
        image2.setProduct(this);
    }
}
