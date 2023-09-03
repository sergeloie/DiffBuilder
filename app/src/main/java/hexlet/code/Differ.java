package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static hexlet.code.DataSupplier.getData;
import static hexlet.code.DifferMapsToList.buildDiffList;
import static hexlet.code.formatters.Formatter.buildFormattedString;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatOutput) throws IOException {

        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);
        List<DifferBuilder> diffBuildList = buildDiffList(data1, data2);
        return buildFormattedString(formatOutput, diffBuildList);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        return generate(filePath1, filePath2, "stylish");
    }
}
