package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.domain.Product;
import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.MediaService;
import com.inventario.sistemainv.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


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

    @Autowired
    private MediaService mediaService;

    @GetMapping("/editar_producto/{id}")
    public String editarProducto(Product product, Model model) {
        log.info("Accediendo a editar producto");
        product = productService.searchProduct(product);
        log.info("Producto a editar:" + product);
        model.addAttribute("product", product);
        var categoriesEdit = categoriesService.listCategories();
        model.addAttribute("categoriesEdit", categoriesEdit);
        var mediaEdit = mediaService.listMedia();
        model.addAttribute("mediaEdit", mediaEdit);
        return "editar_producto";
    }

    @GetMapping("/agregar_productos")
    public String mostrarAgregarProductos(Model model, Map<String, Object> map) {
        log.info("Accediendo a producto");
        var categories = categoriesService.listCategories();
        model.addAttribute("categories", categories);
        log.info("Categorias: " + categories);
        var media = mediaService.listMedia();
        model.addAttribute("media", media);
        return "agregar_productos";
    }

    @PostMapping("/agregar_nuevo_producto")
    public String agregarProductos(Product product, Model model, RedirectAttributes flash) {
        model.addAttribute("pageTitle", "Productos");
        log.info("Agregando el producto " + product);
        try {
            if(product.getId() != null){ //producto modificado
                productService.saveProduct(product);
                flash.addFlashAttribute("success", "El producto se modificó con éxito!.");
            }else { //porducto nuevo
                var registred = productService.registred(product);
                if(!registred){
                    log.info("Se ha agregado un nuevo producto");
                    log.info("Contador de productos " + productService.countProducts());
                    flash.addFlashAttribute("success", "El producto " + product.getName() + " ha sido agregado con éxito.");
                    product.setModified_date(null);
                    productService.saveProduct(product);
                }else {
                    flash.addFlashAttribute("error", "El producto ya se encuentra registrado.");
                }
            }
        }catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error", "El producto ya se encuentra registrado.");
        }
        return "redirect:/productos";
    }

    @GetMapping("/eliminar_producto/{id}")
    public String eliminarProducto(Product product, RedirectAttributes flash) {
        log.info("Se eliminino el producto: " + product);
        productService.deleteProduct(product);
        flash.addFlashAttribute("success", "El producto se eliminó con éxito!.");
        return "redirect:/productos";
    }

}
