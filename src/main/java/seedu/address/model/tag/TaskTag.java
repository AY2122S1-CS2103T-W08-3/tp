package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TaskTag {

    public static final String MESSAGE_CONSTRAINTS =
            "Task tags names should either be 'General' or "
                    + "have prefix 'SO' followed by at least 1 digit.";
    public static final String VALIDATION_REGEX = "SO[\\d]+|General";

    public final String tagName;

    /**
     * Constructs a {@code TaskTag}.
     *
     * @param tagName A valid tag name.
     */
    public TaskTag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskTag // instanceof handles nulls
                && tagName.equals(((TaskTag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName;
    }

}
