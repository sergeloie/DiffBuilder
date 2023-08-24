package hexlet.code;

import static hexlet.code.Differ.generate;
import static hexlet.code.DifferBuilder.buildDiffList;
import static hexlet.code.Formatter.buildFormattedString;
import static hexlet.code.Parser.buildDiffObject;
import static hexlet.code.Parser.getListOfUniqueKeys;
import static hexlet.code.Parser.parseFileToMap;
import static hexlet.code.formatters.Plain.isComplexObject;
import static hexlet.code.formatters.Plain.isStringObject;
import static hexlet.code.formatters.Stylish.diffToStylish;
import static hexlet.code.serializers.SerializeJSON.diffToJSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TestDiffer {

    ClassLoader classLoader = this.getClass().getClassLoader();
    File file11 = new File(Objects.requireNonNull(classLoader.getResource("file11.json")).getFile());
    File file12 = new File(Objects.requireNonNull(classLoader.getResource("file12.json")).getFile());

    File file21 = new File(Objects.requireNonNull(classLoader.getResource("file21.yml")).getFile());
    File file22 = new File(Objects.requireNonNull(classLoader.getResource("file22.yml")).getFile());

    Map<String, Object> map11 = parseFileToMap(file11);
    Map<String, Object> map12 = parseFileToMap(file12);
    Map<String, Object> map21 = parseFileToMap(file21);
    Map<String, Object> map22 = parseFileToMap(file22);

    String expectedStylishLong = """
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

    String expectedPlainLong = """
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

    public TestDiffer() throws IOException {
    }


    @Test
    public void dummyTest() {
        assertTrue(true);
    }

    @Test
    public void testReadFileWithClassLoader() {
        assertTrue(file11.exists());
    }

    @Test
    public void testWithJsonFiles() throws IOException {
        String actual = generate(file11.toPath(), file12.toPath(), "stylish");
        assertEquals(expectedStylishLong, actual);
    }

    @Test
    public void testFileTypeDetection() throws IOException {
        Tika defaultTika = new Tika();

        String jsonType = "application/json";
        String yamlType = "text/x-yaml";

        assertEquals(jsonType, defaultTika.detect(file11));
        assertEquals(jsonType, defaultTika.detect(file12));
        assertEquals(yamlType, defaultTika.detect(file21));
        assertEquals(yamlType, defaultTika.detect(file22));
    }


    @Test
    public void testBigJsonStringToMap() {

        List<String> sortedList = getListOfUniqueKeys(map11, map12);
        System.out.println(buildDiffObject(sortedList, map11, map12));

    }

    @Test
    public void testGenerateString() throws IOException {

        assertEquals(expectedStylishLong, generate(file11.toPath(), file12.toPath(), "stylish"));
        assertEquals(expectedPlainLong, generate(file11.toPath(), file12.toPath(), "plain"));
    }


    @Test
    public void testBuildDiffList() {

        List<String> sortedList = getListOfUniqueKeys(map11, map12);
        List<DifferBuilder> list1 = buildDiffList(sortedList, map11, map12);
        assertEquals(expectedStylishLong, diffToStylish(list1));
    }

    @Test
    public void testComplicity() throws IOException {
        Map<String, Object> testMap1 = parseFileToMap(file12);
        testMap1.keySet()
                .forEach(x -> {
                    System.out.println(x + " " + testMap1.get(x) + " "
                            + (testMap1.get(x) != null ? testMap1.get(x).getClass() : null)
                            + " isComplex=" + isComplexObject(testMap1.get(x))
                            + " isString =" + isStringObject(testMap1.get(x)));
                });
    }

    @Test
    public void testParseFileToMap() {
        List<String> sortedList = getListOfUniqueKeys(map11, map12);
        List<DifferBuilder> list1 = buildDiffList(sortedList, map11, map12);
        assertEquals(expectedStylishLong, diffToStylish(list1));
    }

    @Test
    public void testToJSON() throws IOException {
        List<String> sortedList = getListOfUniqueKeys(map11, map12);
        List<DifferBuilder> list1 = buildDiffList(sortedList, map11, map12);
        System.out.println(diffToJSON(list1));
        System.out.println(buildFormattedString("json", list1));
    }
}
