package ru.aston.finalproject.app;

import ru.aston.finalproject.app.actions.AppAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class App {

    private static final Set<String> exitWords = Set.of(
            "exit",
            "^Z"
    );
    private static final Map<String, AppAction> actionMap = Map.of(
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
