package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * builds a list of differences between 2 files.
 */
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

    /**
     * @param status
     * @param diffKey
     * @param diffCurrentValue
     * @param diffPreviousValue
     */
    public DifferBuilder(Status status, String diffKey, Object diffCurrentValue, Object diffPreviousValue) {
        this.status = status;
        this.diffKey = diffKey;
        this.diffCurrentValue = diffCurrentValue;
        this.diffPreviousValue = diffPreviousValue;
    }

    /**
     * @param status
     * @param diffKey
     * @param diffCurrentValue
     */
    public DifferBuilder(Status status, String diffKey, Object diffCurrentValue) {
        this.status = status;
        this.diffKey = diffKey;
        this.diffCurrentValue = diffCurrentValue;

    }

    /**
     * @return Status [DELETED, ADDED, UNCHANGED, UPDATED].
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return Key of comparable node
     */
    public String getDiffKey() {
        return diffKey;
    }

    /**
     * @param diffKey
     */
    public void setDiffKey(String diffKey) {
        this.diffKey = diffKey;
    }

    /**
     * @return Current value of comparable node
     */
    public Object getDiffCurrentValue() {
        return diffCurrentValue;
    }

    /**
     * @param diffCurrentValue
     */
    public void setDiffCurrentValue(Object diffCurrentValue) {
        this.diffCurrentValue = diffCurrentValue;
    }

    /**
     * @return Previous value of comparable node
     */
    public Object getDiffPreviousValue() {
        return diffPreviousValue;
    }

    /**
     * @param diffPreviousValue
     */
    public void setDiffPreviousValue(Object diffPreviousValue) {
        this.diffPreviousValue = diffPreviousValue;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DifferBuilder{");
        sb.append("status=").append(status);
        sb.append(", diffKey='").append(diffKey).append('\'');
        sb.append(", diffCurrentValue=").append(diffCurrentValue);
        sb.append(", diffPreviousValue=").append(diffPreviousValue);
        sb.append('}');
        return sb.toString();
    }

    public static List<String> getListOfUniqueKeys(Map<String, Object> map1, Map<String, Object> map2) {

        return Stream.concat(
                        map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
    }
}
