package ru.aston.finalproject.service.loader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.entity.user.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserLoaderServiceTest {
    @Mock
    FileDataLoader<User> userFileDataLoader;
    @Mock
    AppRequest appRequest;
    @Mock
    ConsoleDataLoader<User> userConsoleDataLoader;
    @Mock
    RandomUserDataLoader randomUserDataLoader;
    @InjectMocks
    private UserLoaderService userLoaderService;

    @Test
    void givenNonValidLoaderKey_whenCallLoadEntityListAndCollect_thenThrowsAppException() {
        int size = 10;
        String nonValidLoaderKey = "nonValid";

        assertThrows(AppException.class, () -> userLoaderService.loadEntityList(nonValidLoaderKey, size, appRequest).toList());
    }

    @ParameterizedTest
    @ValueSource(strings = {UserLoaderService.LOAD_FROM_CONSOLE, UserLoaderService.LOAD_RANDOM, UserLoaderService.LOAD_FROM_FILE})
    void givenValidLoaderKey_whenCallLoadEntityListAndCollect_thenThrowsAppException(String loaderKey) {
        int size = 10;

        assertDoesNotThrow(() -> userLoaderService.loadEntityList(loaderKey, size, appRequest).toList());
    }

    @Test
    void givenValidConsoleLoaderKey_whenCallLoadEntityListAndCollect_thenVerifyConsoleDataLoaderLoadEntityListCalled() {
        String loaderKey = UserLoaderService.LOAD_FROM_CONSOLE;
        Integer size = 5;

        userLoaderService.loadEntityList(loaderKey, size, appRequest).toList();
        verify(userConsoleDataLoader, only()).loadEntityList(size, appRequest);
    }

    @Test
    void givenValidRandomLoaderKey_whenCallLoadEntityListAndCollect_thenVerifyRandomUserDataLoaderLoadEntityListCalled() {
        String loaderKey = UserLoaderService.LOAD_RANDOM;
        Integer size = 5;

        userLoaderService.loadEntityList(loaderKey, size, appRequest).toList();
        verify(randomUserDataLoader, only()).loadEntityList(size, appRequest);
    }

    @Test
    void givenValidFileLoaderKey_whenCallLoadEntityListAndCollect_thenVerifyFileDataLoaderLoadEntityListCalled() {
        String loaderKey = UserLoaderService.LOAD_FROM_FILE;
        Integer size = 5;

        userLoaderService.loadEntityList(loaderKey, size, appRequest).toList();
        verify(userFileDataLoader, only()).loadEntityList(size, appRequest);
    }
}