package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Date;
import seedu.address.model.tag.TaskTag;

/**
 * Represents a Task in the SalesNote.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task {
    private Label label;
    private Date date;
    private TaskTag taskTag;
    private boolean isDone;

    /**
     * Every field must be present and not null.
     */
    public Task(Label label, Date date, TaskTag taskTag) {
        CollectionUtil.requireAllNonNull(label, date, taskTag);
        this.label = label;
        this.date = date;
        this.taskTag = taskTag;
        this.isDone = false;
    }

    /**
     * Method used to mark the task as done.
     *
     * @param isDone indicates whether the task is marked as done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Getter for boolean isDone.
     *
     * @return the isDone of the Task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Getter for label.
     *
     * @return the label of the task.
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Getter for date.
     *
     * @return A String representing the date associated with the Task.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the task tag of the task
     */
    public TaskTag getTaskTag() {
        return taskTag;
    }

    /**
     * Override of toString to provide a user-friendly String representation of Task objects.
     *
     * @return A string representing the Task meant to be printed for the user.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (isDone) {
            builder.append("[X] ");
        } else {
            builder.append("[ ] ");
        }
        builder.append(getLabel())
                .append("; Date: ")
                .append(getDate())
                .append("; TaskTag: ")
                .append(getTaskTag());

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getLabel().equals(getLabel())
                && otherTask.getDate().equals(getDate())
                && otherTask.getTaskTag().equals(getTaskTag());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(label, date, taskTag);
    }
}
