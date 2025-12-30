package ru.aston.finalproject.service;

import lombok.AllArgsConstructor;
import ru.aston.finalproject.parser.Parsing;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class FileWriter<T> {

    private final Parsing<T> parser;

    public void write(List<T> list, String filePath) {

        Iterator<T> iterator = list.iterator();
        StringBuilder content = new StringBuilder();
        while (iterator.hasNext()) {
            content.append(this.parser.parseToString(iterator.next())).
                    append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath, true),
                        StandardCharsets.UTF_8))) {
            writer.write(content.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
