package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;


/**
 * Wraps all data at the task-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class TaskBook implements ReadOnlyTaskBook {

    private final TaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tasks = new TaskList();
    }

    public TaskBook() {}

    /**
     * Creates a TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskBook(ReadOnlyTaskBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the tasks list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskBook newData) {
        setTasks(newData.getTaskList());
    }

    //// task-level operations

    public void addTask(Task toAdd) {
        tasks.add(toAdd);
    }

    public void deleteTask(Task toDelete, int index) {
        tasks.remove(toDelete, index);
    }

    public void deleteTaskIf(Predicate<Task> pred) {
        tasks.removeIf(pred);
    }

    public void markDone(Task task, int index) {
        tasks.markDone(task, index);
    }

    public void setTask(Task target, Task editedTask) {
        tasks.setTask(target, editedTask);
    }

    public boolean hasTask(Task task) {
        return tasks.hasTask(task);
    }


    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBook // instanceof handles nulls
                && tasks.equals(((TaskBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }

}
