package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static hexlet.code.DifferBuilder.buildDiffList;
import static hexlet.code.Formatter.buildFormattedString;


public class Differ {

//    public static String generate(File file1, File file2, String formatOutput) throws IOException {
//
//        Map<String, Object> map1 = Parser.parseFileToMap(file1);
//        Map<String, Object> map2 = Parser.parseFileToMap(file2);
//        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
//        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, map1, map2);
//        return buildFormattedString(formatOutput, diffBuildList);
//    }

//    public static String generate(File file1, File file2) throws IOException {
//
//        Map<String, Object> map1 = Parser.parseFileToMap(file1);
//        Map<String, Object> map2 = Parser.parseFileToMap(file2);
//        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
//        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, map1, map2);
//        return buildFormattedString("stylish", diffBuildList);
//    }
//    public static String generate(Path filePath1, Path filePath2, String formatOutput) throws IOException {
//
//        Map<String, Object> map1 = Parser.parseFileToMap(filePath1.toFile());
//        Map<String, Object> map2 = Parser.parseFileToMap(filePath2.toFile());
//        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
//        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, map1, map2);
//        return buildFormattedString(formatOutput, diffBuildList);
//    }

    public static String generate(String filePath1, String filePath2, String formatOutput) throws IOException {

        Map<String, Object> map1 = Parser.parseFileToMap(Path.of(filePath1).toFile());
        Map<String, Object> map2 = Parser.parseFileToMap(Path.of(filePath2).toFile());
        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, map1, map2);
        return buildFormattedString(formatOutput, diffBuildList);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        Map<String, Object> map1 = Parser.parseFileToMap(Path.of(filePath1).toFile());
        Map<String, Object> map2 = Parser.parseFileToMap(Path.of(filePath2).toFile());
        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, map1, map2);
        return buildFormattedString("stylish", diffBuildList);
    }
}
