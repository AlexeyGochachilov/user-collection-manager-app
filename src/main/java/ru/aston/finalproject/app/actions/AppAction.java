package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;

public abstract class AppAction {

    public abstract String action(AppData appData, String[] args) throws AppException;
}
