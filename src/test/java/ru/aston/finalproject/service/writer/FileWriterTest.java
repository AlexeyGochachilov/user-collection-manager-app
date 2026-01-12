package ru.aston.finalproject.service.writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.collection.CustomArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileWriterTest {

    @TempDir
    private Path tempDir;

    @Mock
    private Parsing<String> mockParser;

    private FileWriter<String> fileWriter;
    private Path testFilePath;

    @BeforeEach
    void setUp() {
        fileWriter = new FileWriter<>(mockParser);
        testFilePath = tempDir.resolve("test_output.txt");
    }

    @Nested
    class PositiveScenarios {

        @Test
        void givenNonEmptyListAndValidPath_whenWrite_thenFileContainsFormattedLines() throws IOException {
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
        void givenExistingFile_whenWrite_thenDataAppendedToFile() throws IOException {
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
        void givenEmptyList_whenWrite_thenOnlyDirectoryCreated() {
            Path deepPath = tempDir.resolve("a/b/c/test.txt");
            fileWriter.write(List.of(), deepPath.toString());
            assertTrue(Files.exists(deepPath.getParent()));
            assertFalse(Files.exists(deepPath));
            verify(mockParser, never()).parseToString(any());
        }

        @Test
        void givenMoreThan1000Lines_whenWrite_thenFlushPerformedByThreshold() throws IOException {
            List<String> largeList = creatLargeList();
            when(mockParser.parseToString(any()))
                    .thenAnswer(invocation -> invocation.getArgument(0).toString());
            fileWriter.write(largeList, testFilePath.toString());
            List<String> lines = Files.readAllLines(testFilePath);
            assertEquals(1100, lines.size());
        }

        private List<String> creatLargeList() {
            List<String> largeList = new CustomArrayList<>();
            for (int i = 0; i < 1100; i++) {
                largeList.add("item" + i);
            }
            return largeList;
        }
    }

    @Nested
    class InputValidationTests {

        @Test
        void givenNullList_whenWrite_thenThrowNullPointerException() {
            String filePath = testFilePath.toString();
            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> fileWriter.write(null, filePath)
            );
            assertEquals("items is marked non-null but is null", exception.getMessage());
        }

        @Test
        void givenEmptyFilePath_whenWrite_thenThrowAppException() {
            List<String> items = List.of("test");
            AppException exception = assertThrows(
                    AppException.class,
                    () -> fileWriter.write(items, "")
            );
            assertNotNull(exception.getMessage());
        }

        @Test
        void givenNullFilePath_whenWrite_thenThrowAppException() {
            List<String> items = List.of("test");
            AppException exception = assertThrows(
                    AppException.class,
                    () -> fileWriter.write(items, null)
            );
            assertNotNull(exception.getMessage());
        }
    }

    @Nested
    class IOExceptionHandlingTests {


        @Test
        void givenInvalidPath_whenWrite_thenThrowAppExceptionOnDirectoryCreation() {
            String invalidPath = "NUL:\\invalid\\path\\file.txt";
            List<String> items = List.of("test");
            AppException exception = assertThrows(
                    AppException.class,
                    () -> fileWriter.write(items, invalidPath)
            );
            assertTrue(exception.getMessage().contains("Failed to write"));
        }
    }

    @Nested
    class ParserIntegrationTests {

        @Test
        void givenListOfItems_whenWrite_thenParserCalledForEachItem() {
            List<String> items = List.of("a", "b", "c");
            when(mockParser.parseToString(any()))
                    .thenReturn("parsed");
            fileWriter.write(items, testFilePath.toString());
            verify(mockParser, times(3)).parseToString(any());
            verify(mockParser).parseToString("a");
            verify(mockParser).parseToString("b");
            verify(mockParser).parseToString("c");
        }

        @Test
        void givenParserReturningNull_whenWrite_thenFileContainsNullString() throws IOException {
            List<String> items = List.of("item1");
            when(mockParser.parseToString(any())).thenReturn(null);
            fileWriter.write(items, testFilePath.toString());
            List<String> lines = Files.readAllLines(testFilePath);
            assertEquals(1, lines.size());
            assertEquals("null", lines.get(0));
        }
    }

    @Nested
    class EdgeCasesTests {

        @Test
        void givenUtf8Characters_whenWrite_thenFileCorrectlyEncoded() throws IOException {
            List<String> items = List.of("строка на русском", "ümlaut", "🎉 emoji");
            when(mockParser.parseToString(any()))
                    .thenAnswer(invocation -> invocation.getArgument(0).toString());
            fileWriter.write(items, testFilePath.toString());
            String content = Files.readString(testFilePath);
            assertTrue(content.contains("русском"));
            assertTrue(content.contains("ümlaut"));
            assertTrue(content.contains("🎉"));
        }
    }
}
