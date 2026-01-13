package ru.aston.finalproject.service.loader;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.entity.BuildUser;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.entity.UserDataFaker;

import java.util.stream.Stream;

@AllArgsConstructor
public class RandomUserDataLoader implements DataLoader<User> {
    private final UserDataFaker userDataFaker;
    private final BuildUser buildUser;

    @Override
    public Stream<User> loadEntityList(@NonNull Integer size, AppRequest request) {

        return Stream.generate(() -> buildUser.capitalizeNameAndNormalizedEmail(
                        userDataFaker.getRandomUserName(),
                        userDataFaker.getRandomUserEmail(),
                        userDataFaker.getRandomUserAge()))
                .limit(size);
    }
}
