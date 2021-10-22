package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkOrderCommand;

/**
 * Mirrors DeleteCommandParserTest
 */
class MarkOrderCommandParserTest {
    private MarkOrderCommandParser parser = new MarkOrderCommandParser();

    @Test
    public void parse_validArgs_returnsMarkOrderCommand() {
        assertParseSuccess(parser, "1", new MarkOrderCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE));
    }

}
