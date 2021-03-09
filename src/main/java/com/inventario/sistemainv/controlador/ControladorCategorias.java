package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//     ____        _                             _
//    / ___| __ _ | |_  ___   __ _   ___   _ __ (_)  __ _  ___
//   | |    / _` || __|/ _ \ / _` | / _ \ | '__|| | / _` |/ __|
//   | |___| (_| || |_|  __/| (_| || (_) || |   | || (_| |\__ \
//    \____|\__,_| \__|\___| \__, | \___/ |_|   |_| \__,_||___/
//                           |___/


@Controller
@Slf4j
@RequestMapping("/categorias") //localhost:8080/categorias/
public class ControladorCategorias {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/eliminar_categoria/{id}")
    public String eliminarCategoria(Categories categories) {
        log.info("Se eliminino el grupo: " + categories);
        categoriesService.deleteCategories(categories);
        return "redirect:/categorias";
    }

    @GetMapping("/editar_categoria/{id}")
    public String editarCategoria(Categories categories, Model model) {
        log.info("Se va editar el producto: " + categories);
        categories = categoriesService.searchCategories(categories);
        model.addAttribute("categoria", categories);
        return "edit_categorias";
    }

    @PostMapping("/add_categoria")
    public String agregarCategoria(Categories categoria_new) {
        log.info("Agregado el producto :" + categoria_new);
        categoriesService.saveCategories(categoria_new);
        log.info("CONTADOR CATEGORIAS: "+categoriesService.countCategories() );
        return "redirect:/categorias";
    }



}
