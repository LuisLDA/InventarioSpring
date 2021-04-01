package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface CategoriesDao extends CrudRepository<Categories, Long> {

    @Query(value = "SELECT count(*) from categories",nativeQuery = true)
    int countCategories();

    @Query(value = "SELECT name FROM categories WHERE name= ?1", nativeQuery = true)
    String searchNameCat(String name);
}
