package com.inditex.ecommerce.service.imp;

import com.inditex.ecommerce.entity.PriceList;
import com.inditex.ecommerce.repository.ProductRepository;
import com.inditex.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<PriceList> listProductsWithFilter(Map<String, String> filters) {

        String productId = "";
        String brandId = "";
        Timestamp startDate = new Timestamp(new Date().getTime());
        Timestamp endDate = new Timestamp(new Date().getTime());

        return this.productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, startDate, endDate).stream().toList();
    }
}
