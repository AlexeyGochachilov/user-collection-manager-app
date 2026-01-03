package ru.aston.finalproject.service;

import lombok.Getter;
import lombok.Setter;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.parser.Parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class FileDataLoader<T> implements DataLoader<T> {
    private final Parsing<T> parser;
    private String filePath;

    public FileDataLoader(Parsing<T> parser) {
        this.parser = parser;
    }

    @Override
    public List<T> loadEntityList(Integer size) {
        if (size <= 0) {
            throw new AppException("Please enter a number of elements greater than zero.");
        }
        List<T> resultList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            while (size > 0) {
                String line = reader.readLine();
                T entity = parser.parse(line);
                resultList.add(entity);
                size--;
            }
        } catch (IOException e) {
            throw new AppException("File %s not found".formatted(filePath));
        }
        return resultList;
    }
}
