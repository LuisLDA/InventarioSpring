package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.UserGroupService;
import com.inventario.sistemainv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String inicio(Model model, @AuthenticationPrincipal UserDetails user2auth) {
        model.addAttribute("pageTitle","Home");
        log.info("INICIANDO EL CONTROLADOR DE INICIO...");
        log.info("USUARIO LOGEADO: "+user2auth);
        var user_group =user2auth.getAuthorities();
        model.addAttribute("user_group",user_group.toString());
        return "home";
    }

    @GetMapping("/media")
    public String media(Model model) {
        model.addAttribute("pageTitle","Media");
        return "media";
    }

    @GetMapping("/accesos")
    public String controlAcceso(Model model) {
        model.addAttribute("pageTitle","Accesos");
        log.info("Acceso a grupos");
        var grupos = userGroupService.listGroup();
        model.addAttribute("groups",grupos);
        return "accesos_grupos";
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        model.addAttribute("pageTitle","Productos");
        return "productos";
    }

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        model.addAttribute("pageTitle","Categorias");
        var categorias = categoriesService.listCategories();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/index")
    public String inicioLogin(Model model) {
        model.addAttribute("pageTitle","Login");
        return "index";
    }

}
