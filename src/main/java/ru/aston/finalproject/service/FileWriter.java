package ru.aston.finalproject.service;

import lombok.NonNull;
import ru.aston.finalproject.parser.Parsing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static ru.aston.finalproject.constants.ConstantMethods.checkedStringOnEmpty;

public class FileWriter<T> {
    private static final int FLUSH_THRESHOLD = 1000;
    private static final String LINE_SEPARATOR = "\n";
    private static final String NUMBER_SEPARATOR = ") ";

    @NonNull
    private final Parsing<T> parser;

    public FileWriter(@NonNull Parsing<T> parser) {
        this.parser = parser;
    }

    public void write(@NonNull List<T> items, String filePath) {
        validateInput(items, filePath);

        if (items.isEmpty()) {
            createEmptyFile(filePath);
            return;
        }

        try {
            writeItemsToFile(items, filePath);
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Failed to write %d items to file '%s'", items.size(), filePath),
                    e
            );
        }
    }

    private void validateInput(List<T> items, String filePath) {
        Objects.requireNonNull(items, "Items list cannot be null");
        checkedStringOnEmpty(filePath);
    }

    private void createEmptyFile(String filePath) {
        new File(filePath).getParentFile().mkdirs();
    }

    private void writeItemsToFile(List<T> items, String filePath) throws IOException {
        int digitCount = calculateDigitCount(items.size());

        try (BufferedWriter writer = createWriter(filePath)) {
            writeFormattedItems(items, writer, digitCount);
        }
    }

    private BufferedWriter createWriter(String filePath) throws IOException {
        return new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filePath, true),
                        StandardCharsets.UTF_8
                )
        );
    }

    private void writeFormattedItems(List<T> items, BufferedWriter writer, int digitCount)
            throws IOException {
        for (int i = 0; i < items.size(); i++) {
            writeFormattedLine(writer, items.get(i), i + 1, digitCount);
            flushIfNeeded(writer, i + 1);
        }
        writer.flush();
    }

    private void writeFormattedLine(BufferedWriter writer, T item, int lineNumber, int digitCount)
            throws IOException {
        String formattedLine = formatLine(item, lineNumber, digitCount);
        writer.write(formattedLine);
    }

    private String formatLine(T item, int lineNumber, int digitCount) {
        String number = formatLineNumber(lineNumber, digitCount);
        return number + NUMBER_SEPARATOR + parser.parseToString(item) + LINE_SEPARATOR;
    }

    private String formatLineNumber(int currentLine, int digitCount) {
        return String.format("%0" + digitCount + "d", currentLine);
    }

    private int calculateDigitCount(int totalLines) {
        return String.valueOf(totalLines).length();
    }

    private void flushIfNeeded(BufferedWriter writer, int currentLine) throws IOException {
        if (currentLine % FLUSH_THRESHOLD == 0) {
            writer.flush();
        }
    }
}