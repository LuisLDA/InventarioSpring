package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.service.VentasService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/reportes")
public class ControladorReportes {

    public static String fechaInicio = "";
    public static String fechaFin = "";

    @Autowired
    private VentasService ventasService;

    @PostMapping("/reporteFecha")
    public String reporteFechaDatos(@RequestParam(name = "start-date") String date1, @RequestParam(name = "end-date") String date2,
                                    RedirectAttributes flash) {
        log.info("Rango de datos fechas: " + date1 + " a " + date2);
        fechaInicio=date1;
        fechaFin=date2;
        log.info("fecha inicio: " + fechaInicio);
        log.info("fecha fin: " + fechaFin);
        return "redirect:/reportes/reportes_fecha";
    }

    @GetMapping("/reportes_fecha")
    public String reporte_fecha(Model model, RedirectAttributes flash) {
        log.info("Rango de fechas: " + fechaInicio + " a " + fechaFin);
        String error = "No existen registros para la consulta.";
        var ventasfecha = ventasService.searchSalesByDate(fechaInicio, fechaFin);
        if (ventasfecha != null || !ventasfecha.isEmpty()) {
            log.info("Ventas: " + ventasfecha);
            model.addAttribute("ventasfecha", ventasfecha);
        } else {
            model.addAttribute("error", error);
        }
        return "reportes_fecha";
    }
}