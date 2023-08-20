package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
//import org.assertj.core.api.Assert;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestDiffer {



    @Test
    public void dummyTest() {
        assertEquals(true, true);
    }

    @Test
    public void testDifferJSON() throws JsonProcessingException {
        String jsonString1 = """
                {
                  "host": "hexlet.io",
                  "timeout": 50,
                  "proxy": "123.234.53.22",
                  "follow": false
                }""";

        String jsonString2 = """
                {
                  "timeout": 20,
                  "verbose": true,
                  "host": "hexlet.io"
                }""";
        String expected = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        String actual = Differ.generate(jsonString1, jsonString2);
        assertEquals(expected, actual);
    }

    @Test
    public void testYAML() throws JsonProcessingException {
        String yamlString1 = """
                host: hexlet.io,
                timeout: 50,
                proxy: 123.234.53.22,
                follow: false
                """;

        String yamlString2 = """
                timeout: 20,
                verbose: true,
                host: hexlet.io""";
        String expected = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        String actual = Differ.generate(yamlString1, yamlString2);
        assertEquals(expected, actual);

    }

    @Test
    public void testReadFileWithClassLoader() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("file1.json").getFile());
        assertTrue(file.exists());
    }

}
