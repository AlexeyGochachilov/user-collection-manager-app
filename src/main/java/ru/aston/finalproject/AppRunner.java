package ru.aston.finalproject;

import org.apache.commons.lang3.StringUtils;
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
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.environment.appdata.StockAppData;
import ru.aston.finalproject.environment.appdata.UserAppData;
import ru.aston.finalproject.util.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static ru.aston.finalproject.parser.StockParser.STOCK_FORMAT;
import static ru.aston.finalproject.parser.UserParser.USER_FORMAT;

public class AppRunner {
    private static AppData<?> appData = null;

    public static final String HELP = "help";
    public static final String CLEAR_LIST_OF_ENTITY = "clear";
    public static final String LOAD = "load";
    public static final String PRINT_ENTITY = "print";
    public static final String SHOW_ENTITY = "show";
    public static final String WRITE_ALL_ENTITIES = "write";
    public static final String COUNT_ENTRIES_ENTITY = "count";
    public static final String SORTING_ENTITY = "sort";
    public static final String FILTER_STOCK = "filter";

    private static final Map<String, AppAction<?>> actionMap = Map.of(
            HELP, new HelpAction(),
            CLEAR_LIST_OF_ENTITY, new ClearAction(),
            LOAD, new LoadAction(),
            PRINT_ENTITY, new PrintAction(),
            SHOW_ENTITY, new ShowAction(),
            WRITE_ALL_ENTITIES, new WriteAction(),
            COUNT_ENTRIES_ENTITY, new UsersCountAction(),
            SORTING_ENTITY, new UserSortingAction(),
            FILTER_STOCK, new RemovePartsOfStockAction()
    );

    public static final String USER = "user";
    public static final String STOCK = "stock";

    private static final Map<String, AppData<?>> dataMap = Map.of(
            USER, new UserAppData(),
            STOCK, new StockAppData()
    );

    public static void main(String[] args) {
        try (BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in))) {
            runApplication(inReader);
        } catch (IOException exception) {
            System.out.printf((Message.INPUT_ERROR_X) + "%n", exception.getMessage());
        }
    }

    private static void runApplication(BufferedReader reader) throws IOException {
        while (true) {
            try {
                if (appData == null) {
                    handleEntitySelection(reader);
                } else {
                    handleUserCommand(reader);
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                appData = null;
            }
        }
    }

    private static void handleEntitySelection(BufferedReader reader) throws IOException {
        System.out.println("\n=== Select an entity to work with ===");
        System.out.println("Available entities: " + String.join(", ", dataMap.keySet()));
        System.out.print("Enter the name of the entity (or 'exit' for exit): ");

        String input = readInput(reader).trim().toLowerCase();

        if (!dataMap.containsKey(input)) {
            System.out.println("Error: Unknown entity '" + input + "'");
            return;
        }

        appData = dataMap.get(input);
        System.out.println("The selected entity: " + input);

        if (appData instanceof UserAppData) {
            System.out.printf((Message.ENTER_USERS_EXPECTED_FORMAT_S) + "%n", USER_FORMAT);
        } else if (appData instanceof StockAppData) {
            System.out.printf((Message.ENTER_STOCKS_EXPECTED_FORMAT_S) + "%n", STOCK_FORMAT);
        }

        printHelpCommand();
    }

    private static void handleUserCommand(BufferedReader reader) throws IOException {
        String input = readInput(reader);

        if (StringUtils.isBlank(input)) {
            return;
        }

        AppRequest appRequest = AppRequest.createRequest(input);

        if (appRequest.isExitRequest()) {
            System.exit(0);
        }
        if (appRequest.isChangeEntityRequest()) {
            appData = null;
            return;
        }

        String command = appRequest.getCommandName();

        if (!actionMap.containsKey(command)) {
            System.out.printf((Message.WRONG_REQUEST_SYNTAXES_X) + "%n", command);
            printHelpCommand();
            return;
        }

        try {
            AppAction appAction = actionMap.get(command);
            appAction.action(appData, appRequest);
        } catch (AppException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Error: This command is not supported for the current entity");
            System.out.println("Use the 'change' command to change the entity");
        }
    }

    private static String readInput(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        if (input == null) {
            System.out.println("\nShut down...");
            System.exit(0);
        }
        return input.trim();
    }

    private static void printHelpCommand() {
        System.out.println("\nIf You don't know Commands? Write: help");
    }
}