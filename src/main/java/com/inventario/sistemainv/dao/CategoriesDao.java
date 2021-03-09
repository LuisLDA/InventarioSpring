package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface CategoriesDao extends CrudRepository<Categories, Long> {

    @Query(value = "SELECT count(*) from categories",nativeQuery = true)
    public int countCategories();

}
