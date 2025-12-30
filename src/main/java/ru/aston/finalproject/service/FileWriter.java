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

    private String buildContent(@NonNull List<T> list) {

        StringBuilder content = new StringBuilder();
        int totalLines = list.size();
        int digitCount = String.valueOf(totalLines).length();

        for (int i = 0; i < list.size(); i++) {
            String lineNumber = formatLineNumber(i + 1, digitCount);
            content.append(lineNumber)
                    .append(") ")
                    .append(parser.parseToString(list.get(i)))
                    .append("\n");
        }
        return content.toString();
    }

    private String formatLineNumber(int currentLine, int digitCount) {
        return String.format("%0" + digitCount + "d", currentLine);
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