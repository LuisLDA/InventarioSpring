package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.CategoriesDao;
import com.inventario.sistemainv.domain.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService{

    @Autowired
    private CategoriesDao categoriesDao;


    @Override
    @Transactional(readOnly = true)
    public List<Categories> listCategories() {
        return (List<Categories>) categoriesDao.findAll();
    }

    @Override
    public void saveCategories(Categories categories) {
        categoriesDao.save(categories);
    }

    @Override
    public void deleteCategories(Categories categories) {
        categoriesDao.delete(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public Categories searchCategories(Categories categories) {
        return categoriesDao.findById(categories.getId()).orElse(null);
    }
}
