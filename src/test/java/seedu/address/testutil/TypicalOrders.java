package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_SALE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CUSTOMER_SALE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CUSTOMER_SALE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SEPT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.OrderBook;
import seedu.address.model.order.Order;

public class TypicalOrders {

    public static final Order SALESORDER1 = new OrderBuilder().withCustomer("Josh")
            .withDate("2021-09-18").withAmount("10").withLabel("testorder1").withId(1).build();
    public static final Order SALESORDER2 = new OrderBuilder().withCustomer("Mac")
            .withDate("2021-09-19").withAmount("15").withIsComplete(true).withId(2).withLabel("testorder2").build();
    public static final Order SALESORDER3 = new OrderBuilder().withCustomer("Clark")
            .withDate("2021-09-20").withAmount("20").withIsComplete(true).withId(3).withLabel("testorder3").build();

    // Manually added - Order's details found in {@code CommandTestUtil}
    public static final Order ORDER1 = new OrderBuilder().withCustomer(VALID_CUSTOMER_SALE1)
            .withDate(VALID_DATE_SEPT).withAmount(VALID_AMOUNT_SALE1).build();
    public static final Order ORDER2 = new OrderBuilder().withCustomer(VALID_CUSTOMER_SALE2)
            .withDate(VALID_DATE_OCT).withAmount(VALID_AMOUNT_SALE2).build();

    /**
     * Returns an {@code OrderBook} with all the typical tasks added.
     */
    public static OrderBook getTypicalOrderBook() {
        OrderBook sb = new OrderBook();
        for (Order order : getTypicalOrder()) {
            sb.addOrder(order);
        }
        return sb;
    }

    public static List<Order> getTypicalOrder() {
        return new ArrayList<>(Arrays.asList(SALESORDER1, SALESORDER2, SALESORDER3));
    }

}
