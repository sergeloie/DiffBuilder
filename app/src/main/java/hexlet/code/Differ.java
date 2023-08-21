package hexlet.code;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.getListOfUniqueKeys;
import static hexlet.code.Parser.parseAnyFileToMap;


public class Differ {

    public static String generate(File file1, File file2) throws IOException {

        Map<String, Object> anyMap1 = parseAnyFileToMap(file1);
        Map<String, Object> anyMap2 = parseAnyFileToMap(file2);
        List<String> sortedList = getListOfUniqueKeys(anyMap1, anyMap2);
        return buildJsonDiff(sortedList, anyMap1, anyMap2);
    }

    public static String buildJsonDiff(List<String> list, Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder stylishOutput = new StringBuilder();
        stylishOutput.append("{\n");

        for (String key : list) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                stylishOutput.append(String.format("- %s: %s\n", key, map1.get(key)));
                continue;
            }
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                stylishOutput.append(String.format("+ %s: %s\n", key, map2.get(key)));
                continue;
            }
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    stylishOutput.append(String.format("  %s: %s\n", key, map1.get(key)));
                } else {
                    stylishOutput.append(String.format("- %s: %s\n", key, map1.get(key)));
                    stylishOutput.append(String.format("+ %s: %s\n", key, map2.get(key)));
                }
            }
        }
        stylishOutput.append("}");
        return stylishOutput.toString();
    }

    public static String buildDiffExtended(List<String> list, Map<String, String> map1, Map<String, String> map2) {
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
                if (StringUtils.equals(map1.get(key), map2.get(key))) {
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
}
