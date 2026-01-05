package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.entity.User;

import java.util.List;

public class LoadAction extends AppAction {
    private static final String SIZE_PARAMETER = "-size";
    private static final String LOADER_TYPE_PARAMETER = "-type";

    @Override
    public String action(AppData appData, AppRequest request) throws AppException {
        Integer size = request.getIntegerParameter(SIZE_PARAMETER);
        String loaderKey = request.getStringParameter(LOADER_TYPE_PARAMETER);

        List<User> users = appData.getUserService().loadEntityList(loaderKey, size, request);
        appData.setUserList(users);
        // TODO: уточнить у Никиты что должен возвращать метод
        return "Users loaded";
    }
}
