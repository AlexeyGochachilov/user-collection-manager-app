package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.entity.User;

public class ShowUsers extends AppAction {
    // Текущее наполнение - для демонстрации. Класс открыт для изменения.

    @Override
    public String action(AppData appData, String[] args) throws AppException {
        if (args.length != 0){
            throw new AppException("Ожидалось 0 аргументов.");
        }

        StringBuilder consoleReturn = new StringBuilder();
        for (User user: appData.getUserList()){
            consoleReturn.append(user);
            consoleReturn.append("\n");
        }

        return consoleReturn.toString();
    }
}
