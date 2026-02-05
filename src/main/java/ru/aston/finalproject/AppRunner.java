package ru.aston.finalproject;

import org.apache.commons.lang3.StringUtils;
import ru.aston.finalproject.actions.AppAction;
import ru.aston.finalproject.actions.Action;
import ru.aston.finalproject.environment.appdata.Data;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.environment.appdata.AppData;
import ru.aston.finalproject.environment.appdata.StockAppData;
import ru.aston.finalproject.environment.appdata.UserAppData;
import ru.aston.finalproject.util.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ru.aston.finalproject.parser.StockParser.STOCK_FORMAT;
import static ru.aston.finalproject.parser.UserParser.USER_FORMAT;

public class AppRunner {
    private static AppData<?> appData = null;

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
        startMessage();
        String input = readInput(reader).trim().toLowerCase();

        try {
            appData = Data.valueOf(input).getAppData();
            System.out.println("The selected entity: " + input);
        } catch (IllegalArgumentException e) {
            System.out.println(Message.INVALID_DATA);
        }

        if (appData instanceof UserAppData) {
            System.out.printf((Message.ENTER_USERS_EXPECTED_FORMAT_S) + "%n", USER_FORMAT);
        } else if (appData instanceof StockAppData) {
            System.out.printf((Message.ENTER_STOCKS_EXPECTED_FORMAT_S) + "%n", STOCK_FORMAT);
        }
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

        try {
            AppAction appAction = Action.valueOf(input).getAppAction();
            appAction.action(appData, appRequest);
        } catch (AppException exception) {
            System.out.println("Error: " + exception.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Error: This command is not supported for the current entity");
            System.out.println("Use the 'change' command to change the entity");
        } catch (IllegalArgumentException e) {
            System.out.printf((Message.WRONG_REQUEST_SYNTAXES_X) + "%n", command);
            printHelpCommand();
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

    private static void startMessage() {
        System.out.println("\n=== Select an entity to work with ===");
        System.out.print("Available entities: ");
        for (Data data : Data.values()) {
            System.out.print(data.name() + " ");
        }
        System.out.println();
        System.out.print("Enter the name of the entity : ");
    }

    private static void printHelpCommand() {
        System.out.println("\nIf You don't know Commands. Write: help");
    }
}