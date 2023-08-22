package hexlet.code;

import java.util.List;

public class Formatter {

    public static String stylish(List<DifferBuilder> diffLIst) {

        StringBuilder result = new StringBuilder("{\n");
        for (DifferBuilder element: diffLIst) {
            switch (element.getStatus()) {
                case UNCHANGED -> result.append("    ");
                case ADDED -> result.append("  + ");
                case DELETED -> result.append("  - ");
                default -> { }
            }
            result.append(element.getDiffKey());
            result.append(": ");
            result.append(element.getDiffValue());
            result.append("\n");
        }
        result.append("}");
        return result.toString();
    }

    public static String buildFormattedString(String formatType) {
        return stylish(diffLIst);
    }
}
