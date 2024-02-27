package com.example.pavilion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pavilion.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
