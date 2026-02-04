package ru.aston.finalproject.enums;

import lombok.Getter;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.environment.appdata.StockAppData;
import ru.aston.finalproject.environment.appdata.UserAppData;

public enum Data {
    user(new UserAppData()),
    stock(new StockAppData());

    @Getter
    private final AppData<?> appData;

    Data(AppData<?> appData) {
        this.appData = appData;
    }
}
