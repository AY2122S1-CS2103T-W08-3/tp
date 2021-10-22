package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkOrderCommand object
 */
public class MarkOrderCommandParser implements Parser<MarkOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkOrderCommand
     * and returns a MarkOrderCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public MarkOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
