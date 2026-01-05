package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;

public class HelpAction extends AppAction {
    @Override
    public String action(AppData appData, AppRequest request) throws AppException {
        return """
                help \t list available commands and required arguments
                print \t print current saved list
                load \t load user list
                \t\t -size=<size> -type=file -file=<file-path> \t load from file path
                \t\t -size=<size> -type=console \t\t\t load from console input
                \t\t -size=<size> -type=random \t\t\t load random user list
                """;
    }
}
