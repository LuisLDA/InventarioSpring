package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.ProductService;
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
    private ProductService productService;

    @GetMapping("/")
    public String inicio(Model model) {
        log.info("INICIANDO EL CONTROLADOR...");
        var usuarios = userService.listUser();
        model.addAttribute("users",usuarios);
        //EJEMPLO PARA TRAER LOS VALORES DEL ARRAY
        var productos = productService.listProduct();
        model.addAttribute("products",productos);
        return "home";
    }
}
