package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.SerializeJSON;

import java.util.List;

public class Formatter {

    public static String buildFormattedString(String format, List<DifferBuilder> diffLIst)
            throws JsonProcessingException {
        return switch (format) {
            case ("plain") -> Plain.diffToPlain(diffLIst);
            case ("json") -> SerializeJSON.diffToJSON(diffLIst);
            default -> Stylish.diffToStylish(diffLIst);
        };
    }
}
