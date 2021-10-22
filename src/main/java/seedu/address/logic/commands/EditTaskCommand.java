package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Date;
import seedu.address.model.Model;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing task in the address book.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_LABEL + "LABEL] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TASK_TAG + "TASKTAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LABEL + "Order cloth "
            + PREFIX_DATE + "19th September 2021"
            + PREFIX_TASK_TAG + "SO2100";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";
    public static final String MESSAGE_NO_CHANGES_MADE = "You haven't made any changes to the task.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (taskToEdit.equals(editedTask)) {
            throw new CommandException(MESSAGE_NO_CHANGES_MADE);
        }

        if (!taskToEdit.equals(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Label updatedLabel = editTaskDescriptor.getLabel().orElse(taskToEdit.getLabel());
        Date updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        TaskTag updatedTaskTag = editTaskDescriptor.getTaskTag().orElse(taskToEdit.getTaskTag());

        return new Task(updatedLabel, updatedDate, updatedTaskTag);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Label label;
        private Date date;
        private TaskTag taskTag;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setDate(toCopy.date);
            setLabel(toCopy.label);
            setTaskTag(toCopy.taskTag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, label, taskTag);
        }

        public void setLabel(Label label) {
            this.label = label;
        }

        public Optional<Label> getLabel() {
            return Optional.ofNullable(label);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTaskTag(TaskTag taskTag) {
            this.taskTag = taskTag;
        }

        public Optional<TaskTag> getTaskTag() {
            return Optional.ofNullable(taskTag);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getLabel().equals(e.getLabel())
                    && getDate().equals(e.getDate())
                    && getTaskTag().equals(e.getTaskTag());
        }
    }
}
