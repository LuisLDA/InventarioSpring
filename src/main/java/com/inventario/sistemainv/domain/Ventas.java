package com.inventario.sistemainv.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "sales")
public class Ventas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_id;

    private Integer qty;

    private BigDecimal price;

    private String date;

    public String getDateFormat() {
        String day = date.substring(8);
        String month = date.substring(5,7);
        String year = date.substring(2,4);
        String new_date = day+"/"+month+"/"+year;
        return new_date;
    }
}
