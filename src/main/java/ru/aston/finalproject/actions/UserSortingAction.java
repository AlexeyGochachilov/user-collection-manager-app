package ru.aston.finalproject.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.entity.user.User;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.util.Message;

import java.util.List;

public class UserSortingAction extends AppAction<User> {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 1;
    private static final String COMMAND_PARAMETER_BASIC = "-basic";
    private static final String COMMAND_PARAMETER_STRANGE = "-strange";

    @Override
    public void action(AppData<User> appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);
        List<User> userList = appData.getEntityList();

        if (!request.containsParameter(COMMAND_PARAMETER_BASIC) && !request.containsParameter(COMMAND_PARAMETER_STRANGE)) {
            throw new AppException(Message.WRONG_REQUEST_PARAMETER_SYNTAXES);
        }

        if (ObjectUtils.isEmpty(userList)) {
            throw new AppException(Message.LIST_NOT_LOADED);
        }

        if (request.containsParameter(COMMAND_PARAMETER_BASIC)) {
            userList = appData.getSorter().sort(userList);
        } else if (request.containsParameter(COMMAND_PARAMETER_STRANGE)) {
            userList = appData.getStrangeSorter().sort(userList, User::getAge);
        }

        appData.setEntityList(userList);
        System.out.println(Message.ENTITIES_SORTED);
    }
}