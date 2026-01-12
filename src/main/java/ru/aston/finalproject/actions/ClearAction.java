package ru.aston.finalproject.actions;

import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.staticTools.Message;

public class ClearAction extends AppAction {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);
        appData.getUserList().clear();

        System.out.println(Message.USERS_CLEARED);
    }
}
