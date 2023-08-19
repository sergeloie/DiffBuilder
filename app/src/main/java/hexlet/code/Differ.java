package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.*;


public class Differ {

    public static String generate(String json1, String json2) throws JsonProcessingException {

        Map<String, Object> jsonMap1 = parseJSONstringToMap(json1);
        Map<String, Object> jsonMap2 = parseJSONstringToMap(json2);

        List<String> sortedList = getListOfUniqueKeys(jsonMap1, jsonMap2);

        return buildJsonDiff(sortedList, jsonMap1, jsonMap2);
    }

    public static String generateFromFiles(File file1, File file2) throws IOException {
        Map<String, Object> jsonMap1 = parseJSONfileToMap(file1);
        Map<String, Object> jsonMap2 = parseJSONfileToMap(file2);

        List<String> sortedList = getListOfUniqueKeys(jsonMap1, jsonMap2);

        return buildJsonDiff(sortedList, jsonMap1, jsonMap2);
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
}
