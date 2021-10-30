package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.exceptions.OrderNotFoundException;
import seedu.address.testutil.OrderBuilder;

class OrderListTest {
    private final OrderList orderList = new OrderList();
    // project checkstyle blocks me from naming this in all caps as per normal convention
    private final Order testorder = new OrderBuilder().build();

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.hasOrder(null));
    }

    @Test
    public void hasOrder_orderInList_returnsTrue() {
        orderList.add(testorder);
        assertTrue(orderList.hasOrder(testorder));
    }

    @Test
    public void hasOrder_orderNotInList_returnsFalse() {
        assertFalse(orderList.hasOrder(testorder));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.add(null));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderList.remove(testorder));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        orderList.add(testorder);
        orderList.remove(testorder);
        assertFalse(orderList.hasOrder(testorder));
    }

    @Test
    public void markComplete_existingOrder_marksOrder() {
        orderList.add(testorder);
        orderList.markComplete(testorder, 0);
        assertTrue(testorder.getIsComplete());
    }
}
