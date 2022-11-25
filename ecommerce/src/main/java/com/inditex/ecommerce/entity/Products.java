package com.inditex.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Table(name = "PRICES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private String id;

    @Column(name = "BRAND_ID")
    private String brandId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "PRICE_LIST")
    private String priceList;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private String currency;


}
