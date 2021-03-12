package com.inventario.sistemainv;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //Para agregar recursos estaticos a nuestro proyecto
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        WebMvcConfigurer.super.addResourceHandlers(registry);

        /*
            Para registrar la ruta de manera estatica
            ** = para mapear al nombre de la imagen
         */
        String resourcePath = Paths.get("imagenes").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/imagenes/**").addResourceLocations(resourcePath);


    }
}
