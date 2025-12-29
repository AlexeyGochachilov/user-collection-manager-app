package ru.aston.finalproject.service;

import lombok.AllArgsConstructor;
import ru.aston.finalproject.parser.Parsing;

import java.util.List;

@AllArgsConstructor
public class FileWriter<T> {
    private final Parsing<T> parser;

    public void write(List<T> list, String filePath) {
        // TODO: код для записи в файл в режиме добавления данных
        // для текстирования класса создать объект класса и передать в него парсер
        // пример: UserParser userParser = new UserParser();
        // пример: FileWriter<User> listSorter = new FileWriter<>(userParser);
    }
}
