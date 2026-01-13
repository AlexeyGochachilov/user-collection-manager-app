package ru.aston.finalproject;

import org.apache.commons.lang3.StringUtils;
import ru.aston.finalproject.actions.AppAction;
import ru.aston.finalproject.actions.ClearAction;
import ru.aston.finalproject.actions.HelpAction;
import ru.aston.finalproject.actions.LoadAction;
import ru.aston.finalproject.actions.PrintAction;
import ru.aston.finalproject.actions.WriteAction;
import ru.aston.finalproject.appEnviroment.AppData;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.staticTools.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class AppRunner {
    public final static String HELP = "help";
    public final static String CLEAR_USERS = "clear";
    public final static String LOAD_USERS = "load";
    public final static String PRINT_USERS = "print";
    public final static String WRITE_ALL_USERS = "write";
    public final static String SORTING_ALL_USER = "sort";

    private static final Map<String, AppAction> actionMap = Map.of(
            HELP, new HelpAction(),
            CLEAR_USERS, new ClearAction(),
            LOAD_USERS, new LoadAction(),
            PRINT_USERS, new PrintAction(),
            WRITE_ALL_USERS, new WriteAction(),
            SORTING_ALL_USER, new SortingAction()
    );

    public static void main(String[] args) {
        try (BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in))) {
            AppData appData = new AppData();

            while (true) {
                String rawInput = inReader.readLine();
                if (StringUtils.isBlank(rawInput)) {
                    continue;
                }

                AppRequest appRequest = AppRequest.createRequest(rawInput);
                if (appRequest.isExitRequest()) {
                    break;
                }

                String command = appRequest.getCommandName();
                if (actionMap.containsKey(command)) {
                    AppAction action = actionMap.get(command);
                    try {
                        action.action(appData, appRequest);
                    } catch (AppException exception) {
                        System.out.println(exception.getMessage());
                    }
                    continue;
                }

                System.out.println(String.format(Message.EXCEPTION_WRONG_REQUEST_SYNTAXES_X, command));
            }
        } catch (IOException exception) {
            System.out.println(String.format(Message.INPUT_ERROR_X, exception.getMessage()));
        }
    }
}
