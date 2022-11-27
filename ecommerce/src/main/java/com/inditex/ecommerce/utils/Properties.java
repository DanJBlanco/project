package com.inditex.ecommerce.utils;

public enum Properties {
    PRODUCT_ID("productId"),
    BRAND_ID("brandId"),
    DATE_IN("date"),
    DATE_TIME_PATTERN("yyyy-MM-d-HH.mm.ss");
    private final String key;

    Properties(String key) { this.key = key; }

    public String getKey() {
        return key;
    }
}
