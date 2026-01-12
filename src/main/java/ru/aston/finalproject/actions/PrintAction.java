package ru.aston.finalproject.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.staticTools.Message;

public class PrintAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);

        CustomArrayList<User> userList = appData.getUserList();
        if (ObjectUtils.isEmpty(userList)) {
            System.out.println(Message.EXCEPTION_LIST_NOT_LOADED);
        }

        userList.stream()
                .map(appData.getUserParser()::parseToString)
                .forEach(System.out::println);
    }
}
