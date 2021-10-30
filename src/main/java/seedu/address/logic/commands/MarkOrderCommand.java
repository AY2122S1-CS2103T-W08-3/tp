package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Marks an order as complete, with the order identified using its displayed index from the application
 */
public class MarkOrderCommand extends Command {

    public static final String COMMAND_WORD = "markorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks an order identified by the index number used in the displayed order list as complete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_ORDER_SUCCESS = "Marked Order: %1$s";

    private final Index targetIndex;

    public MarkOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToMark = lastShownList.get(targetIndex.getZeroBased());
        model.markOrder(orderToMark, targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_MARK_ORDER_SUCCESS, orderToMark),
                CommandResult.DisplayState.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MarkOrderCommand
                && targetIndex.equals(((MarkOrderCommand) other).targetIndex));
    }

}
