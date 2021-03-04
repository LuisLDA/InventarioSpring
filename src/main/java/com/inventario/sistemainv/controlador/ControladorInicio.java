package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.catalina.User;
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
        model.addAttribute("users", usuarios);
        //EJEMPLO PARA TRAER LOS VALORES DEL ARRAY
        //var productos = productService.listProduct();
        //model.addAttribute("products",productos);
        return "home";
    }

    @GetMapping("/media")
    public String media(Model model) {
        return "media";
    }

    @GetMapping("/accesos_usuarios")
    public String controlAcceso(User user) {
        return "accesos_grupos";
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        return "productos";
    }

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        var categorias = categoriesService.listCategories();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/index")
    public String inicioLogin(Model model) {
        return "index";
    }
}
