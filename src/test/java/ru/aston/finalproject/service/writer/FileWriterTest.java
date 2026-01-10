package ru.aston.finalproject.service.writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.finalproject.parser.Parsing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileWriterTest {

    @TempDir
    Path tempDir;

    @Mock
    private Parsing<String> mockParser;

    private FileWriter<String> fileWriter;
    private Path testFilePath;

    @BeforeEach
    void init() {
        fileWriter = new FileWriter<>(mockParser);
        testFilePath = tempDir.resolve("test_output.txt");
    }

    @Test
    void write_shouldWriteFormattedLines_whenListIsNotEmpty() throws IOException {

        List<String> items = List.of("item1", "item2", "item3");
        when(mockParser.parseToString(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString());

        fileWriter.write(items, testFilePath.toString());

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(3, lines.size());
        assertEquals("item1", lines.get(0));
        assertEquals("item2", lines.get(1));
        assertEquals("item3", lines.get(2));

        verify(mockParser, times(3)).parseToString(any());
    }

    @Test
    void write_shouldAppendToFile_whenFileExists() throws IOException {

        Files.write(testFilePath, List.of("existing line"));
        when(mockParser.parseToString(any()))
                .thenReturn("new line");

        fileWriter.write(List.of("new item"), testFilePath.toString());

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(2, lines.size());
        assertTrue(lines.contains("existing line"));
        assertTrue(lines.contains("new line"));
    }

    @Test
    void write_shouldOnlyCreateDirectory_whenListIsEmpty() {

        Path deepPath = tempDir.resolve("a/b/c/test.txt");

        fileWriter.write(List.of(), deepPath.toString());

        assertTrue(Files.exists(deepPath.getParent()));
        assertFalse(Files.exists(deepPath));

        verify(mockParser, never()).parseToString(any());
    }

    @Test
    void write_shouldFlushAfterThreshold() throws IOException {

        List<String> largeList = IntStream.rangeClosed(1, 1500)
                .mapToObj(i -> "item" + i)
                .collect(Collectors.toList());

        when(mockParser.parseToString(any()))
                .thenAnswer(invocation -> invocation.getArgument(0).toString());

        fileWriter.write(largeList, testFilePath.toString());

        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(1500, lines.size());
        assertEquals("item1", lines.get(0));
        assertEquals("item1500", lines.get(1499));
    }
}
