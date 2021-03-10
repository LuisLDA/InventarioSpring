package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.domain.Product;
import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.Media;

//    ____                   _               _
//   |  _ \  _ __  ___    __| | _   _   ___ | |_  ___   ___
//   | |_) || '__|/ _ \  / _` || | | | / __|| __|/ _ \ / __|
//   |  __/ | |  | (_) || (_| || |_| || (__ | |_| (_) |\__ \
//   |_|    |_|   \___/  \__,_| \__,_| \___| \__|\___/ |___/
//

@Controller
@Slf4j
@RequestMapping("/productos") //localhost:8080/productos/
public class ControladorProductos {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/editar_producto/{id}")
    public String editarProducto(Product product, Model model) {
        log.info("Accediendo a editar producto");
        product = productService.searchProduct(product);
        model.addAttribute("product", product);
        var categories = categoriesService.listCategories();
        model.addAttribute("categories", categories);
        return "editar_producto";
    }

    @GetMapping("/agregar_productos")
    public String agregarProductos(Model model) {
        log.info("Accediendo a agregar productos");

        return "agregar_productos";
    }

    @GetMapping("/eliminar_producto/{id}")
    public String eliminarProducto(Model model) {
        log.info("Accediendo a eliminar producto");

        return "redirect:/productos";
    }

}
