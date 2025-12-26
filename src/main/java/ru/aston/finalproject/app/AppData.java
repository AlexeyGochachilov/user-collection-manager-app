package ru.aston.finalproject.app;

import lombok.Getter;
import lombok.Setter;
import ru.aston.finalproject.entity.User;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AppData {
    // Этот класс хранит все переменные приложения.
    // Текущее наполнение - для демонстрации. Класс открыт для изменения.
    private List<User> userList = new ArrayList<User>();

}
