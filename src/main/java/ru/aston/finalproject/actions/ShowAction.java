package ru.aston.finalproject.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.util.Message;

import java.util.List;

public class ShowAction extends AppAction{
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);
        List<Stock> stockList = appData.getStockList();
        if (ObjectUtils.isEmpty(stockList)) {
            System.out.println(Message.LIST_NOT_LOADED);
        }
        stockList.forEach(System.out::println);
    }
}
