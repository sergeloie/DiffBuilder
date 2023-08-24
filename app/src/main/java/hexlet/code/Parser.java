package hexlet.code;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static hexlet.code.parsers.ParserJSON.parseJSONstringToMap;
import static hexlet.code.parsers.ParserYAML.parseYAMLstringToMap;

public class Parser {


    public static String getFileType(File file) throws IOException {

        Tika defaultTika = new Tika();
        return defaultTika.detect(file);
    }

    public static List<String> getListOfUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {

        return Stream.concat(
                        map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
    }

    public static Map<String, Object> parseFileToMap(File file) throws IOException {

        String fileType = getFileType(file);

        Path path = Paths.get(file.getAbsolutePath());
        String contentOfFile = Files.readString(path);
        return switch (fileType) {
            case ("application/json") -> parseJSONstringToMap(contentOfFile);
            case ("text/x-yaml") -> parseYAMLstringToMap(contentOfFile);
            default -> Collections.emptyMap();
        };
    }
}
