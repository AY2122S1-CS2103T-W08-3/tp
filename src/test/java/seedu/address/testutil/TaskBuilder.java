package seedu.address.testutil;

import seedu.address.model.Date;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_LABEL = "Get new cloth";
    public static final String DEFAULT_DATE = "20th September 2021";
    public static final String DEFAULT_TASKTAG = "SO007";
    public static final boolean DEFAULT_IS_DONE = false;


    private Label label;
    private Date date;
    private TaskTag taskTag;
    private boolean isDone;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        label = new Label(DEFAULT_LABEL);
        date = new Date(DEFAULT_DATE);
        taskTag = new TaskTag(DEFAULT_TASKTAG);
        isDone = DEFAULT_IS_DONE;

    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        label = taskToCopy.getLabel();
        date = taskToCopy.getDate();
        taskTag = taskToCopy.getTaskTag();
        isDone = taskToCopy.getIsDone();
    }

    /**
     * Sets the {@code Label} of the {@code Task} that we are building.
     */
    public TaskBuilder withLabel(String label) {
        this.label = new Label(label);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code taskTag} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskTag(String taskTag) {
        this.taskTag = new TaskTag(taskTag);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * builds the Task.
     */
    public Task build() {
        Task task = new Task(label, date, taskTag);
        task.setIsDone(isDone);
        return task;
    }
}
