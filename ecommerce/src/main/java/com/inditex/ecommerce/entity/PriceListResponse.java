package com.inditex.ecommerce.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceListResponse {
    private String priceList;
    private String productId;
    private String brandId;
    private Double price;
    private Timestamp  startDate;
    private Timestamp endDate;
}
