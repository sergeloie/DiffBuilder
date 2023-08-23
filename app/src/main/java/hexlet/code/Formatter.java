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
        }
        result.append("}");
        return result.toString();
    }

    public static String plain(List<DifferBuilder> diffLIst) {
        String updated = "Property '%s' was updated. From %s to %s\n";
        String removed = "Property '%s' was removed\n";
        String added = "Property '%s' was added with value: %s\n";
        StringBuilder result = new StringBuilder();
        for (DifferBuilder element: diffLIst) {
            result.append("Property '").append(element.getDiffKey()).append(" ' ");
            switch (element.getStatus()) {
                case DELETED -> result.append("was removed\n");
                case ADDED -> {
                    result.append("was added with value ");
                    result.append(element.getDiffCurrentValue().getClass().isPrimitive() ? element.getDiffCurrentValue() : "[complex value]");
                }
                case UPDATED -> {
                    result.append("was updated. From ");
                    result.append(element.getDiffPreviousValue()).append(" ");
                    result.append(element.getDiffCurrentValue()).append(" ");

//                    result.append(element.getDiffPreviousValue().getClass().isPrimitive() ? element.getDiffPreviousValue() : "[complex value] ");
//                    result.append(element.getDiffCurrentValue().getClass().isPrimitive() ? element.getDiffCurrentValue() : "[complex value]");
                }
                case UNCHANGED -> { }

                default -> throw new IllegalStateException("Unexpected value: " + element.getStatus());
            }
            result.append("\n");

        }
        return result.toString();
    }

    public static String buildFormattedString(String format, List<DifferBuilder> diffLIst) {
        return switch (format) {
            case("plain") -> plain(diffLIst);
            default -> stylish(diffLIst);
        };
    }
}
