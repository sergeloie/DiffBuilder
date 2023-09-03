package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.DifferBuilder;


import java.util.List;

public class Formatter {

    public static String buildFormattedString(String format, List<DifferBuilder> diffLIst)
            throws JsonProcessingException {
        return switch (format) {
            case ("plain") -> Plain.diffToPlain(diffLIst);
            case ("json") -> Json.diffToJSON(diffLIst);
            default -> Stylish.diffToStylish(diffLIst);
        };
    }
}
