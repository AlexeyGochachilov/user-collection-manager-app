package ru.aston.finalproject.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.staticTools.Message;

public class WriteAction extends AppAction {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 1;
    private static final String FILE_PATH_PARAMETER = "-file";

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);
        String filePath = request.getStringParameter(FILE_PATH_PARAMETER);

        CustomArrayList<User> userList = appData.getUserList();
        if (ObjectUtils.isEmpty(userList)) {
            System.out.println(Message.EXCEPTION_LIST_NOT_LOADED);
        }

        appData.getFileWriter().write(userList, filePath);
        System.out.println(Message.USERS_SAVED);
    }
}

