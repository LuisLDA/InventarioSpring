package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> listCategories();
    void saveCategories(Categories categories);
    void deleteCategories(Categories categories);
    Categories searchCategories(Categories categories);
    int countCategories();
    String searchNameCat(String name);

}
