package ru.aston.finalproject.service.loader;

import lombok.Getter;
import lombok.Setter;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.constants.ConstantFields;
import ru.aston.finalproject.parser.Parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class ConsoleDataLoader<T> implements DataLoader<T> {
    private Parsing<T> parser;

    public ConsoleDataLoader(Parsing<T> parser) {
        this.parser = parser;
    }

    @Override
    public List<T> loadEntityList(Integer size) {
        List<T> resultList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Enter users, expected format - %s%n", ConstantFields.USER_FORMAT);
            while (size > 0) {
                String line = reader.readLine();
                T entity = parser.parse(line);
                resultList.add(entity);
                size--;
            }
        } catch (IOException e) {
            throw new AppException("Input stream exception in console loader");
        }
        return resultList;
    }
}
