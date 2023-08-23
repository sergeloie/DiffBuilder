package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.DifferBuilder.buildDiffList;
import static hexlet.code.Formatter.buildFormattedString;


public class Differ {

    public static String generate(String formatOutput, File file1, File file2) throws IOException {

        Map<String, Object> map1 = Parser.parseAnyFileToMap(file1);
        Map<String, Object> map2 = Parser.parseAnyFileToMap(file2);
        List<String> sortedList = Parser.getListOfUniqueKeys(map1, map2);
        List<DifferBuilder> diffBuildList = buildDiffList(sortedList, map1, map2);
        return buildFormattedString(formatOutput, diffBuildList);
//        return stylish(diffBuildList);
        //        return Parser.buildDiffObject(sortedList, map1, map2);
    }

}
