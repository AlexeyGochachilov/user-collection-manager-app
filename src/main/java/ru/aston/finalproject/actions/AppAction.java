package ru.aston.finalproject.actions;

import ru.aston.finalproject.appenviroment.AppData;
import ru.aston.finalproject.appenviroment.AppException;
import ru.aston.finalproject.appenviroment.AppRequest;

public abstract class AppAction {

    public abstract void action(AppData appData, AppRequest request) throws AppException;
}
