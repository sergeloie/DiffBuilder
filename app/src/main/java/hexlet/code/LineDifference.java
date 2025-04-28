package hexlet.code;

import lombok.Getter;
import lombok.Setter;

/**
 * builds a list of differences between 2 files.
 */
@Getter
@Setter
public class LineDifference {

    public enum Status {
        DELETED,
        ADDED,
        UNCHANGED,
        UPDATED;
    }

    private Status status;
    private String key;
    private Object currentValue;
    private Object previousValue;

    public LineDifference(Status status,
                          String key,
                          Object currentValue) {
        this.status = status;
        this.key = key;
        this.currentValue = currentValue;

    }

    public LineDifference(Status status,
                          String key,
                          Object currentValue,
                          Object previousValue) {
        this.status = status;
        this.key = key;
        this.currentValue = currentValue;
        this.previousValue = previousValue;
    }
}
