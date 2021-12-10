package com.gb.balyanova.springdata.repositories;


import com.gb.balyanova.springdata.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findAllByPriceBetween(Integer min, Integer max);

    Optional<Product> findByTitle(String title);

    @Query("select p from Product p where p.price < 100")
    List<Product> findLowPriceProducts(Integer min);

    @Query("select p from Product p where price > 1000")
    List<Product> findMoreThanValue(Integer max);

//    @Query("select p from Product p where [.title = ?1") //поиск по первому аргументу
//    Integer hqlGetPriceByTitle(String title);
//
//    @Query(value = "select price from product where name = :title", nativeQuery = true) //поиск по первому аргументу
//    Integer nativeSqlGetPriceByTitle(String title);
}
