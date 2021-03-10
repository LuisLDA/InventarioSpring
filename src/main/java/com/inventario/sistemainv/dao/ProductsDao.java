package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductsDao extends CrudRepository <Product, Long> {
    @Query(value = "SELECT name from categories where id = ?1",nativeQuery = true)
    public String categorieProduct(Long identificador);
}
