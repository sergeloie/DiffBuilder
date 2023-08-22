package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DifferBuilder {

    private enum Status {
        DELETED,
        ADDED,
        UNCHANGED
    }

    private Status status;
    private String diffKey;
    private Object diffValue;

    public DifferBuilder(Status status, String diffKey, Object diffValue) {
        this.status = status;
        this.diffKey = diffKey;
        this.diffValue = diffValue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDiffKey() {
        return diffKey;
    }

    public void setDiffKey(String diffKey) {
        this.diffKey = diffKey;
    }

    public Object getDiffValue() {
        return diffValue;
    }

    public void setDiffValue(Object diffValue) {
        this.diffValue = diffValue;
    }

    public static List<DifferBuilder> buildDiffList(List<String> list, Map<String, Object> map1, Map<String, Object> map2) {

        List<DifferBuilder> result = new ArrayList<>();
        for(String key: list) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.add(new DifferBuilder(Status.DELETED, key, map1.get(key)));
                continue;
            }
            if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.add(new DifferBuilder(Status.ADDED, key, map2.get(key)));
                continue;
            }
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(map1.get(key), map2.get(key))) {
                    result.add(new DifferBuilder(Status.UNCHANGED, key, map1.get(key)));
                } else {
                    result.add(new DifferBuilder(Status.DELETED, key, map1.get(key)));
                    result.add(new DifferBuilder(Status.ADDED, key, map2.get(key)));
                }
            }
        }
        return result;
    }
}
