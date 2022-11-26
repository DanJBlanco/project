package com.inditex.ecommerce.repository;


import com.inditex.ecommerce.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ProductRepository extends JpaRepository<PriceList, Long> {

    List<PriceList> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String productId, String brandId, Timestamp startDate, Timestamp endaDate);
}
