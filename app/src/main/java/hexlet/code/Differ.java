package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String json1, String json2) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap1 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        Map<String, Object> jsonMap2 = objectMapper.readValue(json2, new TypeReference<>() {
        });

        Set<String> controlSet = jsonMap1.keySet().stream().collect(Collectors.toSet());
        controlSet.addAll(jsonMap2.keySet().stream().collect(Collectors.toSet()));
        List<String> sortedList = new ArrayList<>(controlSet);
        Collections.sort(sortedList);

        StringBuilder stylishOutput = new StringBuilder();
        stylishOutput.append("{\n");

        for (String key : sortedList) {
            if (jsonMap1.containsKey(key) && !jsonMap2.containsKey(key)) {
                stylishOutput.append("- ");
                stylishOutput.append(key);
                stylishOutput.append(": ");
                stylishOutput.append(jsonMap1.get(key));
                stylishOutput.append("\n");
                continue;
            }
            if (!jsonMap1.containsKey(key) && jsonMap2.containsKey(key)) {
                stylishOutput.append("+ ");
                stylishOutput.append(key);
                stylishOutput.append(": ");
                stylishOutput.append(jsonMap2.get(key));
                stylishOutput.append("\n");
                continue;
            }
            if (jsonMap2.containsKey(key) && jsonMap2.containsKey(key)) {
                if (jsonMap1.get(key).equals(jsonMap2.get(key))) {
                    stylishOutput.append("  ");
                    stylishOutput.append(key);
                    stylishOutput.append(": ");
                    stylishOutput.append(jsonMap1.get(key));
                    stylishOutput.append("\n");
                } else {
                    stylishOutput.append("- ");
                    stylishOutput.append(key);
                    stylishOutput.append(": ");
                    stylishOutput.append(jsonMap1.get(key));
                    stylishOutput.append("\n");
                    stylishOutput.append("+ ");
                    stylishOutput.append(key);
                    stylishOutput.append(": ");
                    stylishOutput.append(jsonMap2.get(key));
                    stylishOutput.append("\n");
                }
            }
        }
        stylishOutput.append("}");

        return stylishOutput.toString();
    }
}
