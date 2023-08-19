package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Build {

    public static String buildJsonDiff(List<String> list, Map<String, Object> map1, Map<String, Object> map2) {
        return list.stream()
                .map(key -> {
                    boolean keyPresentInBothMaps = map1.containsKey(key) && map2.containsKey(key);
                    boolean keyPresentOnlyInFirstMap = map1.containsKey(key) && !map2.containsKey(key);
                    boolean keyPresentOnlyInSecondMap = !map1.containsKey(key) && map2.containsKey(key);

                    String diff;
                    if (keyPresentOnlyInFirstMap) {
                        diff = String.format("- %s: %s", key, map1.get(key));
                    } else if (keyPresentOnlyInSecondMap) {
                        diff = String.format("+ %s: %s", key, map2.get(key));
                    } else if (keyPresentInBothMaps) {
                        if (map1.get(key).equals(map2.get(key))) {
                            diff = String.format("  %s: %s", key, map1.get(key));
                        } else {
                            diff = String.format("- %s: %s\n+ %s: %s", key, map1.get(key), key, map2.get(key));
                        }
                    } else {
                        diff = "";
                    }
                    return diff;
                })
                .filter(diff -> !diff.isEmpty())
                .collect(Collectors.joining("\n"));
    }
}
