package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.dao.ProductsDao;
import com.inventario.sistemainv.domain.Product;
import com.inventario.sistemainv.service.*;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


//    ___  _   _  ___  ____  ___  ___
//   |_ _|| \ | ||_ _|/ ___||_ _|/ _ \
//    | | |  \| | | || |     | || | | |
//    | | | |\  | | || |___  | || |_| |
//   |___||_| \_||___|\____||___|\___/
//

@Controller
@Slf4j
@RequestMapping("/")  //localhost:8080/
public class ControladorInicio {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MediaService mediaService;

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("INICIANDO EL CONTROLADOR...");
        var usuarios = userService.listUser();
        model.addAttribute("users", usuarios);
        return "home";
    }

    @GetMapping("/media")
    public String media(Model model) {
        return "media";
    }

    @GetMapping("/accesos")
    public String controlAcceso(Model model) {
        log.info("Acceso a grupos");
        var grupos = userGroupService.listGroup();
        model.addAttribute("groups",grupos);
        return "accesos_grupos";
    }

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        var categorias = categoriesService.listCategories();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        log.info("Accediendo a productos");
        var productos = productService.listProduct();

        for (Product cat: productos){
            Long id_cat = Long.valueOf(cat.getCategorie_id());
            cat.setCategorie(productService.categorieProduct(id_cat));
        }
        for (Product c: productos){
            System.out.println("categorias:"+c.getCategorie());
        }
        model.addAttribute("productos", productos);
        return "productos";
    }


    @GetMapping("/index")
    public String inicioLogin(Model model) {
        return "index";
    }

}
