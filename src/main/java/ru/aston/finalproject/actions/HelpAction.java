package ru.aston.finalproject.actions;

import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;

public class HelpAction extends AppAction {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);

        String help = """
                help \t list available commands and required arguments
                print \t print current saved list
                clear \t clear current saved list
                load \t load user list
                \t\t -size=<size> -type=file -path=<path-to-file> \t load from file path
                \t\t -size=<size> -type=console \t\t\t load from console input
                \t\t -size=<size> -type=random \t\t\t load random user list
                write \t write current list to file
                \t\t -file=<file-path> \t\t\t write to file (uses current parser format)
                sort \t sorting current list to file
                \t\t -by=<field> \t\t sort by field (name, age, email)
                """;
        System.out.println(help);
    }
}
