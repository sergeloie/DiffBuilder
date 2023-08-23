package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DifferBuilder {

    public enum Status {
        DELETED,
        ADDED,
        UNCHANGED,
        UPDATED
    }

    private Status status;
    private String diffKey;
    private Object diffCurrentValue;
    private Object diffPreviousValue;

    public DifferBuilder(Status status, String diffKey, Object diffCurrentValue, Object diffPreviousValue) {
        this.status = status;
        this.diffKey = diffKey;
        this.diffCurrentValue = diffCurrentValue;
        this.diffPreviousValue = diffPreviousValue;
    }

    public DifferBuilder(Status status, String diffKey, Object diffCurrentValue) {
        this.status = status;
        this.diffKey = diffKey;
        this.diffCurrentValue = diffCurrentValue;

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

    public Object getDiffCurrentValue() {
        return diffCurrentValue;
    }

    public void setDiffCurrentValue(Object diffCurrentValue) {
        this.diffCurrentValue = diffCurrentValue;
    }

    public Object getDiffPreviousValue() {
        return diffPreviousValue;
    }

    public void setDiffPreviousValue(Object diffPreviousValue) {
        this.diffPreviousValue = diffPreviousValue;
    }

    public static List<DifferBuilder> buildDiffList(List<String> list,
                                                    Map<String, Object> map1,
                                                    Map<String, Object> map2) {

        List<DifferBuilder> result = new ArrayList<>();
        for (String key: list) {
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
                    result.add(new DifferBuilder(Status.UPDATED, key, map1.get(key), map2.get(key)));
//                    result.add(new DifferBuilder(Status.DELETED, key, map1.get(key)));
//                    result.add(new DifferBuilder(Status.ADDED, key, map2.get(key)));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DifferBuilder{");
        sb.append("status=").append(status);
        sb.append(", diffKey='").append(diffKey).append('\'');
        sb.append(", diffCurrentValue=").append(diffCurrentValue);
        sb.append(", diffPreviousValue=").append(diffPreviousValue);
        sb.append('}');
        return sb.toString();
    }
}
