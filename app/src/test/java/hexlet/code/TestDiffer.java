package hexlet.code;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestDiffer {

    private String expectedStylishLong;
    private String expectedPlainLong;
    private String expectedJsonLong;



    @BeforeEach
    final void beforeEach() throws IOException {

        expectedStylishLong = Files.readString(Path.of("src/test/resources/expectedStylishLong.txt"));
        expectedPlainLong = Files.readString(Path.of("src/test/resources/expectedPlainLong.txt"));
        expectedJsonLong = Files.readString(Path.of("src/test/resources/expectedJsonLong.txt"));

    }

    @Test
    public void testDefaultFormat() throws IOException {
        String actual = generate("src/test/resources/file11.json", "src/test/resources/file12.json");
        assertEquals(actual, expectedStylishLong);
    }

    @Test
    public void testStylishFormat() throws IOException {
        String actualStylish = generate("src/test/resources/file21.yml",
                "src/test/resources/file22.yml",
                "stylish");
        assertEquals(actualStylish, expectedStylishLong);
    }

    @Test
    public void testPlainFormat() throws IOException {
        String actualPlain = generate("src/test/resources/file21.yml",
                "src/test/resources/file22.yml",
                "plain");
        assertEquals(actualPlain, expectedPlainLong);
    }

    @Test
    public void testJsonFormat() throws IOException {
        String actualJson = generate("src/test/resources/file21.yml",
                "src/test/resources/file22.yml",
                "json");
        assertEquals(actualJson, expectedJsonLong);
    }
}
