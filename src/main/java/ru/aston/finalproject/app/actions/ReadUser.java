package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.entity.User;

public class ReadUser extends AppAction {
    // Текущее наполнение - для демонстрации. Класс открыт для изменения.

    @Override
    public String action(AppData appData, String[] args) throws AppException {
        if (args.length != 2){
            throw new AppException("Ожидалось 2 аргумента.");
        }

        int id;
        String username;
        try {
            id = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException exception) {
            throw new AppException("Не удалось привести первый аргумент к int.");
        }
        username = args[1];

        appData.getUserList().add(new User(id, username));
        return "Пользователь успешно добавлен.";
    }
}
