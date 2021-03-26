package com.inventario.sistemainv.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
public class ControladorErrores implements ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public String handleError(@RequestHeader("Accept") String accept, HttpServletRequest request,
                              WebRequest webRequest, Model model) {
        int status = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("error_status", status);

        if (HttpStatus.NOT_FOUND.value() == status) {
            model.addAttribute("message_status", "Página no encontrada");
            model.addAttribute("response_status", "La página solicitada no existe o no se encuentra activa");
            return "/errores/error";
        } else if (HttpStatus.FORBIDDEN.value() == status) {
            model.addAttribute("message_status", "Acceso Denegado");
            model.addAttribute("response_status", "No tienes permisos para ver esta página o ejecutar esta acción");
            return "/errores/error";
        } else {
            Map<String, Object> mapErrors = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));
            String mensajeError = (String) mapErrors.get("error");
            log.info("HA OCURRIDO UN ERROR:"+mensajeError);
            model.addAttribute("message_status", mensajeError);
            model.addAttribute("response_status", "Ha ocurrido un error disculpa las molestias");
            return "/errores/error";
        }
    }

    @Override
    public String getErrorPath() {
        return "/errores/";
    }
}
