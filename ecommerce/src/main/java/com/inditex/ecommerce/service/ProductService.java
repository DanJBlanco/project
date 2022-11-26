package com.inditex.ecommerce.service;

import com.inditex.ecommerce.entity.PriceList;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<PriceList> listProductsWithFilter(Map<String, String> filters);

}
