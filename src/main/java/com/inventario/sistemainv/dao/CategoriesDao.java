package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesDao extends CrudRepository<Categories, Long> {

}
