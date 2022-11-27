package com.inditex.ecommerce.utils;

public enum Properties {
    PRODUCT_ID("productId"),
    BRAND_ID("brandId"),
    DATE_IN("date");
    private final String key;

    Properties(String key) { this.key = key; }

    public String getKey() {
        return key;
    }
}
