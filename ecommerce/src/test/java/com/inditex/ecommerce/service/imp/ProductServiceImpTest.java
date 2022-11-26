package com.inditex.ecommerce.service.imp;

import com.inditex.ecommerce.entity.PriceList;
import com.inditex.ecommerce.mock.EntityMock;
import com.inditex.ecommerce.repository.ProductRepository;
import com.inditex.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.mock;

// @RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;


    @BeforeEach
    void setUp() {
        PriceList product = new PriceList();
                product.setProductId("1L");
        Map<String, String> filter = new HashMap<>();
//        Mockito.when(productRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Mockito.anyString(),Mockito.anyString(), Mockito.any(), Mockito.any()) )
                //.thenReturn(new ArrayList<>());

    }

    @Test
    void listProductsWithFilter() throws IOException {
        Map<String, String> filters = new HashMap<>();
//        List<PriceList> response = productService.listProductsWithFilter(filters);

        List<PriceList> priceList = EntityMock.getInstance().getPriceList();
        System.out.println(priceList);
     Assertions.assertEquals(1, priceList.size());

    }
}