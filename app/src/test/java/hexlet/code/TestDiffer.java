package hexlet.code;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestDiffer {

    private final String file21 = "src/test/resources/file21.yml";
    private final String file22 = "src/test/resources/file22.yml";
    private final String file11 = "src/test/resources/file11.json";
    private final String file12 = "src/test/resources/file12.json";
    private static String expectedStylishLong;
    private static String expectedPlainLong;
    private static String expectedJsonLong;


    @BeforeAll
    static void beforeAll() throws IOException {

        expectedStylishLong = Files.readString(Path.of("src/test/resources/expectedStylishLong.txt"));
        expectedPlainLong = Files.readString(Path.of("src/test/resources/expectedPlainLong.txt"));
        expectedJsonLong = Files.readString(Path.of("src/test/resources/expectedJsonLong.txt"));

    }

    @Test
    public void testDefaultFormat() throws IOException {
        String actual = generate(file11, file12);
        assertEquals(actual, expectedStylishLong);
    }

    @Test
    public void testStylishFormat() throws IOException {
        String actualStylish = generate(file21,
                file22,
                "stylish");
        assertEquals(actualStylish, expectedStylishLong);
    }

    @Test
    public void testPlainFormat() throws IOException {
        String actualPlain = generate(file21,
                file22,
                "plain");
        assertEquals(actualPlain, expectedPlainLong);
    }

    @Test
    public void testJsonFormat() throws IOException {
        String actualJson = generate(file21,
                file22,
                "json");
        assertEquals(actualJson, expectedJsonLong);
    }
}
