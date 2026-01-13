package ru.aston.finalproject.service.loader;

import lombok.AllArgsConstructor;
import ru.aston.finalproject.appEnviroment.AppException;
import ru.aston.finalproject.appEnviroment.AppRequest;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.staticTools.Message;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@AllArgsConstructor
public class FileDataLoader<T> implements DataLoader<T> {
    private static final String FILE_PATH_PARAMETER = "-path";
    private final Parsing<T> parser;

    @Override
    public Stream<T> loadEntityList(Integer size, AppRequest request) {
        String filePath = request.getStringParameter(FILE_PATH_PARAMETER);

        try {
            return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                    .map(parser::parse)
                    .limit(size);
        } catch (IOException e) {
            throw new AppException(Message.EXCEPTION_FILE_NOT_FOUND_X.formatted(filePath));
        }
    }
}
