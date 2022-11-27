package com.inditex.ecommerce.repository;

import com.inditex.ecommerce.entity.PriceList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductRepositoryTest {

    private final String PRODUCT_ID = "35455";
    private final String BRAND_ID = "1";
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll_thenReturnAllProducts(){

        List<PriceList> response = productRepository.findAll();
        int totalProducts = 4;
        Assertions.assertEquals(totalProducts, response.size());
    }

    @Test
    public void findPricesDay14and10hours() {
        String strStartDate = "2020-06-14 10:00:00";
        Timestamp timeDate = Timestamp.valueOf(strStartDate);
        List<PriceList> response = productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(PRODUCT_ID, BRAND_ID, timeDate, timeDate);
        Assertions.assertEquals(1, response.stream().toList().size());
    }

    @Test
    public void findPricesDay14and16hours() {
        String strStartDate = "2020-06-14 16:00:00";
        Timestamp timeDate = Timestamp.valueOf(strStartDate);
        List<PriceList> response = productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(PRODUCT_ID, BRAND_ID, timeDate, timeDate);
        Assertions.assertEquals(2, response.stream().toList().size());
    }

    @Test
    public void findPricesDay14and21hours() {
        String strStartDate = "2020-06-14 21:00:00";
        Timestamp timeDate = Timestamp.valueOf(strStartDate);
        List<PriceList> response = productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(PRODUCT_ID, BRAND_ID, timeDate, timeDate);
        Assertions.assertEquals(1, response.stream().toList().size());
    }

    @Test
    public void findPricesDay15and10hours() {
        String strStartDate = "2020-06-15 10:00:00";
        Timestamp timeDate = Timestamp.valueOf(strStartDate);
        List<PriceList> response = productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(PRODUCT_ID, BRAND_ID, timeDate, timeDate);
        Assertions.assertEquals(2, response.stream().toList().size());
    }

    @Test
    public void findPricesDay16and21hours() {
        String strStartDate = "2020-06-16 21:00:00";
        Timestamp timeDate = Timestamp.valueOf(strStartDate);
        List<PriceList> response = productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(PRODUCT_ID, BRAND_ID, timeDate, timeDate);
        Assertions.assertEquals(2, response.stream().toList().size());
    }



}