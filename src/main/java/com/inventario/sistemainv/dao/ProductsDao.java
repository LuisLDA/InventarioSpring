package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductsDao extends CrudRepository <Product, Long> {

    @Query(value = "SELECT name FROM products p where name= ?1", nativeQuery = true)
    public String searchNameProd(String name);
}
