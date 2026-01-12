package ru.aston.finalproject.app;

import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.parser.UserParser;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.FileDataLoader;
import ru.aston.finalproject.service.loader.RandomUserDataLoader;
import ru.aston.finalproject.service.loader.UserLoaderService;
import ru.aston.finalproject.service.sorting.MergeSorter;
import ru.aston.finalproject.service.sorting.Sorter;
import ru.aston.finalproject.service.sorting.StrangeSorter;
import ru.aston.finalproject.service.writer.FileWriter;
import ru.aston.finalproject.util.CustomArrayList;

import java.util.List;

@Setter
@Getter
public class AppData {
    private final UserParser userParser = new UserParser();
    private final FileDataLoader<User> userFileDataLoader = new FileDataLoader<>(userParser);
    private final ConsoleDataLoader<User> userConsoleDataLoader = new ConsoleDataLoader<>(userParser);
    private final Faker dataFaker = new Faker();
    private final RandomUserDataLoader randomUserDataLoader = new RandomUserDataLoader(dataFaker);
    private final UserLoaderService userService = new UserLoaderService(userFileDataLoader,
            userConsoleDataLoader, randomUserDataLoader);
    private final FileWriter<User> fileWriter = new FileWriter<>(userParser);
    private final Sorter sorter = new MergeSorter();
    private final StrangeSorter strangeSorter = new StrangeSorter(sorter);

    private List<User> userList = new CustomArrayList<>();
}
