package com.inditex.ecommerce.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.ecommerce.entity.PriceList;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EntityMock {

    private ObjectMapper objectMapper;

    private static final EntityMock INSTANCE = new EntityMock();

    private EntityMock() { objectMapper = new ObjectMapper(); }

    public static EntityMock getInstance() { return INSTANCE; }

    public List<PriceList> getPriceList() throws IOException {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mock/PriceList.json")){
            return objectMapper.readValue(is, new TypeReference< List< PriceList > >(){});
        }
    }
}
