package com.gb.balyanova.springdata.services;

import com.gb.balyanova.springdata.entities.Product;
import com.gb.balyanova.springdata.exceptions.ResourceNotFoundException;
import com.gb.balyanova.springdata.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changePrice(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to change product`s price, id: " + productId));
        product.setPrice(product.getPrice() + delta);
    }

    public List<Product> findByPriceBetween(Integer min, Integer max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public List<Product> findLowPriceProducts(Integer min) {
        return productRepository.findLowPriceProducts(min);
    }

    public List<Product> findMoreThanValue(Integer max) { return productRepository.findMoreThanValue(max);
    }


}