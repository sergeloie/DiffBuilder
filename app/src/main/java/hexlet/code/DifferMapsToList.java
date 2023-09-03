package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static hexlet.code.DifferBuilder.getListOfUniqueKeys;

public class DifferMapsToList {
    /**
     * @param map1
     * @param map2
     * @return List with full information about nodes
     */
    public static List<DifferBuilder> buildDiffList(Map<String, Object> map1,
                                                    Map<String, Object> map2) {

        List<String> list = getListOfUniqueKeys(map1, map2);
        List<DifferBuilder> result = new ArrayList<>();
        for (String key: list) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.add(new DifferBuilder(DifferBuilder.Status.DELETED, key, map1.get(key)));
                continue;
            }
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.add(new DifferBuilder(DifferBuilder.Status.ADDED, key, map2.get(key)));
                continue;
            }
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    result.add(new DifferBuilder(DifferBuilder.Status.UNCHANGED, key, map1.get(key)));
                } else {
                    result.add(new DifferBuilder(DifferBuilder.Status.UPDATED, key, map2.get(key), map1.get(key)));
                }
            }
        }
        return result;
    }
}
