package ru.aston.finalproject.actions;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.util.Message;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class RemovePartsOfStockAction extends AppAction<Stock> {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 2;
    private static final String VALUE_PARAMETER = "-value";
    private static final String FIRST_VALUE_PARAMETER = "-first";
    private static final String SECOND_VALUE_PARAMETER = "-second";

    @Override
    public void action(AppData<Stock> appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);

        if (request.containsParameter(VALUE_PARAMETER)) {
            Double value = request.getDoubleParameter(VALUE_PARAMETER);
            List<Stock> partOfList = appData.getEntityList().stream().filter(
                    stock -> stock.getPe().compareTo(new BigDecimal(value)) > 0
            ).collect(Collectors.toList());
            appData.setEntityList(partOfList);
        } else {
            Double firstValue = request.getDoubleParameter(FIRST_VALUE_PARAMETER);
            Double secondValue = request.getDoubleParameter(SECOND_VALUE_PARAMETER);
            List<Stock> partsOfList = appData.getEntityList().stream().filter(
                    stock -> stock.getPe().compareTo(new BigDecimal(firstValue)) > 0
            ).filter(
                    stock -> stock.getPe().compareTo(new BigDecimal(secondValue)) < 0
            ).collect(Collectors.toList());
            appData.setEntityList(partsOfList);
        }

        System.out.println(Message.ENTITIES_FILTERED);
    }
}
