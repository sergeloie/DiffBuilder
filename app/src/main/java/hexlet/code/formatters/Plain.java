package hexlet.code.formatters;

import hexlet.code.LineDifference;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<LineDifference> diffList) {
        String updated = "Property '%s' was updated. From %s to %s\n";
        String removed = "Property '%s' was removed\n";
        String added = "Property '%s' was added with value: %s\n";
        StringBuilder result = new StringBuilder();
        for (LineDifference element : diffList) {
            switch (element.getStatus()) {
                case DELETED -> result.append(String.format(removed, element.getDiffKey()));
                case ADDED -> result.append(String.format(added,
                        element.getDiffKey(),
                        stringify(element.getDiffCurrentValue())));

                case UPDATED -> result.append(String.format(updated,
                        element.getDiffKey(),
                        stringify(element.getDiffPreviousValue()),
                        stringify(element.getDiffCurrentValue())));

                case UNCHANGED -> {
                }
                default -> throw new RuntimeException("Unexpected value: " + element.getStatus());
            }
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

    public static boolean isComplexObject(Object object) {
        return (object instanceof Map<?, ?> || object instanceof List<?>);
    }

    public static boolean isStringObject(Object object) {
        return object instanceof String;
    }

    public static String stringify(Object object) {

        if (isComplexObject(object)) {
            return "[complex value]";
        }
        if (isStringObject(object)) {
            return String.format("'%s'", object);
        }
        return String.valueOf(object);

    }
}
