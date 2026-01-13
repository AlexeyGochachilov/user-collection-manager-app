package ru.aston.finalproject.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.environment.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.entity.user.User;
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
            throw new AppException(Message.LIST_NOT_LOADED);
        }

        if (request.containsParameter(COMMAND_PARAMETER_BASIC)) {
            userList = appData.getSorter().sort(userList);
        } else if (request.containsParameter(COMMAND_PARAMETER_STRANGE)) {
            userList = appData.getStrangeSorter().sort(userList, User::getAge);
        } else {
            throw new AppException(Message.WRONG_REQUEST_PARAMETER_SYNTAXES);
        }

        appData.setUserList(userList);
        System.out.println(Message.USERS_SORTED);
    }
}