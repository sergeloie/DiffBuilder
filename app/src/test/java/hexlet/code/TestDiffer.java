package hexlet.code;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestDiffer {

    private ClassLoader classLoader = this.getClass().getClassLoader();
//    private File file11 = new File(Objects.requireNonNull(classLoader.getResource("file11.json")).getFile());
//    private Path pathFile11 = Paths.get(Objects.requireNonNull(classLoader.getResource("file11.json")).getFile());

    private String stringPath11 = "/home/superadmin/java-project-71/app/build/install/app/bin/file11.json";
    private String stringPath12 = "/home/superadmin/java-project-71/app/build/install/app/bin/file12.json";
    private String stringPath21 = "/home/superadmin/java-project-71/app/build/install/app/bin/file21.yml";
    private String stringPath22 = "/home/superadmin/java-project-71/app/build/install/app/bin/file22.yml";

    private String expectedStylishLong = """
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

    private String expectedPlainLong = """
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

    @Test
    public void mainTest() throws IOException {
        String actual = generate(stringPath11, stringPath12);
        String actualPlain = generate(stringPath21, stringPath22, "plain");
        assertEquals(actual, expectedStylishLong);
        assertEquals(actualPlain, expectedPlainLong);
    }
}
