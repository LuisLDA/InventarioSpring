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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

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

    @GetMapping("/agregar_ventas")
    public String mostrarVentaInicial(Model model, RedirectAttributes flash) {
        //Lista que recupera unicamente los nombres
        String namesString = convertToString((productService.names_products()).toString());
        model.addAttribute("namesString", namesString);
        log.info("String de productos: "+namesString);
        //Recupera los id's de los productos
        String idString = convertToString((productService.id_products()).toString());
        model.addAttribute("idString", idString);
        log.info("String de id's: "+idString);
        return "agregar_ventas";
    }

    @PostMapping("/buscar_ventas")
    public String buscarVenta(@Validated String name, RedirectAttributes flash, Model model){
        //Para que se pueda realizar nuevamente la busqueda
        String namesString = convertToString((productService.names_products()).toString());
        model.addAttribute("namesString", namesString);
        String idString = convertToString((productService.id_products()).toString());
        model.addAttribute("idString", idString);
        try {
            //Trae el producto espeficico a vender
            log.info("Buscando producto "+ name +" para vender");
            var producto = productService.saleProduct(name); //lo busca por nombre
            if(producto != null){ //producto que si existe
                var productSale = validarStock(producto);
                if(productSale != null){//Tiene stock
                    log.info("Producto encontrado: "+ productSale);
                    model.addAttribute("productoSale",productSale);
                    return "agregar_ventas";
                }else{ // NO cuenta con stock
                    flash.addFlashAttribute("error", "El producto "+producto.getName()+" ya no cuenta con existencias en bodega." +
                            "\nActualice su stock o intente con otro producto.");
                    return "redirect:/ventas/agregar_ventas";
                }
            }else{ //producto que no existe
                flash.addFlashAttribute("info", "El producto "+name+ " no se encuentra en registrado. Intentelo nuevamente.");
                return "redirect:/ventas/agregar_ventas";
            }
        }catch (DataIntegrityViolationException e){
            log.error("Error al agregar el producto para vender",e);
            return "redirect:/ventas/agregar_ventas";
        }
    }

    @PostMapping("/guardar_venta")
    public String guardarVenta(@Validated Ventas new_venta, RedirectAttributes flash) {
        try {
            var sale = ventasService.searchVentas(new_venta);
            if(sale != null){ //venta editada
                ventasService.saveVentas(new_venta);
                actualizarStock(new_venta);
                flash.addFlashAttribute("success", "La venta del producto: "+new_venta.getProduct_id().getName()+" ha sido modificada correctamente.");
                log.info("Se ha agregado la venta: "+new_venta);
                return "redirect:/ventas";
            }else{ //nueva venta
                ventasService.saveVentas(new_venta);
                actualizarStock(new_venta);
                flash.addFlashAttribute("success", "La venta del producto: "+new_venta.getProduct_id().getName()+" se ha agregado correctamente.");
                log.info("Se ha agregado la venta: "+new_venta);
                return "redirect:/ventas";
            }
        }catch (DataIntegrityViolationException e){
            log.error("ERROR AL AGREGAR O MODIFICAR",e);
            return "redirect:/ventas/agregar_ventas";
        }
    }

    @GetMapping("/eliminar_venta/{id}")
    public String eliminarVenta(Ventas ventas, RedirectAttributes flash){
        log.info("Se eliminino la venta: " + ventas);
        flash.addFlashAttribute("success", "La venta del producto se ha eliminado correctamente.");
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
        String str =(((cadena.replace("[", "")).replaceAll(",\\s",",")).replace("]", ""));
        return str;
    }

    public void actualizarStock(Ventas ventas){
        Integer cantidad = ventas.getQty();
        BigDecimal saleP = ventas.getProduct_id().getSale_price();
        log.info("El precio del producto unitario es: "+saleP);
        log.info("la cantidad ingresada es: "+cantidad);
        var producto = productService.listProduct();
        Integer qty;
        Integer stock;
        for (var p: producto) {
            if(ventas.getProduct_id().getId().equals(p.getId())){
                qty = p.getQuantityAsInteger();
                stock = qty-cantidad;
                p.setQuantity(stock.toString());
                p.setSale_price(saleP);
                productService.saveProduct(p);
            }
        }
    }

    public Product validarStock(Product product){
        Integer qty = product.getQuantityAsInteger();
        if(qty > 0){
            return product;
        }else{
            return null;
        }
    }
}