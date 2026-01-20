package ru.aston.finalproject.entity.stock;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateFieldsStockTest {

    @Test
    public void testCalculateNowPercent() {
        Stock stock = Stock.builder()
                .setName("gazProm")
                .setNowValue("75")
                .setMaxValue("100")
                .setMinValue("50")
                .setEPS("12")
                .setPE("12")
                .setEpsFrom5Years("138.24")
                .build();

        BigDecimal result = stock.getMaxValue().subtract(stock.getMinValue());
        BigDecimal nowResult = stock.getNowValue().subtract(stock.getMinValue());
        BigDecimal hundred = new BigDecimal("100");
        nowResult = nowResult.multiply(hundred).divide(result, 2, java.math.RoundingMode.HALF_UP);
        assertEquals(50.0, nowResult.doubleValue());
    }

    @Test
    public void setGrahamPrice() {
        Stock stock = Stock.builder()
                .setName("Черкизово")
                .setNowValue("3598")
                .setMaxValue("4745")
                .setMinValue("2911")
                .setEPS("254.5")
                .setPE("14.03")
                .setEpsFrom5Years("199.7")
                .build();

        assertEquals(8748.44,stock.getGrahamPrice().doubleValue());
    }
}
