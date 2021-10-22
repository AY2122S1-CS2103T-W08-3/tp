package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_OCT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LABEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_SEW;
import static seedu.address.logic.commands.CommandTestUtil.TASKTAG_DESC_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL_SEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASKTAG_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.Date;
import seedu.address.model.task.Label;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LABEL_ORDER, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTaskCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + LABEL_DESC_ORDER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + LABEL_DESC_ORDER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_LABEL_DESC, Label.MESSAGE_CONSTRAINTS); // invalid label
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date

        // invalid label
        assertParseFailure(parser, "1" + INVALID_LABEL_DESC + DATE_DESC_OCT, Label.MESSAGE_CONSTRAINTS);

        // valid label followed by invalid label. The test case for invalid label followed by valid label
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + LABEL_DESC_SEW + INVALID_LABEL_DESC, Label.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASKTAG_DESC_ORDER + LABEL_DESC_SEW + DATE_DESC_SEPT;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_SEW)
                .withDate(VALID_DATE_SEPT).withTaskTag(VALID_TASKTAG_ORDER).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TASKTAG_DESC_ORDER + LABEL_DESC_SEW;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_SEW)
                .withTaskTag(VALID_TASKTAG_ORDER).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // label
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + LABEL_DESC_ORDER;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_ORDER).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_OCT;
        descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_OCT).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // taskTag
        userInput = targetIndex.getOneBased() + TASKTAG_DESC_ORDER;
        descriptor = new EditTaskDescriptorBuilder().withTaskTag(VALID_TASKTAG_ORDER).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DATE_DESC_SEPT + LABEL_DESC_ORDER
                + DATE_DESC_SEPT + LABEL_DESC_ORDER + LABEL_DESC_SEW;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_SEPT)
                .withLabel(VALID_LABEL_SEW).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_LABEL_DESC + LABEL_DESC_SEW;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withLabel(VALID_LABEL_SEW).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_LABEL_DESC + DATE_DESC_SEPT + LABEL_DESC_SEW;
        descriptor = new EditTaskDescriptorBuilder().withDate(VALID_DATE_SEPT).withLabel(VALID_LABEL_SEW).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
