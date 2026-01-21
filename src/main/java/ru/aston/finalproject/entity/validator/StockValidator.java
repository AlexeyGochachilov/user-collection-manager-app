package ru.aston.finalproject.entity.validator;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppException;

import java.math.BigDecimal;

import static ru.aston.finalproject.util.ConstantMethods.checkedStringOnEmpty;
import static ru.aston.finalproject.util.Message.INVALID_DATA;

public class StockValidator implements Validate<Stock> {

    private BigDecimal nowValue;
    private BigDecimal maxValue;
    private BigDecimal minValue;

    @Override
    public void validate(Object... obj) throws AppException {

        nowValue = (BigDecimal) obj[1];
        maxValue = (BigDecimal) obj[2];
        minValue = (BigDecimal) obj[3];
        checkedName((String) obj[0]);
        checkedNowValue(nowValue);
        checkedMaxValue(maxValue);
        checkedMinValue(minValue);
    }

    private void checkedName(String name) {
        checkedStringOnEmpty(name, "Name of Stock");
    }

    private void checkedNowValue(BigDecimal value) {
        if (value == null) {
            throw new AppException(INVALID_DATA);
        }
        if (nowValue.compareTo(maxValue) > 0) {
            throw new AppException("nowValue: " + value + " can't be greater than maxValue");
        }
        if (nowValue.compareTo(minValue) < 0) {
            throw new AppException("nowValue can't be less than minValue");
        }
    }

    private void checkedMaxValue(BigDecimal value) {
        if (value == null) {
            throw new AppException(INVALID_DATA);
        }
        value = nowValue.max(maxValue).max(minValue);
        if (maxValue.compareTo(value) != 0) {
            throw new AppException("maxValue can't be less than all Values");
        }
    }

    private void checkedMinValue(BigDecimal value) {
        if (value == null) {
            throw new AppException(INVALID_DATA);
        }
        value = nowValue.min(maxValue).min(minValue);
        if (minValue.compareTo(value) != 0) {
            throw new AppException("minValue can't be greater than all Values");
        }
    }
}