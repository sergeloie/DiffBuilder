package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.LineDifference;


import java.util.List;

public class Formatter {

    public static String buildFormattedString(String format, List<LineDifference> diffList)
            throws JsonProcessingException {
        return switch (format) {
            case ("plain") -> Plain.format(diffList);
            case ("stylish") -> Stylish.format(diffList);
            case ("json") -> Json.format(diffList);
            default -> throw new RuntimeException("Unexpected format type: " + format);
        };
    }
}
