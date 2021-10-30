package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

class MarkTaskCommandTest {
    private static final String TEST_DATE = "04 July 2020";

    private static final Task testTask = new Task(new Label("test label"),
            new Date(TEST_DATE), new TaskTag("SO100"));

    //I followed the style of AddCommand test instead of DeleteCommand test since I thought using a modelStub
    //was more stylistically appropriate for testing.

    @Test
    public void execute_validIndexMarkTask_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOnePerson modelStub = new ModelStubWithOnePerson();
        assertFalse(testTask.getIsDone());

        Task secondTestTask = new Task(new Label("test label"),
                new Date(TEST_DATE), new TaskTag("SO100"));
        secondTestTask.setIsDone(true);

        CommandResult commandResult = new MarkTaskCommand(targetIndex).execute(modelStub);
        assertTrue(testTask.getIsDone());
        assertEquals(String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, testTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);
        ModelStubWithOnePerson modelStub = new ModelStubWithOnePerson();

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, (
        ) -> markTaskCommand.execute(modelStub));
    }

    private class ModelStubWithOnePerson extends ModelStub {
        private final ObservableList<Task> listWithOneTask = FXCollections.observableArrayList(testTask);

        @Override
        public void markTask(Task task, int index) {
            listWithOneTask.get(0).setIsDone(true);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return listWithOneTask;
        }
    }
}
