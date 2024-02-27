package com.example.pavilion.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.pavilion.model.Product;
import com.example.pavilion.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", service.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/add")
    public String save(@RequestParam MultipartFile image, @RequestParam String name, @RequestParam String city, @RequestParam String description, @RequestParam String price) throws IOException {
        Product product = new Product();
        product.setTitle(name);
        product.setCity(city);
        product.setDescription(description);
        product.setPrice(price);
        service.save(product, image);
        return "redirect:/";
    }
}
