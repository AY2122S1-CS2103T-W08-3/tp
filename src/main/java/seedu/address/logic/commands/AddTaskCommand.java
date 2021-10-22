package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the application. "
            + "Parameters: "
            + PREFIX_LABEL + "LABEL "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TASK_TAG + "TASKTAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LABEL + "Sew buttons onto black blazer "
            + PREFIX_DATE + "20th August 2021 "
            + PREFIX_TASK_TAG + "SO2103";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddTaskCommand
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
