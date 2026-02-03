package ru.aston.finalproject.actions;

import ru.aston.finalproject.entity.stock.Stock;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.util.Message;

public class RemovePartsOfStockAction extends AppAction<Stock> {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 2;
    private static final String VALUE_PARAMETER = "-value";
    private static final String FLOOR_VALUE_PARAMETER = "-floor";
    private static final String CEILING_VALUE_PARAMETER = "-ceiling";

    @Override
    public void action(AppData<Stock> appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);

        if (request.getSizeParameters() == 1) {
            if (request.containsParameter(VALUE_PARAMETER)) {
                Double value = request.getDoubleParameter(VALUE_PARAMETER);
                appData.getFilter().filter(stock -> stock.isThisValue(value));
                appData.setEntityList(appData.getFilter().getList());
            } else if (request.containsParameter(FLOOR_VALUE_PARAMETER)) {
                Double floorValue = request.getDoubleParameter(FLOOR_VALUE_PARAMETER);
                appData.getFilter().filter(stock -> stock.isFloorBorder(floorValue));
                appData.setEntityList(appData.getFilter().getList());
            } else if (request.containsParameter(CEILING_VALUE_PARAMETER)) {
                Double ceilingValue = request.getDoubleParameter(CEILING_VALUE_PARAMETER);
                appData.getFilter().filter(stock -> stock.isCeilingBorder(ceilingValue));
                appData.setEntityList(appData.getFilter().getList());
            }
        } else if (request.getSizeParameters() == 2) {
            if (!request.containsParameter(FLOOR_VALUE_PARAMETER)
                    && !request.containsParameter(CEILING_VALUE_PARAMETER)) {
                throw new AppException(Message.WRONG_REQUEST_PARAMETER_SYNTAXES);
            }
            Double floorValue = request.getDoubleParameter(FLOOR_VALUE_PARAMETER);
            Double ceilingValue = request.getDoubleParameter(CEILING_VALUE_PARAMETER);
            appData.getFilter().filter(stock -> stock.isFloorBorder(floorValue))
                    .filter(stock -> stock.isCeilingBorder(ceilingValue));
            appData.setEntityList(appData.getFilter().getList());
        }

        System.out.println(Message.ENTITIES_FILTERED);
    }
}
