package ru.aston.finalproject.actions;

import ru.aston.finalproject.appenviroment.AppData;
import ru.aston.finalproject.appenviroment.AppException;
import ru.aston.finalproject.appenviroment.AppRequest;
import ru.aston.finalproject.statictools.Message;

public class ClearAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);
        appData.getUserList().clear();

        System.out.println(Message.USERS_CLEARED);
    }
}
