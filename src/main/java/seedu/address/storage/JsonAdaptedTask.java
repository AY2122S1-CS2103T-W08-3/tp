package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Date;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tasks's %s field is missing!";

    private final String label;
    private final String date;
    private final String taskTag;
    private final String isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("label") String label, @JsonProperty("date") String date,
                             @JsonProperty("taskTag") String taskTag, @JsonProperty("isDone") String isDone) {
        this.label = label;
        this.date = date;
        this.taskTag = taskTag;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        label = source.getLabel().toString();
        date = source.getDate().toString();
        taskTag = source.getTaskTag().toString();
        isDone = String.valueOf(source.getIsDone());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {

        if (label == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName()));
        }
        if (!Label.isValidLabel(label)) {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }
        final Label modelLabel = new Label(label);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(label)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (taskTag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTag.class.getSimpleName()));
        }
        if (!TaskTag.isValidTagName(taskTag)) {
            throw new IllegalValueException(TaskTag.MESSAGE_CONSTRAINTS);
        }
        final TaskTag modelTaskTag = new TaskTag(taskTag);

        Task newTask = new Task(modelLabel, modelDate, modelTaskTag);

        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Isdone"));
        }
        if (isDone.equals("true")) {
            newTask.setIsDone(true);
        } else if (isDone.equals("false")) {
            newTask.setIsDone(false);
        } else {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }

        return newTask;
    }
}
