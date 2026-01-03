package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.config.ServiceLocator;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.service.UserService;
import ru.aston.finalproject.util.Key;

import java.util.Arrays;
import java.util.List;

public class LoadAction extends AppAction {
    private final UserService userService;

    public LoadAction() {
        userService = ServiceLocator.getService(UserService.class);
    }

    @Override
    public String action(AppData appData, String[] args) throws AppException {
        // ожидаемый формат:
        // load size -key arg
        // load 4 -file /some/path
        // load 4 -console
        // load 4 -random
        try {
            int size = Integer.parseInt(args[0]);
            if (size <= 0) {
                throw new AppException("Please enter a number of elements greater than zero.");
            }
            String key = args[1];
            if (Key.LOAD_FROM_FILE.equals(key)) {
                userService.setLoaderFilePath(args[2]);
            }
            List<User> users = userService.loadUsers(key, size);
            appData.setUserList(users);
        } catch (IndexOutOfBoundsException e) {
            throw new AppException("Wrong command arguments: %s".formatted(Arrays.toString(args)));
        }

        // TODO: уточнить у Никиты что должен возвращать метод
        return "Users loaded";
    }
}
