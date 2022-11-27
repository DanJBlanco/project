package com.inditex.ecommerce.service.imp;

import com.inditex.ecommerce.entity.PriceList;
import com.inditex.ecommerce.mock.EntityMock;
import com.inditex.ecommerce.repository.ProductRepository;
import com.inditex.ecommerce.service.ProductService;
import com.inditex.ecommerce.utils.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Autowired
    ProductService productService;


    @BeforeEach
    void setUp() {

    }

    @Test
    void listProductsWithFilter() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "1");
        filters.put(Properties.PRODUCT_ID.getKey(), "35455");
        filters.put(Properties.DATE_IN.getKey(), "2020-06-14 10:00:00");

        List<PriceList> response = productService.listPricesWithFilter(filters);

        String expected = "1";

        Assertions.assertEquals(expected, response.get(0).getPriceList());
    }
    @Test
    void listProductsWithFilterNoDate() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "1");
        filters.put(Properties.PRODUCT_ID.getKey(), "35455");
        filters.put(Properties.DATE_IN.getKey(), null);

        List<PriceList> response = productService.listPricesWithFilter(filters);

        Assertions.assertNull(response);
    }
    @Test
    void listProductsWithFilterNoBrand() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), null);
        filters.put(Properties.PRODUCT_ID.getKey(), "35455");
        filters.put(Properties.DATE_IN.getKey(), "2020-06-14 10:00:00");

        List<PriceList> response = productService.listPricesWithFilter(filters);


        Assertions.assertNull(response);
    }
    @Test
    void listProductsWithFilterNoProduct() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "1");
        filters.put(Properties.PRODUCT_ID.getKey(), null);
        filters.put(Properties.DATE_IN.getKey(), "2020-06-14 10:00:00");

        List<PriceList> response = productService.listPricesWithFilter(filters);
        Assertions.assertNull(response);
    }
    @Test
    void listProductsWithFilterEmptyProduct() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "1");
        filters.put(Properties.PRODUCT_ID.getKey(), "");
        filters.put(Properties.DATE_IN.getKey(), "2020-06-14 10:00:00");

        List<PriceList> response = productService.listPricesWithFilter(filters);
        Assertions.assertNull(response);
    }
    @Test
    void listProductsWithFilterEmptyBrand() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "");
        filters.put(Properties.PRODUCT_ID.getKey(), "35455");
        filters.put(Properties.DATE_IN.getKey(), "2020-06-14 10:00:00");

        List<PriceList> response = productService.listPricesWithFilter(filters);
        Assertions.assertNull(response);
    }
    @Test
    void listProductsWithFilterEmptyDate() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "1");
        filters.put(Properties.PRODUCT_ID.getKey(), "35455");
        filters.put(Properties.DATE_IN.getKey(), "");

        List<PriceList> response = productService.listPricesWithFilter(filters);
        Assertions.assertNull(response);
    }
    @Test
    void listProductsWithFilterErrorFormatDate() throws IOException {

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(anyString(),anyString(), any(), any()) )
                .thenReturn(priceList);

        Map<String, String> filters = new HashMap<>();
        filters.put(Properties.BRAND_ID.getKey(), "1");
        filters.put(Properties.PRODUCT_ID.getKey(), "35455");
        filters.put(Properties.DATE_IN.getKey(), "232/42/242 24122");

        List<PriceList> response = productService.listPricesWithFilter(filters);
        Assertions.assertNull(response);
    }
}