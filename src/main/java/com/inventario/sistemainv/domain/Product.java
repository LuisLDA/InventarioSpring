package com.inventario.sistemainv.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String quantity;

    private BigDecimal buy_price;

    private BigDecimal sale_price;

    private int categorie_id;

    private int media_id;

    private String date;

    private String categorie;

    public String getCategorie(int id) {
        return categorie;
    }

    public Long getId() {
        return id;
    }
}
