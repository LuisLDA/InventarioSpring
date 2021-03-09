package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductsDao extends CrudRepository <Product, Long> {

}
