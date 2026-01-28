package ru.aston.finalproject.actions;

import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;

public class HelpAction<T> extends AppAction<T> {
    private static final Integer EXPECTED_MAX_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData<T> appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_MAX_PARAMETERS_AMOUNT);

        String help = """
                help \t list available commands and required arguments
                print \t print current saved list as string
                show \t print current saved list with toString
                clear \t clear current saved list
                load \t load stock ore user list
                \t\t -size=<size> -type=file \t -path=<path-to-file> \t load from file path
                \t\t -size=<size> -type=console \t\t\t\t\t\t load from console input
                \t\t -size=<size> -type=random \t\t\t\t\t\t\t load random user list
                write \t write current list to file
                \t\t -file=<file-path> \t\t\t write to file (uses current parser format)
                sort \t sorting current list to file
                \t\t -basic \t\t\t\t\t natural order sorting for user and stock
                \t\t -strange \t\t\t\t\t sorting only by even age user
                filter \t remove stocks from list auto filters
                \t\t -value=<value> with ground parameter
                \t\t -first=<value> -second=<value> with both borders
                \t\t in value need write PE stock
                count \t count entries of a given user
                \t\t -threads=<tread count> \t thread count for multithread search
                \t\t (set at 1 for sequential search)
                change \t to change entity
                """;
        System.out.println(help);
    }
}
