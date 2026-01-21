package ru.aston.finalproject.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.util.Message;

import java.util.List;

public class WriteAction<T> extends AppAction<T> {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 1;
    private static final String FILE_PATH_PARAMETER = "-file";

    @Override
    public void action(AppData<T> appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);
        String filePath = request.getStringParameter(FILE_PATH_PARAMETER);

        List<T> entityList = appData.getEntityList();
        if (ObjectUtils.isEmpty(entityList)) {
            System.out.println(Message.LIST_NOT_LOADED);
        }

        appData.getFileWriter().write(entityList, filePath);
        System.out.println(Message.ENTITIES_SAVED);
    }
}
