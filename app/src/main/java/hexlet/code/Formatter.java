package hexlet.code;

import java.util.List;

public class Formatter {

    public static String stylish(List<DifferBuilder> diffLIst) {

        StringBuilder result = new StringBuilder("{\n");
        String unchanged = "    %s: %s\n";
        String added = "  + %s: %s\n";
        String deleted = "  - %s: %s\n";
        String updated = deleted + added;
        for (DifferBuilder element: diffLIst) {
            switch (element.getStatus()) {
                case UNCHANGED -> result.append(String.format(unchanged,
                        element.getDiffKey(),
                        element.getDiffCurrentValue()));
                case ADDED -> result.append(String.format(added,
                        element.getDiffKey(),
                        element.getDiffCurrentValue()));
                case DELETED -> result.append(String.format(deleted,
                        element.getDiffKey(),
                        element.getDiffCurrentValue()));
                case UPDATED -> {
                    result.append(String.format(updated,
                            element.getDiffKey(),
                            element.getDiffPreviousValue(),
                            element.getDiffKey(),
                            element.getDiffCurrentValue()));
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
            switch (element.getStatus()) {
                case DELETED -> result.append(String.format(removed, element.getDiffKey()));
                case ADDED -> {
                    result.append(String.format(added,
                            element.getDiffKey(),
                            getElementPlainValue(element.getDiffCurrentValue())));
                }
                case UPDATED -> {
                    result.append(String.format(updated,
                            element.getDiffKey(),
                            getElementPlainValue(element.getDiffPreviousValue()),
                            getElementPlainValue(element.getDiffCurrentValue())));
                }
                case UNCHANGED -> { }
                default -> throw new IllegalStateException("Unexpected value: " + element.getStatus());
            }
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

    public static boolean isComplexObject(Object object) {
        return object == null ? false : !object.getClass().getPackageName().equals("java.lang");
    }

    public static boolean isStringObject(Object object) {
        return object == null ? false : object.getClass().getName().equals("java.lang.String");
    }

    public static String getElementPlainValue(Object object) {
        if (isComplexObject(object)) {
            return "[complex value]";
        }
        return object == null ? "null" : (isStringObject(object) ? String.format("'%s'", object) : object.toString());
    }

    public static String buildFormattedString(String format, List<DifferBuilder> diffLIst) {
        return switch (format) {
            case ("plain") -> plain(diffLIst);
            default -> stylish(diffLIst);
        };
    }
}
