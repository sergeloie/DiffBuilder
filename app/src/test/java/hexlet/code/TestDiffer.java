package hexlet.code;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class TestDiffer {

    private ClassLoader classLoader = this.getClass().getClassLoader();
    private Path pathFile11 = Paths.get(Objects.requireNonNull(classLoader.getResource("file11.json")).getFile());
    private Path pathFile12 = Paths.get(Objects.requireNonNull(classLoader.getResource("file12.json")).getFile());
    private Path pathFile21 = Paths.get(Objects.requireNonNull(classLoader.getResource("file21.yml")).getFile());
    private Path pathFile22 = Paths.get(Objects.requireNonNull(classLoader.getResource("file22.yml")).getFile());
    private Path pathStylishLong = Paths.get(Objects.requireNonNull(
            classLoader.getResource("expectedStylishLong.txt")).getFile());
    private Path pathPlainLong = Paths.get(Objects.requireNonNull(
            classLoader.getResource("expectedPlainLong.txt")).getFile());
    private Path pathJsonLong = Paths.get(Objects.requireNonNull(
            classLoader.getResource("expectedJsonLong.txt")).getFile());

    private String stringPath11 = pathFile11.toString();
    private String stringPath12 = pathFile12.toString();
    private String stringPath21 = pathFile21.toString();
    private String stringPath22 = pathFile22.toString();

    private String expectedStylishLong = Files.readString(pathStylishLong);
    private String expectedPlainLong = Files.readString(pathPlainLong);
    private String expectedJsonLong = Files.readString(pathJsonLong);

    public TestDiffer() throws IOException {
    }

    @Test
    public void mainTest() throws IOException {
        String actual = generate(stringPath11, stringPath12);
        String actualStylish = generate(stringPath21, stringPath22, "stylish");
        String actualPlain = generate(stringPath21, stringPath22, "plain");
        String actualJson = generate(stringPath21, stringPath22, "json");
        assertEquals(actual, expectedStylishLong);
        assertEquals(actualStylish, expectedStylishLong);
        assertEquals(actualPlain, expectedPlainLong);
        assertEquals(actualJson, expectedJsonLong);
    }
}
