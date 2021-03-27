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
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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


@ControllerAdvice
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
    @Autowired
    private VentasService ventasService;


    @ModelAttribute("imageAvatar")
    public String test(@AuthenticationPrincipal UserDetails user2auth) {
        if (user2auth != null) {
            var userImg = userService.searchbyUserName(user2auth.getUsername());
            String imageName = "no image.jpg";

            if (userImg.getImage() != null ) {
                if(!userImg.getImage().equals("")){
                    imageName = userImg.getImage();
                }
            }
            log.info("Imagen de usuario:" + imageName);

            return imageName;
        }
        return null;

    }


    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal UserDetails user2auth) {
        model.addAttribute("pageTitle", "Home");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("INICIANDO EL CONTROLADOR DE INICIO...");
        log.info("USUARIO LOGEADO: " + user2auth);
        log.info("LOGIN A LAS:" + formatter.format(date));
        //Control acceso  y actualización de fecha de login
        var userLoginupdate = userService.searchbyUserName(user2auth.getUsername());
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
        var countUsers = userService.countUsers();
        model.addAttribute("countUsers", countUsers);
        var countCat = categoriesService.countCategories();
        model.addAttribute("countCat", countCat);
        var countProd = productService.countProducts();
        model.addAttribute("countProd", countProd);
        var productRecient = productService.productRecient();
        model.addAttribute("productRecient", productRecient);
        var latestSales = ventasService.latestSales();
        model.addAttribute("latestSales", latestSales);
        var ventasCount = ventasService.countVentas();
        model.addAttribute("ventasCount", ventasCount);
        var mostSales = productService.mostSales();
        model.addAttribute("mostSales",mostSales);
        log.info("Product recient: " + productRecient);
        return "panel_control";
    }

    @GetMapping("/ventas")
    public String mostrarVentas(Model model) {
        log.info("Accediendo a ventas");
        model.addAttribute("pageTitle", "Ventas");
        var ventas = ventasService.listVentas();
        log.info("Se han encontrado las siguientes ventas: " + ventas);
        model.addAttribute("ventas", ventas);
        return "ventas";
    }

    @GetMapping("/index")
    public String inicioLogin(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "index";
    }

    @GetMapping("/perfil")
    public String perfil(Model model, @AuthenticationPrincipal UserDetails user2auth) {
        var user = userService.searchbyUserName(user2auth.getUsername());
        model.addAttribute("pageTitle", "Perfil");
        log.info("Accediendo a perfil...");
        if (user.getImage() != null) {
            if (mediaService.searchbyFile_name(user.getImage()) == null) {
                user.setImage("no image.jpg");
            }
        }

        model.addAttribute("usuario", user);
        return "perfil";
    }


}
