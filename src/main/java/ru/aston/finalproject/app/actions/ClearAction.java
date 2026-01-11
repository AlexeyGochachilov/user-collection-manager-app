package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.util.Message;

public class ClearAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);
        appData.getUserList().clear();

        System.out.println(Message.USERS_CLEARED);
    }
}
