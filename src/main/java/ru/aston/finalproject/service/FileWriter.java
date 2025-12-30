package ru.aston.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.aston.finalproject.parser.Parsing;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;

@AllArgsConstructor
public class FileWriter<T> {

    @NonNull
    private final Parsing<T> parser;

    public void write(@NonNull List<T> list, String filePath) {
        checkedStringOnEmpty(filePath);
        String content = buildContent(list);
        writeToFile(content, filePath);
    }

    private String buildContent(List<T> list) {
        StringBuilder content = new StringBuilder();
        for (T item : list) {
            content.append(parser.parseToString(item)).append("\n");
        }
        return content.toString();
    }

    private void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filePath, true),
                        StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw new FileWriteException(
                    String.format("Failed to write %d items to file '%s'",
                            content.lines().count(), filePath),
                    e
            );
        }
    }
}

class FileWriteException extends RuntimeException {
    public FileWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}