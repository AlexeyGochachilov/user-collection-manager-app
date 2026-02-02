package ru.aston.finalproject.service.filters;

import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.util.Message;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterListStocks implements FilterList<Stock> {

    public List<Stock> filter(List<Stock> list, Double... value) {
        if (Objects.isNull(list)) {
            return new CustomArrayList<>();
        }
        if (value.length == 1) {
            return list.stream().filter(
                    stock -> stock.getPe().compareTo(BigDecimal.valueOf(value[0])) > 0
            ).collect(Collectors.toList());
        }
        if (value.length == 2) {
            return list.stream().filter(
                    stock -> stock.getPe().compareTo(BigDecimal.valueOf(value[0])) > 0
            ).filter(
                    stock -> stock.getPe().compareTo(BigDecimal.valueOf(value[1])) < 0
            ).collect(Collectors.toList());
        } else {
            throw new AppException(Message.WRONG_PARAMETERS_AMOUNT);
        }
    }
}
