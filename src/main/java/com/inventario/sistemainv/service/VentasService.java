package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Ventas;

import java.util.List;


public interface VentasService {
    List<Ventas> listVentas();
    void saveVentas(Ventas ventas);
    void deleteVentas(Ventas ventas);
    Ventas searchVentas(Ventas ventas);
    int countVentas();
    List<Ventas> latestSales();

    //public List<String> producto_media();
}
