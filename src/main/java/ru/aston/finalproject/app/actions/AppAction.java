package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;

public abstract class AppAction {

    public abstract void action(AppData appData, AppRequest request) throws AppException;
}
