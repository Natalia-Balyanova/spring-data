package com.gb.balyanova.springdata.converter;

import com.gb.balyanova.springdata.dto.JwtRequest;
import com.gb.balyanova.springdata.dto.ProductDto;
import com.gb.balyanova.springdata.entities.Product;
import com.gb.balyanova.springdata.entities.User;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
    public User jwtRequestToUser(JwtRequest jwtRequest) {
        return new User(jwtRequest.getUsername(), jwtRequest.getPassword());
    }
}
