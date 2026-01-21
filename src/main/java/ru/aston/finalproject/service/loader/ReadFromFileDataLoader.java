package ru.aston.finalproject.service.loader;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.aston.finalproject.collection.CustomArrayList;
import ru.aston.finalproject.environment.AppException;
import ru.aston.finalproject.environment.AppRequest;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.util.Message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
public class ReadFromFileDataLoader<T> implements DataLoader<T> {
    private static final String FILE_PATH_PARAMETER = "-read";
    private final Parsing<T> parser;

    @Override
    public Stream<T> loadEntityList(Integer size, AppRequest request) {
        String filePath = request.getStringParameter(FILE_PATH_PARAMETER);

        List<String> list = new CustomArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (StringUtils.isNotBlank(line = reader.readLine())) {
                String[] stockConstr = line.trim().split("\\s+");
                if (stockConstr.length != 9) {
                    continue;
                }
                list.add(line);
            }
            return list.stream().map(parser::parse).limit(size);
        } catch (IOException e) {
            throw new AppException(Message.FILE_NOT_FOUND_X.formatted(filePath));
        }
    }
}
