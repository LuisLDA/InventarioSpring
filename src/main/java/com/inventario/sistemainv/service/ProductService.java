package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Product;
import java.util.List;

public interface ProductService {
    public List<Product> listProduct();
    public void saveProduct(Product product);
    public void deleteProduct(Product product);
    public Product searchProduct(Product product);

    public int countProducts();
    public String searchNameProd(String name);
    public List<Product> productRecient();

}
