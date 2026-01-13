package ru.aston.finalproject.environment;

import lombok.Getter;
import lombok.Setter;
import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.entity.user.BuildUser;
import ru.aston.finalproject.entity.user.User;
import ru.aston.finalproject.entity.user.UserDataFaker;
import ru.aston.finalproject.parser.UserParser;
import ru.aston.finalproject.service.counters.MultithreadCounter;
import ru.aston.finalproject.service.loader.ConsoleDataLoader;
import ru.aston.finalproject.service.loader.FileDataLoader;
import ru.aston.finalproject.service.loader.RandomUserDataLoader;
import ru.aston.finalproject.service.loader.UserLoaderService;
import ru.aston.finalproject.service.sorting.MergeSorter;
import ru.aston.finalproject.service.sorting.Sorter;
import ru.aston.finalproject.service.sorting.StrangeSorter;
import ru.aston.finalproject.service.writer.FileWriter;

import java.util.List;

@Setter
@Getter
public class AppData {
    private final UserParser userParser = new UserParser();
    private final BuildUser buildUser = new BuildUser();
    private final FileDataLoader<User> userFileDataLoader = new FileDataLoader<>(userParser);
    private final ConsoleDataLoader<User> userConsoleDataLoader = new ConsoleDataLoader<>(userParser);
    private final UserDataFaker userDataFaker = new UserDataFaker();
    private final RandomUserDataLoader randomUserDataLoader = new RandomUserDataLoader(userDataFaker, buildUser);
    private final UserLoaderService userService = new UserLoaderService(userFileDataLoader,
            userConsoleDataLoader, randomUserDataLoader);
    private final FileWriter<User> fileWriter = new FileWriter<>(userParser);
    private final Sorter sorter = new MergeSorter();
    private final StrangeSorter strangeSorter = new StrangeSorter(sorter);
    private final MultithreadCounter<User> entryCounter = new MultithreadCounter<>();

    private List<User> userList = new CustomArrayList<>();
}
