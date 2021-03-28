package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Product;
import com.inventario.sistemainv.domain.Ventas;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VentasService {
    List<Ventas> listVentas();
    void saveVentas(Ventas ventas);
    void deleteVentas(Ventas ventas);
    Ventas searchVentas(Ventas ventas);
    int countVentas();

    //public List<String> producto_media();
}
