package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Date;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;
import seedu.address.model.order.Order;

public class DeleteOrderCommandTest {
    private static final Order testOrder = new Order(new Customer("test customer"),
            new Date("test date"), new Amount("99.99"));

    @Test
    public void execute_validIndexDeletion_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();

        CommandResult commandResult = new DeleteOrderCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, testOrder),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.listWithOneOrder);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, (
                ) -> deleteOrderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        DeleteOrderCommand deleteFirstOrderCommand = new DeleteOrderCommand(INDEX_FIRST_PERSON);
        DeleteOrderCommand deleteSecondOrderCommand = new DeleteOrderCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstOrderCommand.equals(deleteFirstOrderCommand));

        // same values -> returns true
        DeleteOrderCommand deleteFirstCommandCopy = new DeleteOrderCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstOrderCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstOrderCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstOrderCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstOrderCommand.equals(deleteSecondOrderCommand));
    }


    private class ModelStubWithOneOrder extends ModelStub {
        private final ObservableList<Order> listWithOneOrder = FXCollections.observableArrayList(testOrder);

        @Override
        public void deleteOrder(Order order) {
            requireNonNull(order);
            listWithOneOrder.remove(order);
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            return listWithOneOrder;
        }
    }
}
