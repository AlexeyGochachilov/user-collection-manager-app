package ru.aston.finalproject.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    // Текущее наполнение - для демонстрации. Класс открыт для изменения.
    private int id;
    private String username;

    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("Пользователь %d %s", id, username);
    }
}
