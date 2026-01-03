package ru.aston.finalproject.app.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.config.ServiceLocator;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.service.UserService;
import ru.aston.finalproject.util.Key;

import java.util.Arrays;
import java.util.List;

public class PrintAction extends AppAction {

    @Override
    public String action(AppData appData, String[] args) throws AppException {
        List<User> userList = appData.getUserList();
        if (ObjectUtils.isEmpty(userList)) {
            return "List not loaded";
        }

        userList.forEach(System.out::println);
        return "";
    }
}
