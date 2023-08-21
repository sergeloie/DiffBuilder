package hexlet.code;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static hexlet.code.ParserJSON.parseJSONfileToMap;
import static hexlet.code.ParserYAML.parseYAMLfileToMap;

public class Parser {

    /**
     * @param map1 Map<String, Object> for extraction keySet
     * @param map2 Map<String, Object> for extraction keySet
     * @return Sorted List<String> of unique keys
     */
    public static List<String> getListOfUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {

        return Stream.concat(
                map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
    }

    public static List<String> getListOfUniqueStringKeys(Map<String, String> map1, Map<String, String> map2) {

        return Stream.concat(
                        map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
    }

    public static String isMapsContainKey(Map<String, Object> map1, Map<String, Object> map2, String key) {

        if (map1.containsKey(key) && !map2.containsKey(key)) {
            return "MAP1";
        }
        if (!map1.containsKey(key) && map2.containsKey(key)) {
            return "MAP2";
        }
        return "BOTH MAPS";
    }

    public static String isValuesEqual(Map<String, Object> map1, Map<String, Object> map2, String key) {

        if (map1.get(key).equals(map2.get(key))) {
            return "EQUAL";
        }
        return "UNEQUAL";
    }

    public static Map<String, Object> parseAnyFileToMap(File anyFile) throws IOException {
        String fileType = getFileType(anyFile);

        return switch (fileType) {
            case ("application/json") -> parseJSONfileToMap(anyFile);
            case ("text/x-yaml") -> parseYAMLfileToMap(anyFile);
            default -> Collections.<String, Object>emptyMap();
        };
    }


    public static String getFileType(File file) throws IOException {

        Tika defaultTika = new Tika();
        return defaultTika.detect(file);
    }
}
