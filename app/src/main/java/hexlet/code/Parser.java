package hexlet.code;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;


import static hexlet.code.ParserJSON.parseJSONfileToMap;
import static hexlet.code.ParserYAML.parseYAMLfileToMap;

public class Parser {


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


    public static String getFileType(File file) throws IOException {

        Tika defaultTika = new Tika();
        return defaultTika.detect(file);
    }

    public static String buildDiffObject(List<String> list, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder output = new StringBuilder();
        output.append("{\n");

        for (String key : list) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                output.append(String.format("- %s: %s\n", key, map1.get(key)));
                continue;
            }
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                output.append(String.format("+ %s: %s\n", key, map2.get(key)));
                continue;
            }
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    output.append(String.format("  %s: %s\n", key, map1.get(key)));
                } else {
                    output.append(String.format("- %s: %s\n", key, map1.get(key)));
                    output.append(String.format("+ %s: %s\n", key, map2.get(key)));
                }
            }
        }
        output.append("}");
        return output.toString();
    }

    public static List<String> getListOfUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {

        return Stream.concat(
                        map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
    }

    public static Map<String, Object> parseAnyFileToMap(File anyFile) throws IOException {

        String fileType = getFileType(anyFile);

        return switch (fileType) {
            case ("application/json") -> parseJSONfileToMap(anyFile);
            case ("text/x-yaml") -> parseYAMLfileToMap(anyFile);
            default -> Collections.<String, Object>emptyMap();
        };
    }
}
