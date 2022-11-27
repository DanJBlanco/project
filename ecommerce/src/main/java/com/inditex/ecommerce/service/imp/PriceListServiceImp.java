package com.inditex.ecommerce.service.imp;

import com.inditex.ecommerce.entity.PriceList;
import com.inditex.ecommerce.entity.PriceListResponse;
import com.inditex.ecommerce.repository.ProductRepository;
import com.inditex.ecommerce.service.PriceListService;
import com.inditex.ecommerce.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PriceListServiceImp implements PriceListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceListServiceImp.class);
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
        Timestamp date = this.getTimestampSQL(filters.get(Properties.DATE_IN.getKey()));
        if (Objects.isNull(date)){
            return null;
        }

        LOGGER.debug("[ecommerce] ListPriceServiceImp.listPricesWithFilter request repository: productId ->{}, brandId -> {}, date -> {}", productId, brandId, date);

        return this.productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, date, date).stream().toList();
    }

    private Timestamp getTimestampSQL(String dateAsString) {


        try{
            String patternSQL = Properties.DATE_TIME_PATTERN.getKey();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternSQL);
            LocalDateTime localDateTime = LocalDate.parse(dateAsString ,formatter).atStartOfDay();
            return Timestamp.valueOf(localDateTime);
        }
        catch(Exception e)
        {
            LOGGER.info("[ecommerce] ListPriceServiceImp.isTimeStampValid not valid");
            return null;
        }
    }


    private boolean isValidInput(Map<String, String> filters){

        LOGGER.info("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters ...");
        LOGGER.debug("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters: filters -> {}", filters);
        return this.isEmptyOrNullFilters(filters); //|| !this.isTimeStampValid(filters.get(Properties.DATE_IN.getKey()).toString());
    }

    private boolean isEmptyOrNullFilters(Map<String, String> filters) {

        LOGGER.info("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters ...");
        LOGGER.debug("[ecommerce] ListPriceServiceImp.isEmptyOrNullFilters: filters -> {}", filters);

        return filters.values().stream().anyMatch(value -> Objects.isNull(value) || value.toString().isEmpty());

    }
}
