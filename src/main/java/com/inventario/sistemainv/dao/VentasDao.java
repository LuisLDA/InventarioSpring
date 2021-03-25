package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Ventas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VentasDao extends CrudRepository<Ventas,Long> {

    @Query(value = "SELECT * FROM sales s WHERE s.date between ?1 and ?2", nativeQuery = true)
    public List<Ventas> searchSalesByDate(String date1, String date2);
}
