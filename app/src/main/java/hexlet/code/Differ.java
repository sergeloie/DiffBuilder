package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Differ {

    public static String generate(String json1, String json2) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap1 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        Map<String, Object> jsonMap2 = objectMapper.readValue(json2, new TypeReference<>() {
        });

//        Set<String> controlSet = jsonMap1.keySet().stream().collect(Collectors.toSet());
//        controlSet.addAll(jsonMap2.keySet().stream().collect(Collectors.toSet()));
//        List<String> sortedList = new ArrayList<>(controlSet);
//        Collections.sort(sortedList);

        List<String> sortedList = Stream.concat(
                jsonMap1.keySet().stream(), jsonMap2.keySet().stream())
                .distinct()
                .sorted()
                .toList();

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
            if (map2.containsKey(key) && map2.containsKey(key)) {
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
