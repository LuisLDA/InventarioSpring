package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProductsDao extends CrudRepository <Product, Long> {

    @Query(value = "SELECT name FROM products p where name= ?1", nativeQuery = true)
    String searchNameProd(String name);

    @Query(value = "SELECT * FROM products p ORDER BY p.id DESC LIMIT 3", nativeQuery = true)
    List<Product> productRecient();

    @Query(value = "SELECT p.name, COUNT(s.product_id) AS totalSold, SUM(s.qty) AS totalQty FROM sales s LEFT JOIN products p ON p.id = s.product_id GROUP BY s.product_id ORDER BY SUM(s.qty) DESC LIMIT 10;", nativeQuery = true)
    ArrayList<ArrayList<String>> mostSales();
}
