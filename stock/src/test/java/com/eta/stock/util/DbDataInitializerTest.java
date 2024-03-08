package com.eta.stock.util;

import com.eta.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class DbDataInitializerTest {

    @Autowired
    DbDataInitializer dbDataInitializer;
    @Autowired
    StockService stockService;
    @Value("${initdb.amount}")
    long documentAmount;

    @Test
    void initDbData() {
        dbDataInitializer.initDbData();

        assertThat(stockService.count()).isEqualTo(documentAmount);
    }


}