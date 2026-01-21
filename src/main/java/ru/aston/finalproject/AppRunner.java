package ru.aston.finalproject;

import org.apache.commons.lang3.StringUtils;
import ru.aston.finalproject.actions.AppAction;
import ru.aston.finalproject.actions.ClearAction;
import ru.aston.finalproject.actions.CountAction;
import ru.aston.finalproject.actions.HelpAction;
import ru.aston.finalproject.actions.LoadAction;
import ru.aston.finalproject.actions.LoadStockAction;
import ru.aston.finalproject.actions.PrintAction;
import ru.aston.finalproject.actions.ShowAction;
import ru.aston.finalproject.actions.SortingAction;
import ru.aston.finalproject.actions.WriteAction;
import ru.aston.finalproject.environment.AppData;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.util.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class AppRunner {
    public static final String HELP = "help";
    public static final String CLEAR_USERS = "clear";
    public static final String LOAD_USERS = "load_user";
    public static final String LOAD_STOCKS = "load_stock";
    public static final String PRINT_USERS = "print";
    public static final String SHOW_STOCK = "show";
    public static final String WRITE_ALL_USERS = "write";
    public static final String COUNT_ENTRIES = "count";
    public static final String SORTING_ALL_USER = "sort";

    private static final Map<String, AppAction> actionMap = Map.of(
            HELP, new HelpAction(),
            CLEAR_USERS, new ClearAction(),
            LOAD_USERS, new LoadAction(),
            LOAD_STOCKS, new LoadStockAction(),
            PRINT_USERS, new PrintAction(),
            SHOW_STOCK, new ShowAction(),
            WRITE_ALL_USERS, new WriteAction(),
            COUNT_ENTRIES, new CountAction(),
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

                System.out.println(String.format(Message.WRONG_REQUEST_SYNTAXES_X, command));
            }
        } catch (IOException exception) {
            System.out.println(String.format(Message.INPUT_ERROR_X, exception.getMessage()));
        }
    }
}
