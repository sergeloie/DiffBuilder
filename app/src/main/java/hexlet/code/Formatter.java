package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static String buildFormattedString(String format, List<DifferBuilder> diffLIst) {
        return switch (format) {
            case ("plain") -> Plain.plain(diffLIst);
            default -> Stylish.stylish(diffLIst);
        };
    }
}
