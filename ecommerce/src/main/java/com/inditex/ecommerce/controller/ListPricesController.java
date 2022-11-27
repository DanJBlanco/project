package com.inditex.ecommerce.controller;

import com.inditex.ecommerce.entity.PriceList;
import com.inditex.ecommerce.entity.PriceListResponse;
import com.inditex.ecommerce.service.PriceListService;
import com.inditex.ecommerce.utils.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/list-prices")
public class ListPricesController {

    @Autowired
    private PriceListService  priceListService;

    @GetMapping("/{brandId}/{productId}/{date}")
    public ResponseEntity<List<PriceListResponse>> getPriceList(
            @PathVariable String brandId,
            @PathVariable String productId,
            @PathVariable String date
            ){

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), brandId);
        filters.put(Properties.PRODUCT_ID.getKey(), productId);
        filters.put(Properties.DATE_IN.getKey(), date);

        List<PriceList> response = this.priceListService.listPricesWithFilter(filters);

        if (Objects.isNull(response)){
            return  ResponseEntity.badRequest().build();
        }
        if (response.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(
                response.stream().map(priceList -> PriceListResponse.builder()
                        .productId(priceList.getProductId())
                        .brandId(priceList.getBrandId() )
                        .priceList(priceList.getPriceList())
                        .startDate(priceList.getStartDate())
                        .endDate(priceList.getEndDate())
                        .price(priceList.getPrice())
                        .build())
                .collect(Collectors.toList())
        );
    }
}
