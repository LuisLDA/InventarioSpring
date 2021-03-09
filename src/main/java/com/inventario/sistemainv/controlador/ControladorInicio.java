package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.UserGroupService;
import com.inventario.sistemainv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
