package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Categories;

import java.util.List;

public interface CategoriesService {
    public List<Categories> listCategories();
    public void saveCategories(Categories categories);
    public void deleteCategories(Categories categories);
    public Categories searchCategories(Categories categories);
    public int countCategories();
    public String searchNameCat(String name);

}
