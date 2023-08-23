package hexlet.code;

import static hexlet.code.Differ.generate;
import static hexlet.code.DifferBuilder.buildDiffList;
import static hexlet.code.Formatter.stylish;
import static hexlet.code.ParserJSON.parseJSONfileToMap;
import static hexlet.code.ParserJSON.parseJSONstringToMap;
import static hexlet.code.Parser.buildDiffObject;
import static hexlet.code.Parser.getListOfUniqueKeys;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TestDiffer {



    @Test
    public void dummyTest() {
        assertTrue(true);
    }

    @Test
    public void testReadFileWithClassLoader() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("file1.json")).getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testWithJsonFiles() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file1 = new File(Objects.requireNonNull(classLoader.getResource("file1.json")).getFile());
        File file2 = new File(Objects.requireNonNull(classLoader.getResource("file2.json")).getFile());
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = generate("stylish", file1, file2);
        assertEquals(expected, actual);
    }

    @Test
    public void testFileTypeDetection() throws IOException {
//        BasicConfigurator.configure();

        ClassLoader classLoader = this.getClass().getClassLoader();
        Tika defaultTika = new Tika();
        File file1 = new File(Objects.requireNonNull(classLoader.getResource("file1.json")).getFile());
        File file2 = new File(Objects.requireNonNull(classLoader.getResource("file2.json")).getFile());
        File file3 = new File(Objects.requireNonNull(classLoader.getResource("file1.yml")).getFile());
        File file4 = new File(Objects.requireNonNull(classLoader.getResource("file2.yml")).getFile());

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
    public void testBigJsonStringToMap() throws JsonProcessingException {
        String bigJSON = """
                {
                  "setting1": "Some value",
                  "setting2": 200,
                  "setting3": true,
                  "key1": "value1",
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [2, 3, 4, 5],
                  "id": "null",
                  "default": null,
                  "checked": false,
                  "numbers3": [3, 4, 5],
                  "chars1": ["a", "b", "c"],
                  "chars2": ["d", "e", "f"]
                }""";

        String bigJSON2 = """
                {
                  "setting1": "Another value",
                  "setting2": 300,
                  "setting3": "none",
                  "key2": "value2",
                  "numbers1": [1, 2, 3, 4],
                  "numbers2": [22, 33, 44, 55],
                  "id": null,
                  "default": ["value1", "value2"],
                  "checked": true,
                  "numbers4": [4, 5, 6],
                  "chars1": ["a", "b", "c"],
                  "chars2": false,
                  "obj1": {
                    "nestedKey": "value",
                    "isNested": true
                  }
                }""";

        Map<String, Object> map1 = parseJSONstringToMap(bigJSON);
        Map<String, Object> map2 = parseJSONstringToMap(bigJSON2);
        List<String> sortedList = getListOfUniqueKeys(map1, map2);
        System.out.println(buildDiffObject(sortedList, map1, map2));

    }

    @Test
    public void testGenerateString() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file1 = new File(Objects.requireNonNull(classLoader.getResource("file1.json")).getFile());
        File file2 = new File(Objects.requireNonNull(classLoader.getResource("file2.json")).getFile());
        File file3 = new File(Objects.requireNonNull(classLoader.getResource("file1.yml")).getFile());
        File file4 = new File(Objects.requireNonNull(classLoader.getResource("file2.yml")).getFile());

        File file11 = new File(Objects.requireNonNull(classLoader.getResource("file11.json")).getFile());
        File file12 = new File(Objects.requireNonNull(classLoader.getResource("file12.json")).getFile());

        String expectedShort = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String expectedLong = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String expectedPlain = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

        assertEquals(expectedShort, generate("stylish", file1, file2));
        assertEquals(expectedShort, generate("stylish", file3, file4));
        assertEquals(expectedLong, generate("stylish", file11, file12));
        assertEquals(expectedPlain, generate("plain", file11, file12));
        System.out.println(parseJSONfileToMap(file1));
    }

    @Test
    public void testTree() {
        Map<String, String> testMap = new TreeMap<>();
        testMap.put("alfa", "first");
        testMap.put("charlie", "second");
        testMap.put("zulu", "third");
        testMap.put("bravo", "fourth");
        testMap.put("bravo", "fifth");
        System.out.println(testMap);
    }

    @Test
    public void testbuildDiffList() throws IOException {
        String expectedLong = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file1 = new File(Objects.requireNonNull(classLoader.getResource("file11.json")).getFile());
        File file2 = new File(Objects.requireNonNull(classLoader.getResource("file12.json")).getFile());
        Map<String, Object> anyMap1 = parseJSONfileToMap(file1);
        Map<String, Object> anyMap2 = parseJSONfileToMap(file2);
        List<String> sortedList = getListOfUniqueKeys(anyMap1, anyMap2);
        List<DifferBuilder> list1 = buildDiffList(sortedList, anyMap1, anyMap2);
        System.out.println(list1.toString());
        System.out.println(stylish(list1));
        assertEquals(expectedLong, stylish(list1));

    }

    @Test
    public void testFormattedString() {
        String str1 = "Number %d was %s\n";
        System.out.printf(str1, 10, "submitted");
        System.out.printf(str1, 156, "decrised");
        System.out.printf(str1, -15, "rejected");
    }
}
