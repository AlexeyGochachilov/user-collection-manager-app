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
                \t\t -size=<size> -type=file \t -path=<path-to-file> \t load from file path
                \t\t -size=<size> -type=console \t\t\t\t\t\t load from console input
                \t\t -size=<size> -type=random \t\t\t\t\t\t\t load random user list
                write \t write current list to file
                \t\t -file=<file-path> \t\t\t write to file (uses current parser format)
                sort \t sorting current list to file
                \t\t -basic \t\t natural order sorting
                \t\t -strange \t\t sorting only by even age
                count \t count entries of a given user
                \t\t -threads=<tread count> \t thread count for multithread search
                \t\t (set at 1 for sequential search)
                """;
        System.out.println(help);
    }
}
