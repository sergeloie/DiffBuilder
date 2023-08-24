package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class ParserYAML {
    /**
     * @param yamlString
     * @return Map<String, Object> from parsed yaml String
     * @throws JsonProcessingException
     */
    public static Map<String, Object> parseYAMLstringToMap(String yamlString) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yamlString, new TypeReference<>() {
        });
    }
}
