package hexlet.code;

import hexlet.code.parsers.Parser;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DataSupplier {

    public static Map<String, Object> getData(String pathToFile) throws IOException {
        Path fullPath = Paths.get(pathToFile).toAbsolutePath();
        String content = Files.readString(fullPath);
        String extension = FilenameUtils.getExtension(String.valueOf(fullPath));
        return Parser.parse(content, extension);
    }
}
