package com.example.pavilion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pavilion.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
