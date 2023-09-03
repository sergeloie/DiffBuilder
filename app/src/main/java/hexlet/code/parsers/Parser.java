package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

import static hexlet.code.parsers.ParserJSON.parseJSONstringToMap;
import static hexlet.code.parsers.ParserYAML.parseYAMLstringToMap;

public class Parser {

    public static Map<String, Object> parse(String content, String parseType) throws JsonProcessingException {
        return switch (parseType) {
            case ("json") -> parseJSONstringToMap(content);
            case ("yml") -> parseYAMLstringToMap(content);
            default -> throw new RuntimeException("Undefined type - " + parseType);
        };

    }
}
