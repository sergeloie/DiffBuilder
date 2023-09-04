package hexlet.code;

/**
 * builds a list of differences between 2 files.
 */
public class LineDifference {



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
    public LineDifference(Status status, String diffKey, Object diffCurrentValue, Object diffPreviousValue) {
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
    public LineDifference(Status status, String diffKey, Object diffCurrentValue) {
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

}
