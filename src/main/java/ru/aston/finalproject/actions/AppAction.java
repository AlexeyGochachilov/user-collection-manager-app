package ru.aston.finalproject.actions;

import ru.aston.finalproject.environment.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;

public abstract class AppAction {

    public abstract void action(AppData appData, AppRequest request) throws AppException;
}
