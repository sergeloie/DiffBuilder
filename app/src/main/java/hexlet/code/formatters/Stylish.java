package hexlet.code.formatters;

import hexlet.code.DifferBuilder;

import java.util.List;

public class Stylish {
    public static String diffToStylish(List<DifferBuilder> diffList) {

        StringBuilder result = new StringBuilder("{\n");
        String unchanged = "    %s: %s\n";
        String added = "  + %s: %s\n";
        String deleted = "  - %s: %s\n";
        String updated = deleted + added;
        for (DifferBuilder element: diffList) {
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
                default -> {
                    throw new RuntimeException("Unexpected value: " + element.getStatus());
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
