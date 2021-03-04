package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("INICIANDO EL CONTROLADOR...");
        var usuarios = userService.listUser();
        model.addAttribute("users",usuarios);
        var categoria = categoriesService.listCategories();
        model.addAttribute("categorie", categoria);
        return "home";
    }

    /*
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/")
    public String categorie(Model model) {
        log.info("INICIANDO CONTROLADOR...");
        var categoria = categoriesService.listCategories();
        model.addAttribute("categorie", categoria);
        return "home";
    }*/
}
