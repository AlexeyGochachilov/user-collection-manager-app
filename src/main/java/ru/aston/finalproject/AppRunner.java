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

public class AppRunner {
    private static AppData<?> appData = null;
    private static BufferedReader reader = null;

    public static final String HELP = "help";
    public static final String CLEAR_LIST_OF_ENTITY = "clear";
    public static final String LOAD = "load";
    public static final String PRINT_ENTITY = "print";
    public static final String SHOW_ENTITY = "show";
    public static final String WRITE_ALL_ENTITIES = "write";
    public static final String COUNT_ENTRIES_ENTITY = "count";
    public static final String SORTING_ENTITY = "sort";
    public static final String FILTER_STOCK = "filter";
    public static final String EXIT = "exit";


    private static final Map<String, AppAction> actionMap = Map.of(
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
            reader = inReader;
            runApplication();
        } catch (IOException exception) {
            System.out.println(String.format(Message.INPUT_ERROR_X, exception.getMessage()));
            closeResources();
        }
    }

    private static void runApplication() throws IOException {
        while (true) {
            try {
                if (appData == null) {
                    handleEntitySelection();
                } else {
                    handleUserCommand();
                }
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
                appData = null;
            }
        }
    }

    private static void handleEntitySelection() throws IOException {
        System.out.println("\n=== Select an entity to work with ===");
        System.out.println("Available entities: " + String.join(", ", dataMap.keySet()));
        System.out.print("Enter the name of the entity (or 'exit' for exit): ");

        String input = readInput().trim().toLowerCase();

        if (EXIT.equalsIgnoreCase(input)) {
            System.exit(0);
        }

        if (!dataMap.containsKey(input)) {
            System.out.println("Error: Unknown entity '" + input + "'");
            return;
        }

        appData = dataMap.get(input);
        System.out.println("The selected entity: " + input);
        printAvailableCommands();
    }

    private static void handleUserCommand() throws IOException {
        System.out.print("\nEnter the command (or 'help' for help, 'change' for changing the entity): ");
        String input = readInput();

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
            System.out.println(String.format(Message.WRONG_REQUEST_SYNTAXES_X, command));
            printAvailableCommands();
            return;
        }

        try {
            AppAction action = actionMap.get(command);
            action.action(appData, appRequest);
        } catch (AppException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Error: This command is not supported for the current entity");
            System.out.println("Use the 'change' command to change the entity");
        }
    }

    private static String readInput() throws IOException {
        String input = reader.readLine();
        if (input == null) {
            System.out.println("\nShut down...");
            System.exit(0);
        }
        return input.trim();
    }

    private static void printAvailableCommands() {
        System.out.println("\nAvailable Commands:");
        System.out.println("- help      : show help");
        System.out.println("- load      : load data");
        System.out.println("- print     : print data");
        System.out.println("- show      : show data (for stocks)");
        System.out.println("- write     : save data in file");
        System.out.println("- count     : count needed entity");
        System.out.println("- sort      : sort data");
        System.out.println("- filter    : filter data (for stocks)");
        System.out.println("- clear     : clear data");
        System.out.println("- change    : change entity");
        System.out.println("- exit      : exit fom program");
    }

    private static void closeResources() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error when closing resources: " + e.getMessage());
            }
        }
    }
}