package ru.aston.finalproject.enums;

import lombok.Getter;
import ru.aston.finalproject.actions.AppAction;
import ru.aston.finalproject.actions.ClearAction;
import ru.aston.finalproject.actions.HelpAction;
import ru.aston.finalproject.actions.LoadAction;
import ru.aston.finalproject.actions.PrintAction;
import ru.aston.finalproject.actions.RemovePartsOfStockAction;
import ru.aston.finalproject.actions.ShowAction;
import ru.aston.finalproject.actions.UserSortingAction;
import ru.aston.finalproject.actions.UsersCountAction;
import ru.aston.finalproject.actions.WriteAction;

public enum Action {
    HELP(new HelpAction<>()),
    CLEAR(new ClearAction<>()),
    LOAD(new LoadAction<>()),
    PRINT( new PrintAction<>()),
    SHOW(new ShowAction<>()),
    WRITE(new WriteAction<>()),
    COUNT(new UsersCountAction()),
    SORT(new UserSortingAction()),
    FILTER(new RemovePartsOfStockAction());

    @Getter
    private final AppAction<?> appAction;

    Action(AppAction<?> appAction) {
        this.appAction = appAction;
    }
}
