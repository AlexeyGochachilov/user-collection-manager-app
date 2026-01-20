package ru.aston.finalproject.entity.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.finalproject.environment.AppException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockValidatorTest {

    StockValidator stockValidator;
    String name;
    BigDecimal nowValue;
    BigDecimal maxValue;
    BigDecimal minValue;

    @BeforeEach
    public void setUp() {
        stockValidator = new StockValidator();
        name = "name";
        nowValue = new BigDecimal("75");
        maxValue = new BigDecimal("100");
        minValue = new BigDecimal("50");
    }

    @Test
    public void givenValidData_whenValidate_thenSuccess() {
        stockValidator.validate(name, nowValue, maxValue, minValue);
    }

    @Test
    public void givenName_whenValidate_thenThrowsException() {
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stockValidator.validate(name);
        });
        assertTrue(exception.getMessage().contains("out of bounds"));
    }

    @Test
    public void givenNameNowValue_whenValidate_thenThrowsException() {
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stockValidator.validate(name, nowValue);
        });
        assertTrue(exception.getMessage().contains("out of bounds"));
    }

    @Test
    public void givenNameNowValueMaxValue_whenValidate_thenThrowsException() {
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stockValidator.validate(name, nowValue, maxValue);
        });
        assertTrue(exception.getMessage().contains("out of bounds"));
    }

    @Test
    public void givenMaxValueLessNowValue_whenValidate_thenThrowsException() {
        nowValue = new BigDecimal("150");
        AppException exception = assertThrows(AppException.class, () -> {
            stockValidator.validate(name, nowValue, maxValue, minValue);
        });
        assertTrue(exception.getMessage().contains("can't be"));
    }

    @Test
    public void givenNowValueLessMinValue_whenValidate_thenThrowsException() {
        nowValue = new BigDecimal("25");
        AppException exception = assertThrows(AppException.class, () -> {
            stockValidator.validate(name, nowValue, maxValue, minValue);
        });
        assertTrue(exception.getMessage().contains("can't be"));
    }
}