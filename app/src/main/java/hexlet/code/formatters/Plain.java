package hexlet.code.formatters;

import hexlet.code.DifferBuilder;

import java.util.List;

public class Plain {
    public static String diffToPlain(List<DifferBuilder> diffList) {
        String updated = "Property '%s' was updated. From %s to %s\n";
        String removed = "Property '%s' was removed\n";
        String added = "Property '%s' was added with value: %s\n";
        StringBuilder result = new StringBuilder();
        for (DifferBuilder element: diffList) {
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

        return object == null ? "null"
                : isComplexObject(object) ? "[complex value]"
                : isStringObject(object) ? String.format("'%s'", object) : object.toString();
    }
}
