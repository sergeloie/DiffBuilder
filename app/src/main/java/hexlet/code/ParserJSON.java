package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    public static Map<String, Object> parseJSONfileToMap(File jsonFile) throws IOException {

        Path path = Paths.get(jsonFile.getAbsolutePath());
        String contentOfJSONFile = Files.readString(path);
        return parseJSONstringToMap(contentOfJSONFile);
    }

}
