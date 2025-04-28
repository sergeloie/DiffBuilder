package hexlet.code;

import java.util.*;
import java.util.stream.Stream;

public class DiffBuilder {
    /**
     * @param map1
     * @param map2
     * @return List with full information about nodes
     */
    public static List<LineDifference> buildDiffList(Map<String, Object> map1,
                                                     Map<String, Object> map2) {

        List<String> list = getListOfUniqueKeys(map1, map2);
        List<LineDifference> result = new ArrayList<>();
        for (String key: list) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.add(new LineDifference(LineDifference.Status.DELETED, key, map1.get(key)));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.add(new LineDifference(LineDifference.Status.ADDED, key, map2.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    result.add(new LineDifference(LineDifference.Status.UNCHANGED, key, map1.get(key)));
                } else {
                    result.add(new LineDifference(LineDifference.Status.UPDATED, key, map2.get(key), map1.get(key)));
                }
            }
        }
        return result;
    }

    public static List<String> getListOfUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {

        return Stream.concat(
                        map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
    }

    public static List<String> getDeletedKeys(Set<String> set1, Set<String> set2) {
        return set1.stream().filter(str -> !set2.contains(str)).toList();
    }
}
