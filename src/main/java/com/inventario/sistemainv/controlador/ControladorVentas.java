package com.inventario.sistemainv.controlador;
import com.inventario.sistemainv.domain.Product;
import com.inventario.sistemainv.domain.Ventas;
import com.inventario.sistemainv.service.ProductService;
import com.inventario.sistemainv.service.VentasService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//   __     __            _
//   \ \   / /___  _ __  | |_  __ _  ___
//    \ \ / // _ \| '_ \ | __|/ _` |/ __|
//     \ V /|  __/| | | || |_| (_| |\__ \
//      \_/  \___||_| |_| \__|\__,_||___/
//

@Controller
@Slf4j
@RequestMapping("/ventas")
public class ControladorVentas {

    @Autowired
    private VentasService ventasService;

    @Autowired
    private ProductService productService;

    @GetMapping("/seleccionar_venta")
    public String buscarProductosVenta(Model model) {
        var produ = productService.listProduct();
        log.info("Se han encontrado los siguientes productos: "+produ);
        //Lista que recupera unicamente los nombres
        var namesProducts = (productService.names_products()).toString();
        String namesString = convertToString(namesProducts);
        model.addAttribute("namesString", namesString);
        log.info("String de productos: "+namesString);
        //Recupera los id's de los productos
        var idProducts = (productService.id_products()).toString();
        String idString = convertToString(idProducts);
        model.addAttribute("idString", idString);
        log.info("String de id's: "+idString);
        return "agregar_ventas";
    }


    @PostMapping("/agregar_ventas")
    public String ConseguirProducto(@Validated String name, Model model) {
        try {
            //Para que se pueda realizar nuevamente la busqueda
            var namesProducts = (productService.names_products()).toString();
            String namesString = convertToString(namesProducts);
            model.addAttribute("namesString", namesString);
            var idProducts = (productService.id_products()).toString();
            String idString = convertToString(idProducts);
            model.addAttribute("idString", idString);
            //Trae el producto espeficico a vender
            log.info("Buscando producto "+ name +" para vender");
            var productSale = productService.saleProduct(name);
            log.info("Producto encontrado: "+ productSale);
            model.addAttribute("productoSale",productSale);
            return "agregar_ventas";
        }catch (DataIntegrityViolationException e){
            log.error("Error al agregar el producto para vender",e);
            return "redirect:/ventas/seleccionar_venta";
        }
    }

    @GetMapping("/producto_venta")
    public String mostrarProducto(Product product, Model model){
        product = productService.searchProduct(product);
        model.addAttribute("product",product);
        return "agregar_ventas";
    }

    @PostMapping("/guardar_venta")
    public String agregarVenta(@Validated Ventas new_venta) {
        try {
            ventasService.saveVentas(new_venta);
            log.info("Se ha agregado la venta: "+new_venta);
            return "agregar_ventas";
        }catch (DataIntegrityViolationException e){
            log.error("ERROR AL AGREGAR O MODIFICAR",e);
            return "redirect:/ventas/agregar_ventas";
        }
    }

    @GetMapping("/eliminar_venta/{id}")
    public String eliminarVenta(Ventas ventas){
        log.info("Se eliminino la venta: " + ventas);
        ventasService.deleteVentas(ventas);
        return "redirect:/ventas";
    }

    @GetMapping("/editar_venta/{id}")
    public String editarVenta(@Validated Ventas ventas, Model model){
        model.addAttribute("pageTitle","Editar Venta");
        ventas = ventasService.searchVentas(ventas);
        model.addAttribute("ventas", ventas);
        log.info("Se va editar la venta: " + ventas);
        return "editar_venta";
    }

    public String convertToString(String cadena){
        String str =(((cadena.replace("[", "")).replaceAll("\\s","")).replace("]", ""));
        return str;
    }
}