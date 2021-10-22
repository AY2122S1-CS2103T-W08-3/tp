package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.ModelStub;
import seedu.address.model.Date;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

class AddTaskCommandTest {

    @Test
    public void constructor_nullLabel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new Task(new Label("test label"),
                new Date("test date"), new TaskTag("SO100"));

        CommandResult commandResult = new AddTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasksAdded);
    }

    /**
     * A model stub for testing adding single task
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        private final ArrayList<Task> tasksAdded = new ArrayList<Task>();

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }
    }
}
