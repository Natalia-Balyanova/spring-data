package com.gb.balyanova.springdata.validators;

import com.gb.balyanova.springdata.dto.ProductDto;
import com.gb.balyanova.springdata.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if(productDto.getPrice() < 1) {
            errors.add("product's price cannot be less than 1");
        }
        if(productDto.getTitle().isBlank()) {
            errors.add("product's title cannot be empty or contain space");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
