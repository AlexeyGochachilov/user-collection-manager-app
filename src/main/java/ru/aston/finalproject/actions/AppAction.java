package ru.aston.finalproject.actions;

import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;

public abstract class AppAction {

    public abstract void action(AppData appData, AppRequest request) throws AppException;
}
