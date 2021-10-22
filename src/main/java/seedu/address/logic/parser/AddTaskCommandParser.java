package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Date;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Label;
import seedu.address.model.task.Task;

public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LABEL, PREFIX_DATE, PREFIX_TASK_TAG);

        //checks that preamble has only spaces, and none of the prefix values are empty
        //important for the argmultimap.getvalue().get() calls below
        if (!arePrefixesPresent(argMultimap, PREFIX_LABEL, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Label label = ParserUtil.parseLabel(argMultimap.getValue(PREFIX_LABEL).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        TaskTag taskTag = ParserUtil.parseTaskTag(argMultimap.getValue(PREFIX_TASK_TAG).orElse("General"));
        Task task = new Task(label, date, taskTag);

        return new AddTaskCommand(task);
    }


    //Reused from AddCommandParser - consider refactoring in future.
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
