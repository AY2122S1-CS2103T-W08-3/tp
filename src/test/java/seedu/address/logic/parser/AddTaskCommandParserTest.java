package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_OCT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LABEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASKTAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_SEW;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASKTAG_DESC_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.TASKTAG_DESC_SEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_ORDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.ORDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.Date;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        Task expectedTask = new TaskBuilder(ORDER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LABEL_DESC_ORDER + DATE_DESC_SEPT + TASKTAG_DESC_ORDER,
                new AddTaskCommand(expectedTask));

        // multiple labels - last label accepted
        assertParseSuccess(parser, LABEL_DESC_SEW + LABEL_DESC_ORDER + DATE_DESC_SEPT + TASKTAG_DESC_ORDER,
                new AddTaskCommand(expectedTask));

        // multiple dates - last date accepted
        assertParseSuccess(parser, LABEL_DESC_ORDER + DATE_DESC_OCT + DATE_DESC_SEPT + TASKTAG_DESC_ORDER,
                new AddTaskCommand(expectedTask));

        // multiple task tags - last tag accepted
        assertParseSuccess(parser, LABEL_DESC_ORDER + DATE_DESC_SEPT + TASKTAG_DESC_SEW + TASKTAG_DESC_ORDER,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // right now, only validation check is that both fields are not empty after trimming spaces
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing label prefix
        assertParseFailure(parser, VALID_LABEL_ORDER + DATE_DESC_SEPT + TASKTAG_DESC_ORDER, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, LABEL_DESC_ORDER + VALID_DATE_SEPT + TASKTAG_DESC_ORDER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid label
        assertParseFailure(parser, INVALID_LABEL_DESC + DATE_DESC_SEPT + TASKTAG_DESC_ORDER,
                Label.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, LABEL_DESC_ORDER + INVALID_DATE_DESC + TASKTAG_DESC_ORDER,
                Date.MESSAGE_CONSTRAINTS);

        // invalid task tag
        assertParseFailure(parser, LABEL_DESC_ORDER + DATE_DESC_SEPT + INVALID_TASKTAG_DESC,
                TaskTag.MESSAGE_CONSTRAINTS);
    }
}
