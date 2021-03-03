package com.inventario.sistemainv;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@Controller

public class ControladorInicio {

    @GetMapping("/")
    public String inicio(Model model) {
        //log.info("inicio index");
        return "home";
    }
}
