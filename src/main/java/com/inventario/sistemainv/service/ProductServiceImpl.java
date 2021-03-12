package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.ProductsDao;
import com.inventario.sistemainv.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductsDao productsDao;

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
    public String categorieProduct(Long identificador) {
        return productsDao.categorieProduct(identificador);
    }

    @Override
    @Transactional(readOnly = true)
    public int countProducts() {
        return (int) productsDao.count();
    }

}
