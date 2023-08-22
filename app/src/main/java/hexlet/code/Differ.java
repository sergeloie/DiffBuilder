package hexlet.code;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//import static hexlet.code.Parser.parseAnyFileToStringMap;


public class Differ {

//    public static String generate(File file1, File file2) throws IOException {
//
//        Map<String, String> stringMap1 = parseAnyFileToStringMap(file1);
//        Map<String, String> stringMap2 = parseAnyFileToStringMap(file2);
//        List<String> sortedList = getListOfUniqueStringKeys(stringMap1, stringMap2);
//        return buildDiffExtended(sortedList, stringMap1, stringMap2);
//
//    }

    public static String generate(File file1, File file2) throws IOException {

        Map<String, Object> map1 = Parser.parseAnyFileToMap(file1);
        Map<String, Object> map2 = Parser.parseAnyFileToMap(file2);
        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
        return Parser.buildDiffObject(sortedList, map1, map2);
    }

    public static String buildDiffExtended(List<String> list, Map<String, String> map1, Map<String, String> map2) {
        StringBuilder output = new StringBuilder();
        output.append("{\n");

        for (String key : list) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                output.append(String.format("- %s: %s\n", key, map1.get(key)));
                continue;
            }
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                output.append(String.format("+ %s: %s\n", key, map2.get(key)));
                continue;
            }
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (StringUtils.equals(map1.get(key), map2.get(key))) {
                    output.append(String.format("  %s: %s\n", key, map1.get(key)));
                } else {
                    output.append(String.format("- %s: %s\n", key, map1.get(key)));
                    output.append(String.format("+ %s: %s\n", key, map2.get(key)));
                }
            }
        }
        output.append("}");
        return output.toString();
    }

//    public static Map<String, String> buildCompareTree(List<String> list, Map<String, String> map1, Map<String, String> map2) {
//        Map<String, String> resultMap = new TreeMap<>();
//        for(String key: list) {
//
//        }
//    }
}
