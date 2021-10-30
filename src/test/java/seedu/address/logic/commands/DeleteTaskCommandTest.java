package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Task;

class DeleteTaskCommandTest {
    private static final Task testTask = new Task(new Label("test label"),
            new Date("2001-10-12"), new TaskTag("SO100"));

    //I followed the style of AddCommand test instead of DeleteCommand test since I thought using a modelStub
    //was more stylistically appropriate for testing.

    @Test
    public void execute_validIndexDeletion_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOnePerson modelStub = new ModelStubWithOnePerson();

        CommandResult commandResult = new DeleteTaskCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, testTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.listWithOneTask);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        ModelStubWithOnePerson modelStub = new ModelStubWithOnePerson();

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, (
        ) -> deleteTaskCommand.execute(modelStub));
    }


    private class ModelStubWithOnePerson extends ModelStub {
        private final ObservableList<Task> listWithOneTask = FXCollections.observableArrayList(testTask);

        @Override
        public void deleteTask(Task task, int index) {
            requireNonNull(task);
            listWithOneTask.remove(task);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return listWithOneTask;
        }
    }

    //pending more tests for filtered list(?)

}
