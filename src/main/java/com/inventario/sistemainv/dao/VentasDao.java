package com.inventario.sistemainv.dao;

import com.inventario.sistemainv.domain.Ventas;
import org.springframework.data.repository.CrudRepository;

public interface VentasDao extends CrudRepository<Ventas,Long> {

}
