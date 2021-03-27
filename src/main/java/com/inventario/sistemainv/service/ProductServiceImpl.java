package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.ProductsDao;
import com.inventario.sistemainv.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductsDao productsDao;

    List<Product> productList;

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProduct() {
        return (List<Product>) productsDao.findAll();
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productsDao.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productsDao.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product searchProduct(Product product) {
        return productsDao.findById(product.getId()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public int countProducts() {
        return (int) productsDao.count();
    }

    @Override
    public String searchNameProd(String name) {
        return productsDao.searchNameProd(name);
    }

    @Override
    public List<Product> productRecient() {
        return productsDao.productRecient();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> names_products(){
        List<String> namesProducts = new ArrayList<>();
        List<Product> productos = listProduct();
        productos.forEach(list -> namesProducts.add(list.getName()));
        return namesProducts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> id_products(){
        List<String> idProducts = new ArrayList<>();
        List<Product> productos = listProduct();
        productos.forEach(list -> idProducts.add(list.getId().toString()));
        return idProducts;
    }

    @Override
    @Transactional(readOnly = true)
    public Product saleProduct(String name){
        productList = (List<Product>) productsDao.findAll();
        for (Product pr: productList) {
            if(name.equalsIgnoreCase(pr.getName())){
                return pr;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ArrayList<String>> mostSales() {
        return productsDao.mostSales();
    }

}
