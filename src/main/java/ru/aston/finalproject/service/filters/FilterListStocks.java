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

    public List<Stock> filters(List<Stock> list, Double... value) {

        if (Objects.isNull(list)) {
            return new CustomArrayList<>();
        }

        int oneArgs = 1;
        int twoArgs = 2;

        if (value.length == oneArgs) {
            return filterValuesMoreO(list, value[0]);
        } else if (value.length == twoArgs) {
            list = filterValuesMoreO(list, value[0]);
            return filterValuesLessO(list, value[1]);
        } else {
            throw new AppException(Message.WRONG_PARAMETERS_AMOUNT);
        }
    }

    private List<Stock> filterValuesMoreO(List<Stock> list, Double value) {
        return list.stream()
                .filter(stock -> stock.getPe().compareTo(BigDecimal.valueOf(value)) > 0)
                .collect(Collectors.toList());
    }

    private List<Stock> filterValuesLessO(List<Stock> list, Double value) {
        return list.stream()
                .filter(stock -> stock.getPe().compareTo(BigDecimal.valueOf(value)) < 0)
                .collect(Collectors.toList());
    }
}
