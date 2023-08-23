package hexlet.code;

import java.util.List;

public class Formatter {

    public static String stylish(List<DifferBuilder> diffLIst) {

        StringBuilder result = new StringBuilder("{\n");
        for (DifferBuilder element: diffLIst) {
            String eol = element.getDiffKey() + ": " + element.getDiffCurrentValue() + "\n";
            switch (element.getStatus()) {
                case UNCHANGED -> result.append("    ").append(eol);
                case ADDED -> result.append("  + ").append(eol);
                case DELETED -> result.append("  - ").append(eol);
                case UPDATED -> {
                    result.append("  - ").append(eol);
                    result.append("  + ");
                    result.append(element.getDiffKey());
                    result.append(": ");
                    result.append(element.getDiffPreviousValue());
                    result.append("\n");
                }
                default -> { }
            }
//            result.append(element.getDiffKey());
//            result.append(": ");
//            result.append(element.getDiffValue());
//            result.append("\n");
        }
        result.append("}");
        return result.toString();
    }

    public static String buildFormattedString(String format, List<DifferBuilder> diffLIst) {
        return switch (format) {
            default -> stylish(diffLIst);
        };
    }
}
