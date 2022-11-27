package com.inditex.ecommerce.service.imp;

import com.inditex.ecommerce.entity.PriceList;
import com.inditex.ecommerce.repository.ProductRepository;
import com.inditex.ecommerce.service.ProductService;
import com.inditex.ecommerce.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ListPriceServiceImp implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListPriceServiceImp.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<PriceList> listPricesWithFilter(Map<String, String> filters) {
        LOGGER.info("[ecommerce] ListPriceServiceImp.listPricesWithFilter ...");
        LOGGER.debug("[ecommerce] ListPriceServiceImp.listPricesWithFilter: filters -> {}", filters);

        if (this.isValidInput(filters)){
            LOGGER.info("[ecommerce] ListPriceServiceImp.isValidInput not valid filters");
            return null;
        }

        LOGGER.info("[ecommerce] ListPriceServiceImp.listPricesWithFilter valid filters");

        String productId = filters.get(Properties.PRODUCT_ID.getKey());
        String brandId = filters.get(Properties.BRAND_ID.getKey());
        Timestamp date = Timestamp.valueOf(filters.get(Properties.DATE_IN.getKey()));

        LOGGER.debug("[ecommerce] ListPriceServiceImp.listPricesWithFilter request repository: productId ->{}, brandId -> {}, date -> {}", productId, brandId, date);

        return this.productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, date, date).stream().toList();
    }

    private boolean isValidInput(Map<String, String> filters){

        LOGGER.info("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters ...");
        LOGGER.debug("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters: filters -> {}", filters);
        return this.isEmptyOrNullFilters(filters) || !this.isTimeStampValid(filters.get(Properties.DATE_IN.getKey()));
    }

    private boolean isEmptyOrNullFilters(Map<String, String> filters) {

        LOGGER.info("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters ...");
        LOGGER.debug("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters: filters -> {}", filters);

        return filters.values().stream().anyMatch(value -> Objects.isNull(value) || value.isEmpty());

    }
    private boolean isTimeStampValid(String inputString) {

        LOGGER.info("[ecommerce] ListPriceServiceImp.isTimeStampValid ...");
        LOGGER.debug("[ecommerce] ListPriceServiceImp.isTimeStampValid: inputString -> {}", inputString);

        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            format.parse(inputString);
            return true;
        }
        catch(ParseException e)
        {
            return false;
        }
    }
}
