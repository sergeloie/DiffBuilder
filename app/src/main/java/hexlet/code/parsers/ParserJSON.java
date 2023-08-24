package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ParserJSON {

    /**
     * @param jsonString String of JSON Objects
     * @return Map<String, Object> from parsed json String
     * @throws JsonProcessingException JsonProcessingException
     */
    public static Map<String, Object> parseJSONstringToMap(String jsonString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<>() {
        });
    }
}
