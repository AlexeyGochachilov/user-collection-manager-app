package ru.aston.finalproject.app.actions;

import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.util.Message;

public class HelpAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 0;

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        if (!EXPECTED_PARAMETERS_AMOUNT.equals(request.getParametersAmount())){
            throw new AppException(Message.EXCEPTION_WRONG_PARAMETERS_AMOUNT);
        }

        String help = """
                help \t list available commands and required arguments
                print \t print current saved list
                load \t load user list
                \t\t -size=<size> -type=file -file=<file-path> \t load from file path
                \t\t -size=<size> -type=console \t\t\t load from console input
                \t\t -size=<size> -type=random \t\t\t load random user list
                """;
        System.out.println(help);
    }
}
