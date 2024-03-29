package com.inventario.sistemainv.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String quantity;

    private BigDecimal buy_price;

    private BigDecimal sale_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id", referencedColumnName = "id")
    private Categories categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private Media media_id;

    private String date;

    private String modified_date;

    public Integer getQuantityAsInteger(){
        Integer qty = Integer.valueOf(quantity);
        return qty;
    }

    public String getQuantity() {
        return quantity;
    }

    public Integer getDolarsSale(){
        String str = sale_price.toString();
        Integer intNumber = Integer.parseInt(str.substring(0, str.indexOf('.')));
        return  intNumber;
    }
    public Integer getCentsSale(){
        String str = sale_price.toString();
        Integer decNumberInt = Integer.parseInt(str.substring(str.indexOf('.') + 1));
        return  decNumberInt;
    }

    public Integer getDolarsBuy(){
        String str = buy_price.toString();
        Integer intNumber = Integer.parseInt(str.substring(0, str.indexOf('.')));
        return  intNumber;
    }
    public Integer getCentsBuy(){
        String str = buy_price.toString();
        Integer decNumberInt = Integer.parseInt(str.substring(str.indexOf('.') + 1));
        return  decNumberInt;
    }
}
