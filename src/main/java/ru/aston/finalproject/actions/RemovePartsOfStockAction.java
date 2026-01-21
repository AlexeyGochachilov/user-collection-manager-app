package ru.aston.finalproject.actions;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.environment.appdata.AppData;

import java.math.BigDecimal;
import java.util.List;

public class RemovePartsOfStockAction extends AppAction<Stock> {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 1;
    private static final String VALUE_PARAMETER = "-value";

    @Override
    public void action(AppData<Stock> appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);
        Double size = request.getDoubleParameter(VALUE_PARAMETER);
        List<Stock> partOfList = appData.getEntityList().stream().filter
                        (stock -> stock.getPE().compareTo(new BigDecimal(size)) > 0).toList();
        appData.setEntityList(partOfList);
    }
}
