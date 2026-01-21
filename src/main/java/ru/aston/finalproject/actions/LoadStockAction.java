package ru.aston.finalproject.actions;

import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.collection.CustomArrayListCollector;
import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.util.Message;

public class LoadStockAction extends AppAction{
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 3;
    private static final String SIZE_PARAMETER = "-size";
    private static final String LOADER_TYPE_PARAMETER = "-type";

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);
        Integer size = request.getIntegerParameter(SIZE_PARAMETER);
        String loaderKey = request.getStringParameter(LOADER_TYPE_PARAMETER);

        CustomArrayList<Stock> stocks = appData.getStockService()
                .loadEntityList(loaderKey, size, request)
                .collect(CustomArrayListCollector.toCustomArrayList());
        appData.getStockList().addAll(stocks);

        System.out.printf((Message.X_STOCKS_LOADED) + "%n", stocks.size());
    }
}
