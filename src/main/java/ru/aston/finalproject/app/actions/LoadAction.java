package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.util.Message;

import java.util.List;

public class LoadAction extends AppAction {
    private static final String SIZE_PARAMETER = "-size";
    private static final String LOADER_TYPE_PARAMETER = "-type";

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        Integer size = request.getIntegerParameter(SIZE_PARAMETER);
        String loaderKey = request.getStringParameter(LOADER_TYPE_PARAMETER);

        List<User> users = appData.getUserService().loadEntityList(loaderKey, size, request);
        appData.setUserList(users);

        System.out.println(Message.USERS_LOADED);
    }
}
