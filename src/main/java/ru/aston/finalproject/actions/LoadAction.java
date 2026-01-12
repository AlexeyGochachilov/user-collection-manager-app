package ru.aston.finalproject.actions;

import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.collection.CustomArrayListCollector;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.staticTools.Message;

public class LoadAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 3;
    private static final String SIZE_PARAMETER = "-size";
    private static final String LOADER_TYPE_PARAMETER = "-type";

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);
        Integer size = request.getIntegerParameter(SIZE_PARAMETER);
        String loaderKey = request.getStringParameter(LOADER_TYPE_PARAMETER);

        CustomArrayList<User> users = appData.getUserService()
                .loadEntityList(loaderKey, size, request)
                .collect(CustomArrayListCollector.toCustomArrayList());
        appData.getUserList().addAll(users);

        System.out.println(Message.USERS_LOADED);
    }
}
