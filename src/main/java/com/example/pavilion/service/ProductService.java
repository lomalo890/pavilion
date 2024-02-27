package com.example.pavilion.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pavilion.model.Image;
import com.example.pavilion.model.Product;
import com.example.pavilion.repository.ImageRepository;
import com.example.pavilion.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;


    public void save(Product product, MultipartFile file) throws IOException {
        Image image = toImageEntity(file);
        product.addImage(image);
        product.setPhoto(image.getFilePath().replace("/home/loudviper8815726/programmirovanie/pavilion/src/main/resources/static/", ""));
        productRepository.save(product);
        image.setProduct(product);
        imageRepository.save(image);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        String name = file.getOriginalFilename();
        String pathFolder = "/home/loudviper8815726/programmirovanie/pavilion/src/main/resources/static/images";
        File folder = new File(pathFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = folder + "/" + name;
        try (OutputStream os = new FileOutputStream(filePath)) {
            os.write(file.getBytes());
        }
        image.setName(name);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setFilePath(filePath);
        image.setBytes(file.getBytes());
        return image;
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }
}
