package ru.aston.finalproject.app.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.util.Message;

import java.util.List;

public class SortingAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 1;
    private static final String COMMAND_PARAMETER_BASIC = "-basic";
    private static final String COMMAND_PARAMETER_STRANGE = "-strange";

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);
        List<User> userList = appData.getUserList();

        if (ObjectUtils.isEmpty(userList)) {
            throw new AppException(Message.EXCEPTION_LIST_NOT_LOADED);
        }

        if (request.containsParameter(COMMAND_PARAMETER_BASIC)) {
            userList = appData.getSorter().sort(userList);
        } else if (request.containsParameter(COMMAND_PARAMETER_STRANGE)) {
            userList = appData.getStrangeSorter().sort(userList, User::getAge);
        } else {
            throw new AppException(Message.EXCEPTION_WRONG_REQUEST_PARAMETER_SYNTAXES);
        }

        appData.setUserList(userList);
        System.out.println(Message.USERS_SORTED);
    }
}