package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;

public class HelpAction extends AppAction {
    @Override
    public String action(AppData appData, String[] args) throws AppException {
        return """
                help \t list available commands and required arguments
                print \t print current saved list
                load \t load user list
                \t\t <size> -file <file-path> \t load from file path
                \t\t <size> -console \t\t\t load from console input
                \t\t <size> -random \t\t\t load random user list
                """;
    }
}
