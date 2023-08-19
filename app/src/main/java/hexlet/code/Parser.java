package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Parser {

    /**
     * @param jsonString
     * @return Map<String, Object> from parsed json String
     * @throws JsonProcessingException
     */
    public static Map<String, Object> parseJSONstringToMap(String jsonString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<>() {
        });
    }

    /**
     * @param jsonFile
     * @return Map<String, Object> from parsed json File
     * @throws IOException
     */
    public static Map<String, Object> parseJSONfileToMap(File jsonFile) throws IOException {

        Path path = Paths.get(jsonFile.getAbsolutePath());
        String contentOfJSONFile = Files.readString(path);
        return parseJSONstringToMap(contentOfJSONFile);
    }

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

    public static Map<String, Object> parseYAMLstringToMap(String yamlString) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yamlString, new TypeReference<>() {
        });
    }

    public static Map<String, Object> parseYAMLfileToMap(File yamlFile) throws IOException {

        Path path = Paths.get(yamlFile.getAbsolutePath());
        String contentOfYAMLFile = Files.readString(path);
        return parseYAMLstringToMap(contentOfYAMLFile);
    }


}
