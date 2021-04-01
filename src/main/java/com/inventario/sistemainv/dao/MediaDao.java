package com.inventario.sistemainv.dao;


import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.inventario.sistemainv.domain.Media;
import org.springframework.transaction.annotation.Transactional;

public interface MediaDao extends CrudRepository<Media, Long> {

    @Query(value = "SELECT * FROM media m WHERE m.id = ?1", nativeQuery = true)
    Media mediaporid(Long id);

    @Query(value = "SELECT * FROM media m WHERE m.file_name =:name", nativeQuery = true)
    Media findByFile_Name(@Param("name") String file_name);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE products SET media_id=1 where media_id=?1", nativeQuery = true)
    void actualizarMedia(Long id);
}
