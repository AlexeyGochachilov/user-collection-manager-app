package ru.aston.finalproject.entity.stock;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockTest {

    @Nested
    class StockCreationTest {

        @Test
        void givenMinimumValidData_whenBuildStock_thenStockCreatedSuccessfully() {
            Stock stock = Stock.builder()
                    .setName("gazProm")
                    .setNowValue("75")
                    .setMaxValue("100")
                    .setMinValue("50")
                    .build();

            assertNotNull(stock);
            assertEquals("gazProm", stock.getName());
            assertEquals(new BigDecimal(75), stock.getNowValue());
            assertEquals(new BigDecimal(100), stock.getMaxValue());
            assertEquals(new BigDecimal(50), stock.getMinValue());
        }

        @Test
        void givenAllValidData_whenBuildStock_thenStockCreatedSuccessfully() {
            Stock stock = Stock.builder()
                    .setName("gazProm")
                    .setNowValue("75")
                    .setMaxValue("100")
                    .setMinValue("50")
                    .setEPS("12")
                    .setPE("12")
                    .setEpsFrom5Years("138.24")
                    .setDividends("true")
                    .setBuyInThisPeriod("true")
                    .build();

            assertNotNull(stock);
            assertEquals("gazProm", stock.getName());
            assertEquals(new BigDecimal("75"), stock.getNowValue());
            assertEquals(new BigDecimal("100"), stock.getMaxValue());
            assertEquals(new BigDecimal("50"), stock.getMinValue());
            assertEquals(new BigDecimal("12"), stock.getEPS());
            assertEquals(new BigDecimal("12"), stock.getPE());
            assertEquals(new BigDecimal("138.24"), stock.getEpsFrom5Years());
            assertTrue(stock.isDividends());
            assertTrue(stock.isBuyInThisPeriod());
        }

    }
}
