package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsDao extends CrudRepository <Product, Long> {
    @Query(value = "SELECT name from categories where id = ?1",nativeQuery = true)
    public String categorieProduct(Long identificador);

    @Query(value = "SELECT m.file_name FROM products JOIN media m ON p.media_id = m.id", nativeQuery = true)
    public List<String> producto_media();
}
