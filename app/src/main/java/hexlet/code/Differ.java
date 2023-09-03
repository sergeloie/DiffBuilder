package hexlet.code;

import hexlet.code.parsers.Parser;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.DifferBuilder.buildDiffList;
import static hexlet.code.formatters.Formatter.buildFormattedString;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatOutput) throws IOException {

        Path fullPath1 = Paths.get(filePath1).toAbsolutePath();
        Path fullPath2 = Paths.get(filePath2).toAbsolutePath();
        String fileContent1 = Files.readString(fullPath1);
        String fileContent2 = Files.readString(fullPath2);
        String fileExt1 = FilenameUtils.getExtension(String.valueOf(fullPath1));
        String fileExt2 = FilenameUtils.getExtension(String.valueOf(fullPath2));

        Map<String, Object> data1 = Parser.parse(fileContent1, fileExt1);
        Map<String, Object> data2 = Parser.parse(fileContent2, fileExt2);
        List<String> sortedList = DifferBuilder.getListOfUniqueKeys(data1, data2);
        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, data1, data2);
        return buildFormattedString(formatOutput, diffBuildList);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        return generate(filePath1, filePath2, "stylish");
    }
}
