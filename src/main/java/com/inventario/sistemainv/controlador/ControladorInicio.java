package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.*;
import com.inventario.sistemainv.dao.ProductsDao;
import com.inventario.sistemainv.domain.Product;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
    private CategoriesService categoriesService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails user2auth) {
        model.addAttribute("pageTitle", "Home");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("INICIANDO EL CONTROLADOR DE INICIO...");
        log.info("USUARIO LOGEADO: " + user2auth);
        log.info("LOGIN A LAS:"+formatter.format(date));
        //Control acceso  y actualización de fecha de login
        var userLoginupdate=userService.searchbyUserName(user2auth.getUsername());
        userLoginupdate.setLast_login(formatter.format(date));
        userService.saveUser(userLoginupdate);
        //Permisos
        var user_group = user2auth.getAuthorities();
        model.addAttribute("user_group", user_group.toString());
        // var usuarios = userService.listUser();
        //model.addAttribute("users", usuarios);
        return "home";
    }

    @GetMapping("/media")
    public String media(Map<String, Object> model, Model models) {
        models.addAttribute("pageTitle", "Media");
        log.info("INICIANDO EL CONTROLADOR MEDIA...");
        var media = mediaService.listMedia();
        log.info("Recuperacion data media: " + media);
        model.put("media", media);
        return "media";
    }

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        model.addAttribute("pageTitle", "Categorias");
        var categorias = categoriesService.listCategories();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        model.addAttribute("pageTitle", "Productos");
        log.info("Accediendo a productos");
        var producto = productService.listProduct();
        log.info("List product: " + producto);
        model.addAttribute("producto", producto);
        return "productos";
    }

    @GetMapping("/panel_control")
    public String panelControl(Model model) {
        model.addAttribute("pageTitle", "Panel de Control");
        var countCat = categoriesService.countCategories();
        model.addAttribute("countCat", countCat);
        var countProd = productService.countProducts();
        model.addAttribute("countProd", countProd);
        var productRecient = productService.productRecient();
        model.addAttribute("productRecient", productRecient);
        log.info("Product recient: " + productRecient);
        return "panel_control";
    }

    @GetMapping("/index")
    public String inicioLogin(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "index";
    }

}
