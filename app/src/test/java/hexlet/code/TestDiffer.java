package hexlet.code;

import static hexlet.code.ParserYAML.parseYAMLfileToMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestDiffer {



    @Test
    public void dummyTest() {
        assertEquals(true, true);
    }

//    @Test
//    public void testDifferJSON() throws JsonProcessingException {
//        String jsonString1 = """
//                {
//                  "host": "hexlet.io",
//                  "timeout": 50,
//                  "proxy": "123.234.53.22",
//                  "follow": false
//                }""";
//
//        String jsonString2 = """
//                {
//                  "timeout": 20,
//                  "verbose": true,
//                  "host": "hexlet.io"
//                }""";
//        String expected = """
//                {
//                - follow: false
//                  host: hexlet.io
//                - proxy: 123.234.53.22
//                - timeout: 50
//                + timeout: 20
//                + verbose: true
//                }""";
//        String actual = Differ.generate_json(jsonString1, jsonString2);
//        assertEquals(expected, actual);
//    }

//    @Test
//    public void testYAML() throws JsonProcessingException {
//        String yamlString1 = """
//                host: hexlet.io,
//                timeout: 50,
//                proxy: 123.234.53.22,
//                follow: false
//                """;
//
//        String yamlString2 = """
//                timeout: 20,
//                verbose: true,
//                host: hexlet.io""";
//        String expected = """
//                {
//                - follow: false
//                  host: hexlet.io
//                - proxy: 123.234.53.22
//                - timeout: 50
//                + timeout: 20
//                + verbose: true
//                }""";
//        String actual = Differ.generate_json(yamlString1, yamlString2);
//        assertEquals(expected, actual);
//    }

    @Test
    public void testReadFileWithClassLoader() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("file1.json").getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testWithJsonFiles() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file1 = new File(classLoader.getResource("file1.json").getFile());
        File file2 = new File(classLoader.getResource("file2.json").getFile());
        String expected = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        String actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testFileTypeDetection() throws IOException {
//        BasicConfigurator.configure();

        ClassLoader classLoader = this.getClass().getClassLoader();
        Tika defaultTika = new Tika();
        File file1 = new File(classLoader.getResource("file1.json").getFile());
        File file2 = new File(classLoader.getResource("file2.json").getFile());
        File file3 = new File(classLoader.getResource("file1.yml").getFile());
        File file4 = new File(classLoader.getResource("file2.yml").getFile());

        String jsonType = "application/json";
        String yamlType = "text/x-yaml";

        assertEquals(jsonType, defaultTika.detect(file1));
        assertEquals(jsonType, defaultTika.detect(file2));
        assertEquals(yamlType, defaultTika.detect(file3));
        assertEquals(yamlType, defaultTika.detect(file4));

        System.out.println(defaultTika.detect(file1));
        System.out.println(defaultTika.detect(file2));
        System.out.println(defaultTika.detect(file3));
        System.out.println(defaultTika.detect(file4));
    }


    @Test
    public void testYamlFileToMap() throws IOException, NullPointerException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file1 = new File(classLoader.getResource("file1.yml").getFile());
        Map<String, Object> map1 = parseYAMLfileToMap(file1);
        System.out.println(map1.toString());
    }


}
