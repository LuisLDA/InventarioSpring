package com.inventario.sistemainv.service;

import com.inventario.sistemainv.dao.VentasDao;
import com.inventario.sistemainv.domain.Ventas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VentasServiceImpl implements VentasService{

    @Autowired
    private VentasDao ventasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> listVentas() {
        return (List<Ventas>) ventasDao.findAll();
    }

    @Override
    @Transactional
    public void saveVentas(Ventas ventas) {
        ventasDao.save(ventas);
    }

    @Override
    public void deleteVentas(Ventas ventas) {
        ventasDao.delete(ventas);
    }

    @Override
    @Transactional(readOnly = true)
    public Ventas searchVentas(Ventas ventas) {
        if(ventas.getId() != null){
            return ventasDao.findById(ventas.getId()).orElse(null);
        }else{
            return null;
        }
    }

    @Override
    public int countVentas() {
        return (int) ventasDao.count();
    }

    @Override
    public List<Ventas> searchSalesByDate(String date1, String date2) {
        return ventasDao.searchSalesByDate(date1, date2);
    }

    @Override
    public ArrayList<Long> RelatedSales() {
        log.info("String de id de las ventas: "+ventasDao.RelatedSales().toString());
        return ventasDao.RelatedSales();
    }

    @Transactional(readOnly = true)
    public List<Ventas> latestSales(){
        return ventasDao.latestSales();
    }


}
