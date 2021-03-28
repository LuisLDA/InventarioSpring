package com.inventario.sistemainv.service;

import com.inventario.sistemainv.domain.Ventas;

import java.util.List;


public interface VentasService {
    public List<Ventas> listVentas();
    public void saveVentas(Ventas ventas);
    public void deleteVentas(Ventas ventas);
    public Ventas searchVentas(Ventas ventas);
    public int countVentas();
    public List<Ventas> latestSales();

    //public List<String> producto_media();
}
