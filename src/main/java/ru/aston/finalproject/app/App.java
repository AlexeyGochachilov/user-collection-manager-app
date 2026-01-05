package ru.aston.finalproject.app;

import ru.aston.finalproject.app.actions.AppAction;
import ru.aston.finalproject.app.actions.HelpAction;
import ru.aston.finalproject.app.actions.LoadAction;
import ru.aston.finalproject.app.actions.PrintAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class App {
    public final static String HELP = "help";
    public final static String LOAD_USERS = "load";
    public final static String PRINT_USERS = "print";

    private static final Set<String> exitWords = Set.of(
            "exit",
            "^Z"
    );
    private static final Map<String, AppAction> actionMap = Map.of(
            HELP, new HelpAction(),
            LOAD_USERS, new LoadAction(),
            PRINT_USERS, new PrintAction()
    );

    public static void main(String[] args) {
        try (BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in))){
            AppData appData = new AppData();

            while (true) {
                String rawInput = inReader.readLine();
                String[] splitInput = rawInput.split("\s+");

                if (splitInput.length == 0) {
                    continue;
                }

                String command = splitInput[0];
                String[] arguments = Arrays.stream(splitInput).skip(1).toArray(String[]::new);

                if (exitWords.contains(command)) {
                    break;
                }

                if (actionMap.containsKey(command)) {
                    AppAction action = actionMap.get(command);
                    try {
                        String consoleResult = action.action(appData, arguments);
                        System.out.println(consoleResult);
                    } catch (AppException exception) {
                        System.out.println(exception.getMessage());
                    }
                    continue;
                }

                System.out.println(String.format(
                        "Неверный синтаксис команды - \"%s\".",
                        command
                ));
            }
        } catch (IOException exception) {
            System.out.println(String.format(
                    "Ошибка ввода: %s", exception.getMessage()
            ));
        }


    }
}
