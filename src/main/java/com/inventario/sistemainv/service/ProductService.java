package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Product;
import java.util.List;

public interface ProductService {
    List<Product> listProduct();
    void saveProduct(Product product);
    void deleteProduct(Product product);
    Product searchProduct(Product product);
    int countProducts();
    String searchNameProd(String name);
    List<Product> productRecient();
    List<String> names_products();
    List<String> id_products();
    Product saleProduct(String producto);
}
