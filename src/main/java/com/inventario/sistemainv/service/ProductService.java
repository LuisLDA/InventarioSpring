package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Product;
import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    public List<Product> listProduct();
    public void saveProduct(Product product);
    public void deleteProduct(Product product);
    public Product searchProduct(Product product);

    public int countProducts();
    public String searchNameProd(String name);
    public List<Product> productRecient();

    public List<String> names_products();
    public List<String> id_products();
    public Product saleProduct(String producto);
    public ArrayList<ArrayList<String>> mostSales();
    public Product stockAvaliable(Product product);
    public boolean registred(Product product);
}
