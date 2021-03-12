package com.inventario.sistemainv.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.inventario.sistemainv.domain.Media;

public interface MediaDao extends CrudRepository<Media, Long> {

    @Query(value = "SELECT * FROM media m WHERE m.id = ?1", nativeQuery = true)
    public Media mediaporid(Long id);
}
