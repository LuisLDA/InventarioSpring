package com.inventario.sistemainv.controlador;

import com.inventario.sistemainv.domain.Categories;
import com.inventario.sistemainv.service.CategoriesService;
import com.inventario.sistemainv.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoriesService categoriesService;

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

    @GetMapping("/accesos_usuarios")
    public String controlAcceso(User user) {
        return "accesos_grupos";
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        return "productos";
    }

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        var categorias = categoriesService.listCategories();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/index")
    public String inicioLogin(Model model) {
        return "index";
    }

    @GetMapping("/eliminar_categoria/{id}")
    public String eliminarCategoria(Categories categories){
        log.info("Se eliminino el producto: "+categories);
        categoriesService.deleteCategories(categories);
        return "redirect:/categorias";
    }

    @GetMapping("/editar_categoria/{id}")
    public String editarCategoria(Categories categories,Model model){
        log.info("Se va editar el producto: "+categories);
        categories = categoriesService.searchCategories(categories);
        model.addAttribute("categoria",categories);
        return "edit_categorias";
    }

    @PostMapping("/add_categoria")
    public String agregarCategoria(Categories categoria_new){
        log.info("Agregado el producto :"+categoria_new);
        categoriesService.saveCategories(categoria_new);
        return "redirect:/categorias";
    }

    @PostMapping("/save_categoria")
    public String saveCategoria(Categories categoria_new){
        log.info("Se ha editado el producto :"+categoria_new);
        categoriesService.saveCategories(categoria_new);
        return "redirect:/categorias";
    }

}
