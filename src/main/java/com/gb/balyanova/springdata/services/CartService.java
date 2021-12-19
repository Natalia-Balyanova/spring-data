package com.gb.balyanova.springdata.services;

import com.gb.balyanova.springdata.dto.ProductDto;
import com.gb.balyanova.springdata.entities.Product;
import com.gb.balyanova.springdata.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private List <ProductDto> cart = new ArrayList<>();
    //попытка сделать корзину хэшмапой не увенчалось успехом
//    private Map<ProductDto, Integer> cart = new LinkedHashMap<>();

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void addProductInCart (Long id){
        cart.add(new ProductDto(findById(id).get()));
    }

    public List<ProductDto> cardInfo (){
        return cart;
    }

    public void deleteByIdFromCartId(Long id) {
        cart.remove(new ProductDto(findById(id).get()));
    }
//    public void addProductInCart (Long id, Integer count){
//        cart.put(new ProductDto(findById(id).get()), count);
//    }
//
//    public Map<ProductDto, Integer> cardInfo (){
//        return cart;
//    }
//    @Transactional
//    public void changeAmount(Long productId, Integer amount) {
//        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to change product`s amount, id: " + productId));
//        product.setAmount(product.getAmount() + amount);
//    }
}
