package com.inditex.ecommerce.service;

import com.inditex.ecommerce.entity.PriceList;

import java.util.List;
import java.util.Map;

public interface PriceListService {

    List<PriceList> listPricesWithFilter(Map<String, String> filters);

}
