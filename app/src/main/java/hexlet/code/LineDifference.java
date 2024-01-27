package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * builds a list of differences between 2 files.
 */
@Getter
@Setter
@AllArgsConstructor
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

    public LineDifference(Status status, String diffKey, Object diffCurrentValue) {
        this.status = status;
        this.diffKey = diffKey;
        this.diffCurrentValue = diffCurrentValue;

    }
}
