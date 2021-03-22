package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String eliminarCategoria(Categories categories, RedirectAttributes flash) {
        log.info("Se eliminino el grupo: " + categories);
        categoriesService.deleteCategories(categories);
        flash.addFlashAttribute("success", "La categoría se eliminó con éxito!.");
        return "redirect:/categorias";
    }

    @GetMapping("/editar_categoria/{id}")
    public String editarCategoria(@Validated Categories categories, Model model, Errors errors, RedirectAttributes flash) {
        model.addAttribute("pageTitle","Editar Categoria");
        log.info("Se va editar el producto: " + categories);
        categories = categoriesService.searchCategories(categories);
        model.addAttribute("categoria", categories);
        return "edit_categorias";
    }

    @PostMapping("/add_categoria")
    public String agregarCategoria(@Validated Categories categoria_new, Errors errors, RedirectAttributes flash) {

        try {
            log.info("Agregado la categoria :" + categoria_new);
            var name = categoriesService.searchNameCat(categoria_new.getName());
            if (!categoria_new.getName().equalsIgnoreCase(name)) {
                if (categoria_new.getId() == null){
                    flash.addFlashAttribute("success", "La categoría " + categoria_new.getName() + " ha sido agregada con éxito.");
                } else {
                    flash.addFlashAttribute("success", "La categoría se modificó con éxito!.");
                }
            }
            categoriesService.saveCategories(categoria_new);
            return "redirect:/categorias";
        }catch (DataIntegrityViolationException e){
            log.error("ERROR AL AGREGAR O MODIFICAR",e);
            flash.addFlashAttribute("error", "La categoría ya se encuentra registrada.");
            return "redirect:/categorias";
        }

    }



}
