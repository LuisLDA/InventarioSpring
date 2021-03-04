package com.inventario.sistemainv.controlador;

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
    public String controlAcceso(User user) {
        return "accesos";
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        return "productos";
    }

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        return "categorias";
    }
}
