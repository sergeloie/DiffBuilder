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

    /**
     * @param yamlFile
     * @return Map<String, Object> from parsed yaml File
     * @throws IOException
     */
    public static Map<String, Object> parseYAMLfileToMap(File yamlFile) throws IOException {

        Path path = Paths.get(yamlFile.getAbsolutePath());
        String contentOfYAMLFile = Files.readString(path);
        return parseYAMLstringToMap(contentOfYAMLFile);
    }
}
